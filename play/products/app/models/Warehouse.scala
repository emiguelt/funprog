package models

import org.squeryl.KeyedEntity

case class Warehouse(
    id: Long,
    name: String
)extends KeyedEntity[Long]