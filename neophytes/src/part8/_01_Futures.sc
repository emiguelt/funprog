package part8

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }
import scala.concurrent._
import CapuccinoMaker._

object _01_Futures {

  //callbacks
  grind("Colombian beans").onSuccess {
    case ground => println("Ground Colombian coffe")
  }

  grind("baked beans").onComplete {
    case Success(ground) => println("Baked beans coffe")
    case Failure(ex) => println("Exception ocurred while grinding," + ex)
  }

  // this sleep is to let daemon threads let to finish tasks
  Thread.sleep(2000)
  //map
  heatWater(Water(25)).map { water =>
    println("Back to future!")
    (80 to 85).contains(water.temperature)
  }
  Thread.sleep(2000)
  //flatmap
  def temperatureOkay(water: Water) =
    Future { (80 to 85).contains(water.temperature) }
  //- nested future (Future[Future[Boolean]]
  heatWater(Water(25)).map(water =>
    temperatureOkay(water))
  Thread.sleep(2000)
  //- flat
  heatWater(Water(25)).flatMap(water =>
    temperatureOkay(water))
  Thread.sleep(2000)
  //for comprehensions
  //- sequential
  for {
    ground <- grind("Colombian beans")
    water <- heatWater(Water(85))
    foam <- frothMilk("milk")
    expresso <- brew(ground, water)
  } yield combine(expresso, foam)
  Thread.sleep(6000)

  //- parallel
  val groundCoffe = grind("Colombian beans")
  val hotWater = heatWater(Water(85))
  val foamMilk = frothMilk("milk")
  for {
    ground <- groundCoffe
    water <- hotWater
    foam <- foamMilk
    expresso <- brew(ground, water)
  } yield combine(expresso, foam)
  Thread.sleep(2000)
}