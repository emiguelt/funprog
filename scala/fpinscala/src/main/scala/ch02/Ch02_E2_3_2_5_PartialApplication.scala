package ch02

/**
  * Created by EdwinT on 6/06/2017.
  */
object Ch02_E2_3_2_5_PartialApplication {
  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    (a) => (b) => f(a,b)
  }

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    (a,b) => f(a)(b)
  }

  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    (a) => f(g(a))
  }
}
