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
  val user1 = UserRepository.findById(1)          //> user1  : Option[part5._01_Option.User] = Some(User(1,name1,last1,1,None))
  if(user1.isDefined){
  	println(user1)
  }                                               //> Some(User(1,name1,last1,1,None))
  
  //default value
  println(user1.get.gender.getOrElse("No gender defined"))
                                                  //> No gender defined
  
  //pattern matching
  UserRepository.findAll.foreach(_.gender match{
  	case Some(gender) => println("Gender: " + gender)
  	case None => println("No gender defined")
  })                                              //> No gender defined
                                                  //| Gender: male
                                                  //| Gender: female


	//side effect
	UserRepository.findById(1).foreach(user => println("Name: " + user.firstName))
                                                  //> Name: name1
	//will print nothing
	UserRepository.findById(5).foreach(user => println("Name: " + user.firstName))
}