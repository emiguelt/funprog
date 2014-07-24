package part1

object _02_MultipleExtractor {
	trait User{
		def name: String
		def score: Int
	}
	
	class FreeUser(val name: String, val score: Int, val upgradeProbabilty: Double) extends User
	class PremiumUser(val name: String, val score: Int) extends User
	
	object FreeUser{
		def unapply(user: FreeUser): Option[(String, Int, Double)] = Some((user.name, user.score, user.upgradeProbabilty))
	}
	
	object PremiumUser{
		def unapply(user: PremiumUser): Option[(String, Int)] = Some((user.name, user.score))
	}
	
	object premiumCandidate{
		def unapply(user: FreeUser): Boolean = user.upgradeProbabilty > 0.7d
	}
	
	def doMatch(user: User) = user match{
		case freeuser @ premiumCandidate() => "Start spam program"
		case _ => "Send regular newsletter"
	}                                         //> doMatch: (user: part1._02_MultipleExtractor.User)String
	
	doMatch(new FreeUser("Miguel", 1, 0.5d))  //> res0: String = Send regular newsletter
	doMatch(new FreeUser("Miguel", 1, 0.8d))  //> res1: String = Start spam program
	doMatch(new PremiumUser("Miguel", 1))     //> res2: String = Send regular newsletter
	
}