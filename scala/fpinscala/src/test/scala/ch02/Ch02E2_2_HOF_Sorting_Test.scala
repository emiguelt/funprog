package ch02

import org.scalatest.FreeSpec
import ch02.Ch02E2_2_HOF_Sorting.isSorted

/**
  * Created by EdwinT on 31/05/2017.
  */
class Ch02E2_2_HOF_Sorting_Test extends FreeSpec {
  def intSortedPredicated (a: Int, b: Int) = a <= b
  def callIntSorted(as : Array[Int]) =
    isSorted(as, intSortedPredicated)

    "Empty int array is sorted" in {
     assert(callIntSorted(new Array[Int](0)))
    }

    "One item size array is sorted" in {
      assert(callIntSorted(Array{1}))
    }

    "Two items  size array sorted return true" in {
      assert(callIntSorted(Array(1,2)))
    }
    "Two items  size array unsorted return false" in {
      assert(callIntSorted(Array(3,2))==false)
    }

}

