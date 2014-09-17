package part12

object Math {
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def minus(x: T, y: T): T
    def divide(x: T, y: T): T
    def count(xs: Vector[T]) : T
  }

  object NumberLike {
    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def minus(x: Double, y: Double): Double = x - y
      def divide(x: Double, y: Double): Double = x / y
      def count(xs: Vector[Double]): Double = xs.size
    }
    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def minus(x: Int, y: Int): Int = x - y
      def divide(x: Int, y: Int): Int = x / y
      def count(xs: Vector[Int]): Int = xs.size
    }
  }
}

