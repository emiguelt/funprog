package part8

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }
import scala.concurrent._

object _01_Futures {

  //callbacks
  CapuccinoMaker.grind("Colombian beans").onSuccess {
    case ground => println("Ground Colombian coffe")
  }

  CapuccinoMaker.grind("baked beans").onComplete {
    case Success(ground) => println("Baked beans coffe")
    case Failure(ex) => println("Exception ocurred while grinding," + ex)
  }
  
  // this sleep is to let daemon threads let to finish tasks
  Thread.sleep(2000)                              //> start grind beans
                                                  //| start grind beans
                                                  //| Exception ocurred while grinding,part8.GrindingException: Are you joking?
                                                  //| result : ()
                                                  //| end grind beans
                                                  //| Ground Colombian coffe
}