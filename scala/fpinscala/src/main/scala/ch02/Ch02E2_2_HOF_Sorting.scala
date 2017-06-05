package ch02

/**
  * Created by EdwinT on 31/05/2017.
  */
object Ch02E2_2_HOF_Sorting {
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    if (as == null || as.isEmpty || as.length == 1)
      true
    else {
      def loop(n: Int): Boolean =
        if (n == as.length)
          true
        else if (!ordered(as(n - 1), as(n)))
          false
        else
          loop(n + 1)
      loop(1)
    }

  }
}
