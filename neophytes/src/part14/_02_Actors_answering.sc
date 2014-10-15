package part14

object _02_Actors_answering {
  import akka.actor.Actor
  import akka.actor.ActorSystem
  import akka.actor.{ ActorRef, Props }

  sealed trait CoffeeRequest
  case object CapuccinoRequest extends CoffeeRequest
  case object ColombianRequest extends CoffeeRequest
  case object ClosingTime

  case class Bill(cent: Int)

  class Barista extends Actor {
    def receive = {
      case CapuccinoRequest =>
        sender ! Bill(250)
        println("I have to prepare capuccino, thread: " + Thread.currentThread().getId())
      case ColombianRequest =>
        sender ! Bill(300)
        println("Let's prepare a Colombian coffee, thread: " + Thread.currentThread().getId())
      case ClosingTime =>
        println("Shutting down..., thread: " + Thread.currentThread().getId())
        context.system.shutdown()
    }
  }

  case object CaffeineWithdrawalWarning
  class Customer(caffeineSource: ActorRef) extends Actor {
    def receive = {
      case CaffeineWithdrawalWarning => caffeineSource ! ColombianRequest
      case Bill(cents) => println(s"I have to pay $cents cents, thread: " + Thread.currentThread().getId())
    }
  }

  val system = ActorSystem("System")
  val barista: ActorRef = system.actorOf(Props[Barista], "Barista")
  val customer = system.actorOf(Props(classOf[Customer], barista), "Customer")

  customer ! CaffeineWithdrawalWarning

  println("Coffee requested, thread: " + Thread.currentThread().getId())
  barista ! ClosingTime

  Thread.sleep(2000)
}