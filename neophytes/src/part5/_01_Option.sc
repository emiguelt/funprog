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
	
	//Mapping an option
		
	//flapMap
	UserRepository.findById(2).map(_.gender)  //> res0: Option[Option[String]] = Some(Some(male))
	UserRepository.findById(2).flatMap(_.gender)
                                                  //> res1: Option[String] = Some(male)
                                                  
 //options works as lists
 val names:List[Option[String]] = Some("Name1")::None::Some("Name2")::Nil
                                                  //> names  : List[Option[String]] = List(Some(Name1), None, Some(Name2))
 names.map(_.map(_.toUpperCase))                  //> res2: List[Option[String]] = List(Some(NAME1), None, Some(NAME2))
 names.flatMap(_.map(_.toUpperCase))              //> res3: List[String] = List(NAME1, NAME2)
 
 //filtering
 UserRepository.findById(1).filter(_.age > 2)     //> res4: Option[part5._01_Option.User] = None
 UserRepository.findById(2).filter(_.age > 2)     //> res5: Option[part5._01_Option.User] = None
 UserRepository.findById(3).filter(_.age > 2)     //> res6: Option[part5._01_Option.User] = Some(User(3,name3,last3,3,Some(female
                                                  //| )))
 
 //comprehensions
 for{
 	user <- UserRepository.findById(2)
 	gender <- user.gender
 }yield gender                                    //> res7: Option[String] = Some(male)

 for{
 	user <- UserRepository.findAll
 	gender <- user.gender
 }yield gender                                    //> res8: Iterable[String] = List(male, female)

 for{
 	User(_, _, _, _, Some(gender)) <- UserRepository.findAll
 }yield gender                                    //> res9: Iterable[String] = List(male, female)
 
 //chaining options
 case class Resource(content: String)
 val resInDir: Option[Resource] = None            //> resInDir  : Option[part5._01_Option.Resource] = None
 val resInPath: Option[Resource] = Some(Resource("Content in path"))
                                                  //> resInPath  : Option[part5._01_Option.Resource] = Some(Resource(Content in p
                                                  //| ath))
 val resource = resInDir orElse resInPath         //> resource  : Option[part5._01_Option.Resource] = Some(Resource(Content in pa
                                                  //| th))
}