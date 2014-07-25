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
  }                                               //> greetWithFirstName: (name: String)String
  
  //test
  greetWithFirstName("")                          //> res0: String = Hello anonymous
  greetWithFirstName("Miguel")                    //> res1: String = Hi Miguel
  greetWithFirstName("Edwin Miguel")              //> res2: String = Hi Edwin
}