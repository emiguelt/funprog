package config

import org.squeryl.{ Table, Schema }
import org.squeryl.PrimitiveTypeMode._
import models.Warehouse
import models.Product
import models.StockItem

object Database extends Schema {
  val productsTable = table[Product]("products")
  val warehousesTable = table[Warehouse]("warehouses")
  val stockitemsTable = table[StockItem]("stock_items")

  on(productsTable) { p =>
    declare {
      p.id is (autoIncremented)
    }
  }

  on(stockitemsTable) { s =>
    declare {
      s.id is (autoIncremented)
    }
  }

  on(warehousesTable) { w =>
    declare {
      w.id is (autoIncremented)
    }
  }
}