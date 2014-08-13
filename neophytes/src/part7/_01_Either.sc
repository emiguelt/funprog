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
                                                  //| e"><title>Google</title><script>(function(){window.google={kEI:'VLXrU7P1MPC-
                                                  //| sQSzo4HwBA',kEXPI:'4791,17259,4000116,4007661,4008142,4009033,4010806,401085
                                                  //| 8,4010899,4011228,4011258,4011679,4012373,4012504,4013414,4013591,4013723,40
                                                  //| 13823,4013967,4013979,4014016,4014431,4014515,4014636,4014805,4014991,401523
                                                  //| 4,4015266,4015550,4015587,4015772,4016127,4016309,4016373,4016487,4016824,40
                                                  //| 16976,4017204,4017285,4017578,4017595,4017639,4017658,4017659,4017694,401781
                                                  //| 8,4017894,4017981,4017982,4018106,4018126,4018251,4018519,4018544,4018569,40
                                                  //| 18638,4018673,4018914,4018923,4018932,4018998,4019014,4019018,4019084,401914
                                                  //| 2,4019191,4019207,4019373,4019423,4019438,4019479,4019481,4019483,4019494,40
                                                  //| 19564,4019590,4019664,4019684,4019789,4019800,4019801,4019827,4019850,401985
                                                  //| 6,4019888,4020014,402003
                                                  //| Output exceeds cutoff limit.
  // case
  def printContent(url: URL) = getContent(url) match {
    case Left(msg) => println(msg)
    case Right(source) => source.getLines.foreach(println)
  }                                               //> printContent: (url: java.net.URL)Unit

  printContent(new URL("http://someurl.com"))     //> Cannot access such url
  printContent(new URL("http://www.google.com"))  //> <!doctype html><html itemscope="" itemtype="http://schema.org/WebPage" lang=
                                                  //| "es-419"><head><meta content="/images/google_favicon_128.png" itemprop="imag
                                                  //| e"><title>Google</title><script>(function(){window.google={kEI:'VbXrU_auBsHm
                                                  //| sASG44CYDA',kEXPI:'4791,17259,4000116,4007661,4008142,4009033,4010073,401080
                                                  //| 6,4010858,4010899,4011228,4011258,4011679,4012373,4012504,4013414,4013591,40
                                                  //| 13723,4013823,4013920,4013967,4013979,4014016,4014431,4014515,4014637,401480
                                                  //| 5,4014991,4015234,4015266,4015550,4015587,4015772,4016127,4016309,4016373,40
                                                  //| 16487,4016824,4016976,4017204,4017284,4017595,4017639,4017658,4017659,401769
                                                  //| 4,4017818,4017894,4017981,4017982,4018106,4018126,4018251,4018519,4018544,40
                                                  //| 18569,4018638,4018673,4018914,4018923,4018933,4018998,4019014,4019018,401902
                                                  //| 9,4019084,4019142,4019181,4019191,4019207,4019423,4019438,4019479,4019481,40
                                                  //| 19483,4019494,4019564,4019590,4019664,4019684,4019789,4019800,4019801,401982
                                                  //| 7,4019850,4019856,401987
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

  // flatmap
  val part5 = new URL("http://t.co/UR1aalX4")     //> part5  : java.net.URL = http://t.co/UR1aalX4
  val part6 = new URL("http://t.co/6wlKwTmu")     //> part6  : java.net.URL = http://t.co/6wlKwTmu

  val content = getContent(part5).right.map(a =>
    getContent(part6).right.map(b => (a.getLines.size + b.getLines.size) / 2))
                                                  //> content  : Serializable with Product with scala.util.Either[String,Serializ
                                                  //| able with Product with scala.util.Either[String,Int]] = Right(Right(537))
 val contentFlat = getContent(part5).right.flatMap(a =>
  getContent(part6).right.map(b => (a.getLines.size + b.getLines.size)/2))
                                                  //> contentFlat  : scala.util.Either[String,Int] = Right(537)
 }