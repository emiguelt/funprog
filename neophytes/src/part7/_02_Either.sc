package part7
import java.net.URL
import scala.io.Source
import scala.util.Right

object _02_Either {

def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("someurl"))
      Left("Cannot access such url")
    else
      Right(Source.fromURL(url))                  //> getContent: (url: java.net.URL)Either[String,scala.io.Source]
  
  val part5 = new URL("http://t.co/UR1aalX4")     //> part5  : java.net.URL = http://t.co/UR1aalX4
  val part6 = new URL("http://t.co/6wlKwTmu")     //> part6  : java.net.URL = http://t.co/6wlKwTmu
  
  //For comprehensions
  def averageLineCount(url1: URL, url2: URL): Either[String, Int] =
  	for{
  		source1 <- getContent(url1).right
  		source2 <- getContent(url2).right
  	}yield( (source1.getLines.size + source2.getLines.size)/2)
                                                  //> averageLineCount: (url1: java.net.URL, url2: java.net.URL)Either[String,Int]
                                                  //| 
  averageLineCount(part5, part6)                  //> res0: Either[String,Int] = Right(537)

  def averageLineCount2(url1: URL, url2: URL): Either[String, Int] =
  	for{
  		source1 <- getContent(url1).right
  		source2 <- getContent(url2).right
  		lines1 <- Right(source1.getLines.size).right
  		lines2 <- Right(source2.getLines.size).right
  	}yield( (lines1 + lines2)/2)              //> averageLineCount2: (url1: java.net.URL, url2: java.net.URL)Either[String,Int
                                                  //| ]
  averageLineCount2(part5, part6)                 //> res1: Either[String,Int] = Right(537)
}