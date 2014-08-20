package part6

import scala.util.Try
import java.net.URL
import java.io.InputStream
import java.io.FileNotFoundException
import java.net.MalformedURLException

object _01_Try_Catch {
  def parseUrl(url: String) = Try(new URL(url))

  //Success
  parseUrl("http://www.testurl.co")
  //Failure
  parseUrl("wwwtesturlco")
  parseUrl("wwwtesturlco").getOrElse("http://default.co")

  //chaining
  parseUrl("http://www.testurl.co").map(_.getProtocol())
  parseUrl("wwwtesturlco").map(_.getProtocol())
  def inputStreamForUrl(url: String): Try[Try[Try[InputStream]]] = parseUrl(url).map {
    u => Try(u.openConnection()).map(conn => Try(conn.getInputStream()))
  }

  inputStreamForUrl("http://www.google.com")
  inputStreamForUrl("httpww.google.com")
  def inputStreamForUrlFlat(url: String): Try[InputStream] = parseUrl(url).flatMap {
    u => Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream()))
  }
  inputStreamForUrlFlat("http://www.google.com")
  inputStreamForUrlFlat("httpww.google.com")

  //filter & foreach

  def parseHttpUrl(url: String) = parseUrl(url).filter(_.getProtocol == "http")
  parseHttpUrl("http://www.url.com")
  parseHttpUrl("https://www.url.com")

  parseHttpUrl("http://www.url.com").foreach(println)
  //prints nothing
  parseHttpUrl("https://www.url.com").foreach(println)

  //For comprehensions
  import scala.io.Source

  def getUrlContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseUrl(url)
      connection <- Try(url.openConnection)
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines

  getUrlContent("http://www.google.com")
  getUrlContent("www.google.com")

  import scala.util.Success
  import scala.util.Failure

  getUrlContent("http://www.google.com") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println("Exception: " + ex)
  }

  //recover
  val content = getUrlContent("bla bla") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Make sure url is correct")
    case _ => Iterator("Unexpected exception")
  }
  
  content.foreach(_.foreach(println))
}