package ch03

/**
  * Created by EdwinT on 13/06/2017.
  */
object Ch03_Functional_Data_Structures {
  sealed trait List[+A]
  case object Nil extends  List[Nothing]
  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  object List{
    def sum(ints: List[Int]): Int = ints match {
      case Nil => 0
      case Cons(x,xs) => x + sum(xs)
    }

    def product(ints: List[Int]): Int = ints match {
      case Nil => 1
      case Cons(x, xs) => x * product(xs)
    }

    def apply[A](as: A*): List[A] =
      if(as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def tail[A](as: List[A]): List[A] = as match {
      case Nil => Nil
      case Cons(x, xs) => xs
    }

    def setHead[A](as: List[A], elem: A): List[A] = as match {
      case Nil => Cons(elem, Nil)
      case Cons(_, xs) => Cons(elem, xs)
    }

    def drop[A](as: List[A], n: Int): List[A] = {
      if(n <= 0) as
      else as match {
        case Nil => Nil
        case Cons(x, xs) => drop(xs, n-1)
      }
    }

    def dropWhile[A](as: List[A], p:A=>Boolean): List[A] = as match {
      case Nil => Nil
      case Cons(x, xs) => if(p(x)) dropWhile(xs, p) else as
    }

    def init[A](l: List[A]): List[A] = l match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(x, xs) => Cons(x, init(xs))
    }

    def foldRight[A,B] (as: List[A], z: B)(f: (A,B)=>B):B =
      as match {
        case Nil => z
        case Cons(x, xs) => f(x, foldRight(xs, z)(f))
      }

    def length[A](as: List[A]): Int =
      foldRight(as, 0)((_,b) => b + 1)

    def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B =
      as match {
        case Nil => z
        case Cons(x, xs) => foldLeft(xs, f(z,x))(f)
      }

    def sumL(ints: List[Int]): Int =
      foldLeft(ints, 0)((a,b) => a + b)
    def productL(ints: List[Int]): Int =
    foldLeft(ints, 1)((a,b) => a * b)
    def lengthL[A](as: List[A]): Int =
      foldLeft(as, 0)((a,b) => a + 1)
  }
}
