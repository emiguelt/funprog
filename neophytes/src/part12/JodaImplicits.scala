package part12

object JodaImplicits {
  import Math.NumberLike
  import org.joda.time.Duration

  implicit object NumberLikeDuration extends NumberLike[Duration] {
    def plus(x: Duration, y: Duration): Duration = x.plus(y)
    def minus(x: Duration, y: Duration): Duration = x.minus(y)
    def divide(x: Duration, y: Duration): Duration = Duration.millis(x.getMillis / y.getMillis())
  }
}