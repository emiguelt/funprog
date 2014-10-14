package part13

object _01_Actors {
  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.{ ActorRef, Props }

  sealed trait CoffeeRequest
  case object CapuccinoRequest extends CoffeeRequest
  case object ColombianRequest extends CoffeeRequest

  class Barista extends Actor {
    def receive = {
      case CapuccinoRequest => println("I have to prepare capuccino, thread: " + Thread.currentThread().getId())
      case ColombianRequest => println("Let's prepare a Colombian coffee, thread: " + Thread.currentThread().getId())
    }
  }

  val system = ActorSystem("System")
  val barista: ActorRef = system.actorOf(Props[Barista], "Barista")

  barista ! CapuccinoRequest
  barista ! ColombianRequest
  
  println("Coffee requested, thread: " + Thread.currentThread().getId())
  
   Thread.sleep(2000)
}