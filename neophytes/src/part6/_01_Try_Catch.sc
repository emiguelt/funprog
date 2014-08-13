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
                                                  //| tStream@781f6226)))
  inputStreamForUrl("httpww.google.com")          //> res6: scala.util.Try[scala.util.Try[scala.util.Try[java.io.InputStream]]] = 
                                                  //| Failure(java.net.MalformedURLException: no protocol: httpww.google.com)
  def inputStreamForUrlFlat(url: String): Try[InputStream] = parseUrl(url).flatMap {
    u => Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream()))
  }                                               //> inputStreamForUrlFlat: (url: String)scala.util.Try[java.io.InputStream]
  inputStreamForUrlFlat("http://www.google.com")  //> res7: scala.util.Try[java.io.InputStream] = Success(sun.net.www.protocol.htt
                                                  //| p.HttpURLConnection$HttpInputStream@105e55ab)
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

  //For comprehensions
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
                                                  //| age"><title>Google</title><script>(function(){window.google={kEI:'k7XrU5nMK
                                                  //| 5HgsASfuYH4Aw',kEXPI:'4791,17259,4000116,4007661,4008142,4009033,4010073,40
                                                  //| 10806,4010858,4010899,4011228,4011258,4011679,4012373,4012504,4013414,40135
                                                  //| 91,4013723,4013823,4013967,4013979,4014016,4014431,4014515,4014636,4014805,
                                                  //| 4014991,4015234,4015266,4015550,4015587,4015772,4016127,4016309,4016373,401
                                                  //| 6487,4016824,4016976,4017204,4017285,4017595,4017639,4017658,4017659,401769
                                                  //| 4,4017818,4017894,4017981,4018106,4018126,4018251,4018411,4018519,4018544,4
                                                  //| 018569,4018638,4018914,4018923,4018932,4019014,4019018,4019084,4019142,4019
                                                  //| 191,4019207,4019423,4019438,4019479,4019481,4019483,4019494,4019564,4019590
                                                  //| ,4019664,4019684,4019789,4019800,4019801,4019827,4019850,4019856,4019875,40
                                                  //| 19888,4020014,4020038,4
                                                  //| Output exceeds cutoff limit.

  //recover
  val content = getUrlContent("bla bla") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Make sure url is correct")
    case _ => Iterator("Unexpected exception")
  }                                               //> content  : scala.util.Try[Iterator[String]] = Success(non-empty iterator)
  
  content.foreach(_.foreach(println))             //> Make sure url is correct
}