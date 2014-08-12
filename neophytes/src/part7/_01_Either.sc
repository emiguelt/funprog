package part7

import scala.io.Source
import java.net.URL
import scala.util.Right

object _01_Either {
  def getContent(url: URL): Either[String, Source] =
    if (url.getHost.contains("someurl"))
      Left("Cannot access such url")
    else
      Right(Source.fromURL(url))                  //> getContent: (url: java.net.URL)Either[String,scala.io.Source]

  getContent(new URL("http://someurl.com"))       //> res0: Either[String,scala.io.Source] = Left(Cannot access such url)
  getContent(new URL("http://www.google.com")).right.get.getLines.foreach(println)
                                                  //> <!doctype html><html itemscope="" itemtype="http://schema.org/WebPage" lang=
                                                  //| "es-419"><head><meta content="/images/google_favicon_128.png" itemprop="imag
                                                  //| e"><title>Google</title><script>(function(){
                                                  //| window.google={kEI:"-W7qU4TmH-PgsATLhYCIDQ",getEI:function(a){for(var c;a&&(
                                                  //| !a.getAttribute||!(c=a.getAttribute("eid")));)a=a.parentNode;return c||googl
                                                  //| e.kEI},https:function(){return"https:"==window.location.protocol},kEXPI:"479
                                                  //| 1,25657,4000116,4007661,4008142,4009033,4010806,4010858,4010899,4011228,4011
                                                  //| 258,4011679,4012373,4012504,4013414,4013591,4013605,4013723,4013823,4013967,
                                                  //| 4013979,4014016,4014431,4014515,4014636,4014805,4014991,4015234,4015266,4015
                                                  //| 550,4015587,4015772,4016127,4016309,4016373,4016487,4016824,4016976,4017204,
                                                  //| 4017285,4017595,4017639,4017658,4017659,4017694,4017818,4017894,4017981,4017
                                                  //| 982,4018106,4018126,4018181,4018251,4018519,4018544,4018569,4018638,4018827,
                                                  //| 4018914,4018923,4018933,4018998,4019014,4019018,4019084
                                                  //| Output exceeds cutoff limit.
  // case
  def printContent(url: URL) = getContent(url) match {
    case Left(msg) => println(msg)
    case Right(source) => source.getLines.foreach(println)
  }                                               //> printContent: (url: java.net.URL)Unit

  printContent(new URL("http://someurl.com"))     //> Cannot access such url
  printContent(new URL("http://www.google.com"))  //> <!doctype html><html itemscope="" itemtype="http://schema.org/WebPage" lang=
                                                  //| "es-419"><head><meta content="/images/google_favicon_128.png" itemprop="imag
                                                  //| e"><title>Google</title><script>(function(){
                                                  //| window.google={kEI:"-W7qU7SDL6S-sQTXkIGIDQ",getEI:function(a){for(var c;a&&(
                                                  //| !a.getAttribute||!(c=a.getAttribute("eid")));)a=a.parentNode;return c||googl
                                                  //| e.kEI},https:function(){return"https:"==window.location.protocol},kEXPI:"479
                                                  //| 1,4896,17259,4000116,4007661,4008142,4009033,4010806,4010858,4010899,4011228
                                                  //| ,4011258,4011679,4012373,4012504,4013414,4013591,4013723,4013823,4013967,401
                                                  //| 3979,4014016,4014431,4014515,4014636,4014805,4014991,4015234,4015266,4015550
                                                  //| ,4015587,4015772,4016127,4016309,4016373,4016487,4016824,4016976,4017204,401
                                                  //| 7285,4017578,4017595,4017639,4017658,4017659,4017694,4017818,4017894,4017981
                                                  //| ,4017982,4018106,4018126,4018180,4018251,4018519,4018544,4018569,4018638,401
                                                  //| 8730,4018914,4018923,4018933,4019014,4019018,4019084,40
                                                  //| Output exceeds cutoff limit.

  //projections

  def contentR(url: URL) =
    getContent(url).right.map(Iterator(_))        //> contentR: (url: java.net.URL)Serializable with Product with scala.util.Eithe
                                                  //| r[String,Iterator[scala.io.Source]]
  contentR(new URL("http://www.google.com"))      //> res1: Serializable with Product with scala.util.Either[String,Iterator[scala
                                                  //| .io.Source]] = Right(non-empty iterator)
  contentR(new URL("http://www.someurl.com"))     //> res2: Serializable with Product with scala.util.Either[String,Iterator[scala
                                                  //| .io.Source]] = Left(Cannot access such url)

  def contentL(url: URL) =
    getContent(url).left.map(Iterator(_))         //> contentL: (url: java.net.URL)Serializable with Product with scala.util.Eithe
                                                  //| r[Iterator[String],scala.io.Source]
  contentL(new URL("http://www.google.com"))      //> res3: Serializable with Product with scala.util.Either[Iterator[String],scal
                                                  //| a.io.Source] = Right(non-empty iterator)
  contentL(new URL("http://www.someurl.com"))     //> res4: Serializable with Product with scala.util.Either[Iterator[String],sca
                                                  //| la.io.Source] = Left(non-empty iterator)

}