package part2

object _01_DynamicExtractor {
	
	object GivenNames{
		def unapplySeq(name: String): Option[Seq[String]] = {
			val names = name.trim.split(" ")
			if(names.forall(_.isEmpty)) None else Some(names)
		}
	}

  def greetWithFirstName(name: String) = name match{
  	case GivenNames(firstName, _*) => "Hi " + firstName
  	case _ => "Hello anonymous"
  }
  
  //test
  greetWithFirstName("")
  greetWithFirstName("Miguel")
  greetWithFirstName("Edwin Miguel")
}