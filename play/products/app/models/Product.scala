package models

import anorm.SQL
import anorm.SqlQuery
import play.api.db.DB
import play.api.Play.current
import anorm.ResultSetParser
import anorm.SqlParser

// model class
case class Product(id: Long, ean: Long, name: String, description: String)

//dao
object Product {

  val sql: SqlQuery = SQL("select * from products order by name asc")

  def findAll: List[Product] = DB.withConnection {
    implicit connection =>
      sql().map(row =>
        Product(row[Long]("id"), row[Long]("ean"), row[String]("name"), row[String]("description"))).toList
  }

  // NOT WORKING IN SCALA 2.3
  //  def findAllWithPatterns: List[Product] = DB.withConnection {
  //    implicit connection =>
  //    import anorm.Row
  //    sql().collect {
  //      case Row(Some(id: Long), Some(ean: Long), Some(name: String), Some(description: String)) =>
  //        Product(id, ean, name, description)
  //    }.toList
  //  }

  import anorm.RowParser
  def productParser: RowParser[Product] = {
    import anorm.~
    import anorm.SqlParser._

    long("id") ~
      long("ean") ~
      str("name") ~
      str("description") map {
        case id ~ ean ~ name ~ description =>
          Product(id, ean, name, description)
      }
  }

  import anorm.ResultSetParser
  def productsParser: ResultSetParser[List[Product]] = {
    productParser *
  }

  def getAllWithParser: List[Product] = DB.withConnection {
    implicit connection =>
      sql.as(productsParser)
  }

  def productStockItemParser: RowParser[(Product, StockItem)] = {
    import anorm.SqlParser._
    import anorm.~

    productParser ~ StockItem.stockItemParser map (flatten)
  }

  def getAllProductsWithStockItems: Map[Product, List[StockItem]] = {
    DB.withConnection { implicit connection =>
      val sql = SQL("select p.*, s.* from products p " +
        "inner join stock_items s on (p.id = s.product_id)")
      val results: List[(Product, StockItem)] = sql.as(productStockItemParser *)
      results.groupBy(_._1).mapValues(_.map(_._2))
    }
  }

  def insert(product: Product): Boolean = DB.withConnection {
    implicit connection =>
      val addedRows = SQL("""insert into products
          values ({id}, {ean}, {name}, {description})""").on(
        "id" -> product.id,
        "ean" -> product.ean,
        "name" -> product.name,
        "description" -> product.description).executeUpdate
      addedRows == 1

  }

  def update(product: Product): Boolean = DB.withConnection {
    implicit connection =>
      val updateRows = SQL(""" update products set name = {name}, 
          ean = {ean}, description = {description}
          where id = {id}""").on(
        "id" -> product.id,
        "ean" -> product.ean,
        "name" -> product.name,
        "description" -> product.description).executeUpdate
      updateRows == 1
  }

  def delete(id: Long): Boolean = DB.withConnection {
    implicit connection =>
      val deleteRows = SQL("delect from products where id = {id}").on("id" -> id).executeUpdate
      deleteRows == 1
  }

  def findByEan(ean: Long): Option[Product] = DB.withConnection {
    implicit connection =>
      println("EAN: " + ean)
      val sql = SQL("select * from products where ean = " + ean)
      //fix that
      sql.as(productsParser) match {
        case head :: _ => Some(head)
        case _ => None
      }
  }

}