package ch02

/**
  * Created by EdwinT on 14/03/2017.
  */
object Ch02E2_1_Fibo {
  def fibo(x: Int): Int = {
    def fibo_tail(acc1: Int, acc2: Int, idx: Int): Int = {
      if (x == 0 || x == 1) x
      else if (x == idx) acc1 + acc2
      else fibo_tail(acc2, acc1 + acc2, idx + 1)
    }
    fibo_tail(0, 1, 2)
  }
}


