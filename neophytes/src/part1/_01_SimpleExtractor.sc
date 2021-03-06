package part1

object _01_SimpleExtractor {
  trait User{
  	def name: String
  }
  
  class FreeUser(val name: String) extends User
  class PremiumUser(val name: String) extends User
  
  object FreeUser{
  	def unapply(user: FreeUser): Option[String] = Some(user.name)
  }
  
  object PremiumUser{
  	def unapply(user: PremiumUser): Option[String] = Some(user.name)
  }
  
  //testing
  FreeUser.unapply(new FreeUser("Miguel"))
  
  //testing in pattern matching
  val user = new PremiumUser("Miguel")
  user match{
  	case FreeUser(name) => "Hello" + name
  	case PremiumUser(name) => "Hello dear " + name;
  }
}