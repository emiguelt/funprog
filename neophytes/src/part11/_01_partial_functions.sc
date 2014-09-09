
package part11

object _01_partial_functions {
  case class Email(subject: String, text: String, sender: String, recipient: String)

  type EmailFilter = Email => Boolean

  //partial functions

  type IntPairPred = (Int, Int) => Boolean

  def sizeConstraint(pred: IntPairPred, n: Int, email: Email) = pred(email.text.size, n)

  val gt: IntPairPred = _ > _
  val ge: IntPairPred = _ >= _
  val lt: IntPairPred = _ < _
  val le: IntPairPred = _ <= _
  val eq: IntPairPred = _ == _

  val minimumSize: (Int, Email) => Boolean = sizeConstraint(ge, _: Int, _: Email)
  val maximumSize: (Int, Email) => Boolean = sizeConstraint(le, _: Int, _: Email)

  //test
  val mails = Set(Email(
    subject = "Abc",
    text = "cdef",
    sender = "hij@klm",
    recipient = "nop@qrs"))

  minimumSize(2, mails.head)
  maximumSize(2, mails.head)

  // from methods to functions: all parameters can be passed to the function
  val sizeConstraintFn: (IntPairPred, Int, Email) => Boolean = sizeConstraint _

  //Producing those EmailFilters
  val min20: EmailFilter = minimumSize(20, _: Email)
  min20(mails.head)

  //Spicing up your functions (Currying)
  def sizeConstraint2(pred: IntPairPred)(n: Int)(email: Email): Boolean = pred(email.text.size, n)
  val sizeConstraintFn2: IntPairPred => Int => Email => Boolean = sizeConstraint2 _

  val minimumSize2: Int => Email => Boolean = sizeConstraint2(ge)
  minimumSize2(2)(mails.head)

  //currying A
  val min20_2: Email => Boolean = minimumSize2(20)
  min20_2(mails.head)

  //equivalent to currying A
  val min20_3: EmailFilter = sizeConstraintFn2(ge)(20)
  min20_3(mails.head)

  //currying existing functions
  val sum: (Int, Int) => Int = _ + _
  val sumCurried: Int => Int => Int = sum.curried
  
  sum(1,2)
  sumCurried(1)(2)
}