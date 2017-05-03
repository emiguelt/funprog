package ch02

import ch02.Ch02E2_1_Fibo.fibo
import org.scalatest.FreeSpec

/**
  * Created by EdwinT on 14/03/2017.
  */
class Ch02E2_1_Fibo_Test extends  FreeSpec{
  "Ex 2.1 Fibonacci" - {
    "Test fibo root values" - {
      assertFiboEquals(0,0)
      assertFiboEquals(1,1)
    }

    "Test fibo for 2-5" - {
      assertFiboEquals(2,1)
      assertFiboEquals(3,2)
      assertFiboEquals(4,3)
      assertFiboEquals(5,5)
    }

    def assertFiboEquals(x: Int, expected: Int): Unit ={
      assert(fibo(x) == expected)
    }
  }
}
