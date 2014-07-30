package part4

object _01_PatternMatching_Anonymous {
  val wordFrecuencies = ("Test1", 10) :: ("Test2", 20) :: ("Test3", 5) :: Nil
                                                  //> wordFrecuencies  : List[(String, Int)] = List((Test1,10), (Test2,20), (Test3
                                                  //| ,5))

  def wordsWithoutOutliers(words: Seq[(String, Int)]): Seq[String] =
    words.filter { case (_, f) => f > 5 && f < 20 } map { case (w, _) => w }
                                                  //> wordsWithoutOutliers: (words: Seq[(String, Int)])Seq[String]

  //test
  wordsWithoutOutliers(wordFrecuencies)           //> res0: Seq[String] = List(Test1)
  
  //----------------
  
  wordFrecuencies.collect{case (word, freq) if freq > 5 && freq < 20 => word}
                                                  //> res1: List[String] = List(Test1)
}