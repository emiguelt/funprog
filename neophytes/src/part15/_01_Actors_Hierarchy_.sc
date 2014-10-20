package part15

object _01_Actors_Hierarchy_ {
  import akka.actor._

  object Register {
    sealed trait Article
    case object Espresso extends Article
    case object Capuccino extends Article
    case class Transaction(article: Article)
  }

  object Barista {
    case object EspressoRequest
    case object ClosingTime
    case class EspressoCup(state: EspressoCup.State)
    object EspressoCup {
      sealed trait State
      case object Clean extends State
      case object Filled extends State
      case object Dirty extends State
    }

    case class Receipt(amount: Int)
  }

  class Register extends Actor {
    import Register._
    import Barista._
    
    var revenue = 0
    val prices = Map[Article, Int](Espresso -> 150, Capuccino->100)
    def receive = {
    	case Transaction(article) =>
    		val price = prices(article)
    		sender ! createReceipt(price)
    		revenue += price
    }
    def createReceipt(price: Int): Receipt = Receipt(price)
  }

  class Barista extends Actor {
    import Register._
    import Barista._
    import EspressoCup._
    import context.dispatcher
    import akka.util.Timeout
    import akka.pattern.ask
    import akka.pattern.pipe
    import concurrent.duration._
    
    implicit val timeout = Timeout(4.seconds)
    val register = context.actorOf(Props[Register], "Register")
    def receive = {
    	case EspressoRequest =>
    		val receipt = register ? Transaction(Espresso)
    		receipt.map((EspressoCup(Filled), _)).pipeTo(sender)
    	case ClosingTime => context.stop(self)
    }
  }

  println("end")                                  //> end
}