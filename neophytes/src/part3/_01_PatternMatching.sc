package part3

object _01_PatternMatching {
  case class Player(name: String, score : Int)
  
  def message(player: Player) = player match{
  	case Player(_, score) if score > 1000 => "Get a job!"
  	case Player(name, _) => "Hello " + name
  }                                               //> message: (player: part3._01_PatternMatching.Player)String
  
  def printMsg(msg: String) = println(msg)        //> printMsg: (msg: String)Unit
  
  //test
  printMsg(message(Player("Edwin", 10)))          //> Hello Edwin
  printMsg(message(Player("Edwin", 1001)))        //> Get a job!
  
  //--------------------
  // patterns in value definitions
  def getCurrentPlayer(): Player = Player("Edwin", 1)
                                                  //> getCurrentPlayer: ()part3._01_PatternMatching.Player
  def printStr(str: String) = println(str)        //> printStr: (str: String)Unit
  
  val player = getCurrentPlayer;                  //> player  : part3._01_PatternMatching.Player = Player(Edwin,1)
  printStr(player.name )                          //> Edwin
  
  val Player(name,_) = getCurrentPlayer()         //> name  : String = Edwin
  printStr(name)                                  //> Edwin
  
  def getResult():(Int, Int) = (7, 1)             //> getResult: ()(Int, Int)
  
  val (deutsch, brazil) = getResult()             //> deutsch  : Int = 7
                                                  //| brazil  : Int = 1
  printStr("Deutsch: " + deutsch + " X " + brazil + " Brazil")
                                                  //> Deutsch: 7 X 1 Brazil
 
 	//-------------------------
 	// patterns in comprehensions
	def getGameResults(): Seq[(String, Int)] = ("Edwin", 1)::("Miguel", 2)::("Jhon", 3)::Nil
                                                  //> getGameResults: ()Seq[(String, Int)]
	def hallOfFame1 = for{
		result <- getGameResults
		(name, score) = result
		if(score >= 2)
	}yield name                               //> hallOfFame1: => Seq[String]
	
	hallOfFame1                               //> res0: Seq[String] = List(Miguel, Jhon)
	
	def hallOfFame2 = for{
		(name, score) <- getGameResults
		if(score >= 2)
	}yield name                               //> hallOfFame2: => Seq[String]
	
	hallOfFame2                               //> res1: Seq[String] = List(Miguel, Jhon)
	
	val lists = List(1,2)::List.empty::List(3,4,5)::Nil
                                                  //> lists  : List[List[Int]] = List(List(1, 2), List(), List(3, 4, 5))
	for{
	list @ head :: _ <- lists
	}yield list.size                          //> res2: List[Int] = List(2, 3)
}