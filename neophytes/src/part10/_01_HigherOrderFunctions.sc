package part10

object _01_HigherOrderFunctions {
  case class Email(subject: String, text: String, sender: String, recipient: String)

  type EmailFilter = Email => Boolean

  def newMailsForUser(mails: Set[Email], f: EmailFilter) = mails.filter(f)

  //filters
  val sentByOneOf: Set[String] => EmailFilter = senders => email => senders.contains(email.sender)
  val notSentByOneOf: Set[String] => EmailFilter = senders => email => !senders.contains(email.sender)
  val minimumSize: Int => EmailFilter = n => email => email.text.size >= n
  val maximumSize: Int => EmailFilter = n => email => email.text.size <= n

  //test
  val mails = Set(Email(
    subject = "Abc",
    text = "cdef",
    sender = "hij@klm",
    recipient = "nop@qrs"))

  newMailsForUser(mails, sentByOneOf(Set("hij@klm")))
  newMailsForUser(mails, notSentByOneOf(Set("hij@klm")))
  newMailsForUser(mails, minimumSize(3))
  newMailsForUser(mails, minimumSize(5))
  newMailsForUser(mails, maximumSize(3))
  newMailsForUser(mails, maximumSize(5))

  // reusing functions
  type SizeChecker = Int => Boolean
  val sizeConstraint: SizeChecker => EmailFilter = f => email => f(email.text.size)
  // val sizeConstraint: (SizeChecker => EmailFilter) = ( f => (email => f(email.text.size)))

  val minimumSize2: Int => EmailFilter = n => sizeConstraint(_ >= n)
  val maximumSize2: Int => EmailFilter = n => sizeConstraint(_ <= n)

  newMailsForUser(mails, minimumSize2(3))
  newMailsForUser(mails, minimumSize2(5))
  newMailsForUser(mails, maximumSize2(3))
  newMailsForUser(mails, maximumSize2(5))

  // function composition
  def complement[A](predicate: A => Boolean) = (a: A) => !predicate(a)
  def notSentByOneOf2 = sentByOneOf andThen (complement(_))

  newMailsForUser(mails, notSentByOneOf2(Set("hij@klm")))

  //composing predicates
  def any[A](predicates: (A => Boolean)*): A => Boolean =
    a => predicates.exists(pred => pred(a))
  def none[A](predicates: (A => Boolean)*): A => Boolean = complement(any(predicates: _*))
  def every[A](predicates: (A => Boolean)*): A => Boolean = none(predicates.view.map(complement(_)): _*)

  var multifilter: EmailFilter = every(notSentByOneOf2(Set("hij@klm")), minimumSize2(3), maximumSize2(10))
  newMailsForUser(mails, multifilter)

  multifilter = every(sentByOneOf(Set("hij@klm")), minimumSize2(3), maximumSize2(10))
  newMailsForUser(mails, multifilter)

  //Composing a transformation pipeline
  val addMissingSubject = (email: Email) => if (email.text.isEmpty) email.copy(subject = "No subject") else email
  val checkSpelling = (email: Email) => email.copy(email.text.replaceAll("your", "you are"))
  val removeInappropriateLanguage = (email: Email) => email.copy(email.text.replaceAll("xxx", "*Censored*"))
  val addAdFooter = (email: Email) => email.copy(email.text + "\n Sent by Awesome email service")

  val pipeline = Function.chain(Seq(addMissingSubject, checkSpelling, removeInappropriateLanguage, addAdFooter))
  pipeline(mails.head)
}