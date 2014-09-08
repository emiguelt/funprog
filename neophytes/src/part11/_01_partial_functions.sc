
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
}