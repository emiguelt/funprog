package part5

object _01_Option {
  case class User(
  	id: Int,
  	firstName: String,
  	lastName: String,
  	age: Int,
  	gender: Option[String]
  )
  
  object UserRepository{
  	private val users = Map(
  		1 -> User(1, "name1", "last1", 1, None),
  		2 -> User(2, "name2", "last2", 2, Some("male")),
  		3 -> User(3, "name3", "last3", 3, Some("female")))
  		
		def findById(id: Int) : Option[User] = users.get(id)
		def findAll = users.values
  }
  
  //test
  val user1 = UserRepository.findById(1)
  if(user1.isDefined){
  	println(user1)
  }
  
  //default value
  println(user1.get.gender.getOrElse("No gender defined"))
  
  //pattern matching
  UserRepository.findAll.foreach(_.gender match{
  	case Some(gender) => println("Gender: " + gender)
  	case None => println("No gender defined")
  })


	//side effect
	UserRepository.findById(1).foreach(user => println("Name: " + user.firstName))
	//will print nothing
	UserRepository.findById(5).foreach(user => println("Name: " + user.firstName))
	
	//Mapping an option
		
	//flapMap
	UserRepository.findById(2).map(_.gender)
	UserRepository.findById(2).flatMap(_.gender)
                                                  
 //options works as lists
 val names:List[Option[String]] = Some("Name1")::None::Some("Name2")::Nil
 names.map(_.map(_.toUpperCase))
 names.flatMap(_.map(_.toUpperCase))
 
 //filtering
 UserRepository.findById(1).filter(_.age > 2)
 UserRepository.findById(2).filter(_.age > 2)
 UserRepository.findById(3).filter(_.age > 2)
 
 //For comprehensions
 for{
 	user <- UserRepository.findById(2)
 	gender <- user.gender
 }yield gender

 for{
 	user <- UserRepository.findAll
 	gender <- user.gender
 }yield gender

 for{
 	User(_, _, _, _, Some(gender)) <- UserRepository.findAll
 }yield gender
 
 //chaining options
 case class Resource(content: String)
 val resInDir: Option[Resource] = None
 val resInPath: Option[Resource] = Some(Resource("Content in path"))
 val resource = resInDir orElse resInPath
}