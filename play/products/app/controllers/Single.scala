package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.libs.json.Json

object Single extends Controller {
  def eanList = Action{
    Ok(Json.toJson(Product.findAll.map(_.ean)))
  }
  
  def main = Action{ 
    Ok(views.html.single())
  }
}