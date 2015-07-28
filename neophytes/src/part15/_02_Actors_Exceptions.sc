package part15

object _02_Actors_Exceptions {
  import akka.actor._
  
  class PaperJamException(msg: String) extends Exception(msg)

  object Register {
    sealed trait Article
    case object Espresso extends Article
    case object Capuccino extends Article
    case class Transaction(article: Article)
  }

  class Register extends Actor with ActorLogging{
    import Register._
    import Barista._

    var revenue = 0
    val prices = Map[Article, Int](Espresso -> 150, Capuccino -> 100)
    def receive = {
      case Transaction(article) =>
        val price = prices(article)
        sender ! createReceipt(price)
        revenue += price
    }
    def createReceipt(price: Int): Receipt = {
    	import util.Random
    	if(Random.nextBoolean)
    		throw new PaperJamException("sorry")
    	Receipt(price)
    }
    
    override def postRestart(reason: Throwable) = {
    	super.postRestart(reason);
    	log.info(s"Actor restarted, reason: ${reason.getMessage}")
    }
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

  object Customer {
    case object CaffeineWithdrawalWarning
  }

  class Customer(coffeSource: ActorRef) extends Actor with ActorLogging {
    import Customer._
    import Barista._
    import EspressoCup._

    def receive = {
      case CaffeineWithdrawalWarning => coffeSource ! EspressoRequest
      case (EspressoCup(Filled), Receipt(amount)) =>
        log.info(s"yay, caffeine for ${self}. Price $amount")
    }
  }

  val system = ActorSystem("CoffeeHouse")
  val barista = system.actorOf(Props[Barista], "Barista")
  val jhon = system.actorOf(Props(classOf[Customer], barista), "john")
  val anna = system.actorOf(Props(classOf[Customer], barista), "anna")

  import Customer._

  jhon ! CaffeineWithdrawalWarning
  anna ! CaffeineWithdrawalWarning

  Thread.sleep(2000)
  
  println("end")

}