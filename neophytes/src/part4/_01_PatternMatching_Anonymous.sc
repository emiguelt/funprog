package part4

object _01_PatternMatching_Anonymous {
  val wordFrecuencies = ("Test1", 10) :: ("Test2", 20) :: ("Test3", 5) :: Nil

  def wordsWithoutOutliers(words: Seq[(String, Int)]): Seq[String] =
    words.filter { case (_, f) => f > 5 && f < 20 } map { case (w, _) => w }

  //test
  wordsWithoutOutliers(wordFrecuencies)
  
  //----------------
  
  wordFrecuencies.collect{case (word, freq) if freq > 5 && freq < 20 => word}
}