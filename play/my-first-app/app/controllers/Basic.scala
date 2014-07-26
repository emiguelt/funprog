package controllers

import play.api.mvc._

object Basic extends Controller{
  
  def hello = Action{
    Ok("Hello world!!!")
  }
  
  def todo = TODO
  
  def hello2(name : String) = Action{
    Ok("Hello " + name)
  }
  
  def helloWithTemplate(name: String) = Action{
    Ok(views.html.helloWithTemplate(name))
  }
}