package part10

object _01_HigherOrderFunctions {
  case class Email(subject: String, text: String, sender: String, recipient: String)

  type EmailFilter = Email => Boolean

  def newMailsForUser(mails: Set[Email], f: EmailFilter) = mails.filter(f)
                                                  //> newMailsForUser: (mails: Set[part10._01_HigherOrderFunctions.Email], f: part
                                                  //| 10._01_HigherOrderFunctions.EmailFilter)scala.collection.immutable.Set[part1
                                                  //| 0._01_HigherOrderFunctions.Email]

//filters
  val sentByOneOf: Set[String] => EmailFilter = senders => email => senders.contains(email.sender)
                                                  //> sentByOneOf  : Set[String] => part10._01_HigherOrderFunctions.EmailFilter = 
                                                  //| <function1>
  val notSentByOneOf: Set[String] => EmailFilter = senders => email => !senders.contains(email.sender)
                                                  //> notSentByOneOf  : Set[String] => part10._01_HigherOrderFunctions.EmailFilter
                                                  //|  = <function1>
  val minimumSize: Int => EmailFilter = n => email => email.text.size >= n
                                                  //> minimumSize  : Int => part10._01_HigherOrderFunctions.EmailFilter = <functio
                                                  //| n1>
  val maximumSize: Int => EmailFilter = n => email => email.text.size <= n
                                                  //> maximumSize  : Int => part10._01_HigherOrderFunctions.EmailFilter = <functio
                                                  //| n1>

  //test
  val mails = Set(Email(
    subject = "Abc",
    text = "cdef",
    sender = "hij@klm",
    recipient = "nop@qrs"))                       //> mails  : scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Emai
                                                  //| l] = Set(Email(Abc,cdef,hij@klm,nop@qrs))
    
  newMailsForUser(mails, sentByOneOf(Set("hij@klm")))
                                                  //> res0: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email] 
                                                  //| = Set(Email(Abc,cdef,hij@klm,nop@qrs))
  newMailsForUser(mails, notSentByOneOf(Set("hij@klm")))
                                                  //> res1: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email] 
                                                  //| = Set()
  newMailsForUser(mails, notSentByOneOf(Set("hij@klm")))
                                                  //> res2: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email] 
                                                  //| = Set()
  newMailsForUser(mails, minimumSize(3))          //> res3: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email] 
                                                  //| = Set(Email(Abc,cdef,hij@klm,nop@qrs))
  newMailsForUser(mails, minimumSize(5))          //> res4: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email] 
                                                  //| = Set()
  newMailsForUser(mails, maximumSize(3))          //> res5: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email]
                                                  //|  = Set()
  newMailsForUser(mails, maximumSize(5))          //> res6: scala.collection.immutable.Set[part10._01_HigherOrderFunctions.Email]
                                                  //|  = Set(Email(Abc,cdef,hij@klm,nop@qrs))
}