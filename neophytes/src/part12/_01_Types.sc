package part12

import Statistics._
import JodaImplicits._
import org.joda.time.Duration._

object _01_Types {
  val numbers = Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)

  println(mean(numbers))

  //external library
  val durations = Vector(standardSeconds(20), standardSeconds(57), standardMinutes(2),
    standardMinutes(17), standardMinutes(30), standardMinutes(58), standardHours(2),
    standardHours(5), standardHours(8), standardHours(17), standardDays(1),
    standardDays(4))
  println(mean(durations).getStandardHours)

}