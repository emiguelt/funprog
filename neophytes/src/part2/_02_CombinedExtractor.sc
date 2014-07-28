package part2

object _02_CombinedExtractor {
  object Names{
  	def unapplySeq(name: String): Option[(String, String, Seq[String])] = {
  		val names = name.trim.split(" ")
  		if(names.size < 2) None
  		else Some((names.last, names.head, names.drop(1).dropRight(1)))
  	}
  }
  
  def greet(fullName: String) = fullName match{
  	case Names(lastname, firstname, _*) => "Hello " + firstname + " " + lastname
  	case _ => "Sorry, we need at least 2 name values"
  }                                               //> greet: (fullName: String)String
  
  //Testing
  greet("Edwin")                                  //> res0: String = Sorry, we need at least 2 name values
  greet("Edwin Miguel")                           //> res1: String = Hello Edwin Miguel
  greet("Edwin Miguel Test")                      //> res2: String = Hello Edwin Test
}