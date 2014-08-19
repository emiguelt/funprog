package models

import play.api.db.DB
import play.api.Play.current
import org.squeryl.KeyedEntity
import org.squeryl.{ Table, Query }
import org.squeryl.PrimitiveTypeMode._

// model class
case class Product(id: Long, ean: Long, name: String, description: String) extends KeyedEntity[Long]

//dao
object Product {
  import config.Database.{ productsTable, stockitemsTable }

  def allQ: Query[Product] = from(productsTable) {
    product => select(product) orderBy (product.name desc)
  }

  def findAll: Iterable[Product] = inTransaction {
    allQ.toList
  }

  def productsInWarehouse(warehouse: Warehouse) = {
    join(productsTable, stockitemsTable)((product, stockItem) =>
      where(stockItem.warehouseId === warehouse.id).select(product).on(product.id === stockItem.productId))
  }

  def productsInWarehouseByName(name: String, warehouse: Warehouse): Query[Product] = {
    from(productsInWarehouse(warehouse)) {
      product => where(product.name like name).select(product)
    }
  }

  def insert(product: Product): Product = inTransaction {
    productsTable.insert(product)
  }

//  def getAllProductsWithStockItems: Map[Product, List[StockItem]] = {
//    DB.withConnection { implicit connection =>
//      val sql = SQL("select p.*, s.* from products p " +
//        "inner join stock_items s on (p.id = s.product_id)")
//      val results: List[(Product, StockItem)] = sql.as(productStockItemParser *)
//      results.groupBy(_._1).mapValues(_.map(_._2))
//    }
//  }

  def update(product: Product) = inTransaction {
    productsTable.update(product)
  }

  def delete(id: Long) = inTransaction {
    productsTable.delete(id)
  }

  def findByEan(ean: Long): Option[Product] = inTransaction {
    from(productsTable) {
      product => where(product.ean === ean).select(product)
    }.headOption
  }

}