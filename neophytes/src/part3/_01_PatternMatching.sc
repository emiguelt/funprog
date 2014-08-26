package part3

object _01_PatternMatching {
  case class Player(name: String, score : Int)
  
  def message(player: Player) = player match{
  	case Player(_, score) if score > 1000 => "Get a job!"
  	case Player(name, _) => "Hello " + name
  }
  
  def printMsg(msg: String) = println(msg)
  
  //test
  printMsg(message(Player("Edwin", 10)))
  printMsg(message(Player("Edwin", 1001)))
  
  //--------------------
  // patterns in value definitions
  def getCurrentPlayer(): Player = Player("Edwin", 1)
  def printStr(str: String) = println(str)
  
  val player = getCurrentPlayer;
  printStr(player.name )
  
  val Player(name,_) = getCurrentPlayer()
  printStr(name)
  
  def getResult():(Int, Int) = (7, 1)
  
  val (deutsch, brazil) = getResult()
  printStr("Deutsch: " + deutsch + " X " + brazil + " Brazil")
 
 	//-------------------------
 	// patterns in comprehensions
	def getGameResults(): Seq[(String, Int)] = ("Edwin", 1)::("Miguel", 2)::("Jhon", 3)::Nil
	def hallOfFame1 = for{
		result <- getGameResults
		(name, score) = result
		if(score >= 2)
	}yield name
	
	hallOfFame1
	
	def hallOfFame2 = for{
		(name, score) <- getGameResults
		if(score >= 2)
	}yield name
	
	hallOfFame2
	
	val lists = List(1,2)::List.empty::List(3,4,5)::Nil
	for{
	list @ head :: _ <- lists
	}yield list.size
}