package part6

import scala.util.Try
import java.net.URL
import java.io.InputStream
import java.io.FileNotFoundException
import java.net.MalformedURLException

object _01_Try_Catch {
  def parseUrl(url: String) = Try(new URL(url))   //> parseUrl: (url: String)scala.util.Try[java.net.URL]

  //Success
  parseUrl("http://www.testurl.co")               //> res0: scala.util.Try[java.net.URL] = Success(http://www.testurl.co)
  //Failure
  parseUrl("wwwtesturlco")                        //> res1: scala.util.Try[java.net.URL] = Failure(java.net.MalformedURLException:
                                                  //|  no protocol: wwwtesturlco)
  parseUrl("wwwtesturlco").getOrElse("http://default.co")
                                                  //> res2: java.io.Serializable = http://default.co

  //chaining
  parseUrl("http://www.testurl.co").map(_.getProtocol())
                                                  //> res3: scala.util.Try[String] = Success(http)
  parseUrl("wwwtesturlco").map(_.getProtocol())   //> res4: scala.util.Try[String] = Failure(java.net.MalformedURLException: no pr
                                                  //| otocol: wwwtesturlco)
  def inputStreamForUrl(url: String): Try[Try[Try[InputStream]]] = parseUrl(url).map {
    u => Try(u.openConnection()).map(conn => Try(conn.getInputStream()))
  }                                               //> inputStreamForUrl: (url: String)scala.util.Try[scala.util.Try[scala.util.Try
                                                  //| [java.io.InputStream]]]

  inputStreamForUrl("http://www.google.com")      //> res5: scala.util.Try[scala.util.Try[scala.util.Try[java.io.InputStream]]] = 
                                                  //| Success(Success(Success(sun.net.www.protocol.http.HttpURLConnection$HttpInpu
                                                  //| tStream@656de49c)))
  inputStreamForUrl("httpww.google.com")          //> res6: scala.util.Try[scala.util.Try[scala.util.Try[java.io.InputStream]]] = 
                                                  //| Failure(java.net.MalformedURLException: no protocol: httpww.google.com)
  def inputStreamForUrlFlat(url: String): Try[InputStream] = parseUrl(url).flatMap {
    u => Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream()))
  }                                               //> inputStreamForUrlFlat: (url: String)scala.util.Try[java.io.InputStream]
  inputStreamForUrlFlat("http://www.google.com")  //> res7: scala.util.Try[java.io.InputStream] = Success(sun.net.www.protocol.htt
                                                  //| p.HttpURLConnection$HttpInputStream@781f6226)
  inputStreamForUrlFlat("httpww.google.com")      //> res8: scala.util.Try[java.io.InputStream] = Failure(java.net.MalformedURLEx
                                                  //| ception: no protocol: httpww.google.com)

  //filter & foreach

  def parseHttpUrl(url: String) = parseUrl(url).filter(_.getProtocol == "http")
                                                  //> parseHttpUrl: (url: String)scala.util.Try[java.net.URL]
  parseHttpUrl("http://www.url.com")              //> res9: scala.util.Try[java.net.URL] = Success(http://www.url.com)
  parseHttpUrl("https://www.url.com")             //> res10: scala.util.Try[java.net.URL] = Failure(java.util.NoSuchElementExcept
                                                  //| ion: Predicate does not hold for https://www.url.com)

  parseHttpUrl("http://www.url.com").foreach(println)
                                                  //> http://www.url.com
  //prints nothing
  parseHttpUrl("https://www.url.com").foreach(println)

  // comprehensions
  import scala.io.Source

  def getUrlContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseUrl(url)
      connection <- Try(url.openConnection)
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines                       //> getUrlContent: (url: String)scala.util.Try[Iterator[String]]

  getUrlContent("http://www.google.com")          //> res11: scala.util.Try[Iterator[String]] = Success(non-empty iterator)
  getUrlContent("www.google.com")                 //> res12: scala.util.Try[Iterator[String]] = Failure(java.net.MalformedURLExce
                                                  //| ption: no protocol: www.google.com)

  import scala.util.Success
  import scala.util.Failure

  getUrlContent("http://www.google.com") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println("Exception: " + ex)
  }                                               //> <!doctype html><html itemscope="" itemtype="http://schema.org/WebPage" lang
                                                  //| ="es-419"><head><meta content="/images/google_favicon_128.png" itemprop="im
                                                  //| age"><title>Google</title><script>(function(){
                                                  //| window.google={kEI:"8hXpU5yPEqrIsATDn4DIDw",getEI:function(a){for(var c;a&&
                                                  //| (!a.getAttribute||!(c=a.getAttribute("eid")));)a=a.parentNode;return c||goo
                                                  //| gle.kEI},https:function(){return"https:"==window.location.protocol},kEXPI:"
                                                  //| 4791,17259,4000116,4003510,4007661,4008142,4009033,4010806,4010858,4010899,
                                                  //| 4011228,4011258,4011679,4012373,4012504,4013414,4013591,4013723,4013787,401
                                                  //| 3823,4013967,4013979,4014016,4014431,4014515,4014636,4014805,4014991,401523
                                                  //| 4,4015266,4015550,4015587,4015772,4016013,4016127,4016309,4016372,4016487,4
                                                  //| 016824,4016938,4016976,4017204,4017285,4017578,4017595,4017639,4017658,4017
                                                  //| 659,4017694,4017818,4017894,4017981,4017982,4018019,4018106,4018126,4018181
                                                  //| ,4018251,4018416,4018519,4018544,4018569,4018638,40
                                                  //| Output exceeds cutoff limit.

  //recover
  val content = getUrlContent("bla bla") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Make sure url is correct")
    case _ => Iterator("Unexpected exception")
  }                                               //> content  : scala.util.Try[Iterator[String]] = Success(non-empty iterator)
  
  content.foreach(_.foreach(println))             //> Make sure url is correct
}