package part9

import scala.concurrent.{ Future, Promise }
import concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }

object _01_Promises {
  case class TaxCut(reduction: Int)
  case class LameExcuse(msg: String) extends Exception(msg)

  object Goverment {
    def redeemCampaignPledge(): Future[TaxCut] = {
      val p = Promise[TaxCut]()
      Future {
        println("Start legislative period")
        Thread.sleep(2000)
        if (util.Random.nextInt(2) % 2 == 0) {
          p.success(TaxCut(20))
          println("We reduce tax, please reelect us")
        } else
          p.failure(LameExcuse("The africans need our help"))

      }
      p.future
    }
  }

  def evaluateCampaign() = {
    Goverment.redeemCampaignPledge().onComplete {
      case Success(taxCut) => println(s"Nice, taxes were cut in $taxCut percent")
      case Failure(ex) => println("Sorry, they reduced nothing")
    }
  }

  1.to(5).foreach { index =>
    evaluateCampaign()
  }

  Thread.sleep(3000)
}