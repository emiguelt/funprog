package part7

import scala.io.Source
import java.net.URL
import scala.util.Right

object _01_Either {
  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("someurl"))
      Left("Cannot access such url")
    else
      Right(Source.fromURL(url))

  getContent(new URL("http://someurl.com"))
  getContent(new URL("http://www.google.com")).right.get.getLines.foreach(println)
  // case
  def printContent(url: URL) = getContent(url) match {
    case Left(msg) => println(msg)
    case Right(source) => source.getLines.foreach(println)
  }

  printContent(new URL("http://someurl.com"))
  printContent(new URL("http://www.google.com"))

  //projections

  def contentR(url: URL) =
    getContent(url).right.map(Iterator(_))
  contentR(new URL("http://www.google.com"))
  contentR(new URL("http://www.someurl.com"))

  def contentL(url: URL) =
    getContent(url).left.map(Iterator(_))
  contentL(new URL("http://www.google.com"))
  contentL(new URL("http://www.someurl.com"))

  // flatmap
  val part5 = new URL("http://t.co/UR1aalX4")
  val part6 = new URL("http://t.co/6wlKwTmu")

  val content = getContent(part5).right.map(a =>
    getContent(part6).right.map(b => (a.getLines.size + b.getLines.size) / 2))
 val contentFlat = getContent(part5).right.flatMap(a =>
  getContent(part6).right.map(b => (a.getLines.size + b.getLines.size)/2))
 }