package part6

import scala.util.Try
import java.net.URL
import java.io.InputStream

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
 def inputStreamForUrl(url:String): Try[Try[Try[InputStream]]] = parseUrl(url).map{
 	u => Try(u.openConnection()).map(conn => Try(conn.getInputStream()))
 }                                                //> inputStreamForUrl: (url: String)scala.util.Try[scala.util.Try[scala.util.Try
                                                  //| [java.io.InputStream]]]
 
 inputStreamForUrl("http://www.google.com")       //> res5: scala.util.Try[scala.util.Try[scala.util.Try[java.io.InputStream]]] = 
                                                  //| Success(Success(Success(sun.net.www.protocol.http.HttpURLConnection$HttpInpu
                                                  //| tStream@656de49c)))
 inputStreamForUrl("httpww.google.com")           //> res6: scala.util.Try[scala.util.Try[scala.util.Try[java.io.InputStream]]] = 
                                                  //| Failure(java.net.MalformedURLException: no protocol: httpww.google.com)
 def inputStreamForUrlFlat(url:String): Try[InputStream] = parseUrl(url).flatMap{
 	u => Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream()))
 }                                                //> inputStreamForUrlFlat: (url: String)scala.util.Try[java.io.InputStream]
 inputStreamForUrlFlat("http://www.google.com")   //> res7: scala.util.Try[java.io.InputStream] = Success(sun.net.www.protocol.htt
                                                  //| p.HttpURLConnection$HttpInputStream@781f6226)
 inputStreamForUrlFlat("httpww.google.com")       //> res8: scala.util.Try[java.io.InputStream] = Failure(java.net.MalformedURLExc
                                                  //| eption: no protocol: httpww.google.com)
  
}