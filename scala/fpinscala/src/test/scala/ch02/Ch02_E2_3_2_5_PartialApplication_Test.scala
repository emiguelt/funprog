package ch02

import org.scalatest.FreeSpec
import ch02.Ch02_E2_3_2_5_PartialApplication._

/**
  * Created by EdwinT on 6/06/2017.
  */
class Ch02_E2_3_2_5_PartialApplication_Test extends FreeSpec{
  "Ex 2.3 Curry" in {
    assert(curry((a: Int,b: Int) => a + b)(1)(2) == 3)
  }

  "Ex 2.4 UnCurry" in {
    assert(uncurry((a:Int) => (b: Int) => a + b)(1,2) == 3)
  }

  "Ex 2.5 Composition" in {
    assert(compose((a:Int) => a*2, (b:Int) => b+2)(3) == (3+2)*2 )
  }
}
