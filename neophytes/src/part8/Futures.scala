package part8
import scala.util.Try
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random
import scala.util.Right

object Futures {
}

object CapuccinoMaker {
  //aliases
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    process("grind beans", 
        Unit => if (beans == "baked beans") Left(GrindingException("Are you joking?")) else Right(), 
        Unit => s"ground coffed with $beans"
    )
  }

  def heatWater(water: Water): Future[Water] = Future {
    process("heat water", 
        Unit => Right(), 
        Unit => water.copy(temperature = 85))
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
	  process("froth milk", 
	  Unit => Right(), 
	  Unit => s"frothed $milk")
  }
  
  def brew(groundCoffe: GroundCoffee, headWater: Water): Future[Espresso] = Future {
	  process("brew", 
	  Unit => Right(), 
	  Unit => "espresso")
  }

  private def process[T](name: String, cond: Unit => Either[Exception,Unit], build: Unit => T): T = {
    println(s"start $name")
    Thread.sleep(Random.nextInt(2000))
    cond() match {
      case Left(ex) => throw ex
      case Right(result) => {
    	  println(s"result : $result")
    	  println(s"end $name")
    	  build()
    }
    }
  }
}

case class GrindingException(msg: String) extends Exception(msg)
case class FrothingException(msg: String) extends Exception(msg)
case class WaterBoilingException(msg: String) extends Exception(msg)
case class BrewingException(msg: String) extends Exception(msg)
