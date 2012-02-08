object Pattern {

  class Extractor[A,B](f: A => Option[B]) {
    def unapply( a: A) = f(a)
    def unapply[C]( ta: Traversable[A])(implicit g: Flattener[B,C]): Option[C] = g(ta.view.map(f))
  }

  trait Flattener[B,C] extends (Traversable[Option[B]] => Option[C])

  class DefaultFlattener {
    implicit def defaultFlattener[B] = new Flattener[B,Traversable[B]] {
      def apply( tb: Traversable[Option[B]]): Option[Traversable[B]] = nonEmpty(tb.flatten)
    }
  }

  object Flattener extends DefaultFlattener {
    implicit def flattenSets[E] = new Flattener[Set[E],Set[E]] {
      def apply( tb: Traversable[Option[Set[E]]]): Option[Set[E]] = nonEmpty(tb.flatten.flatten.toSet)
    }
  }

  def nonEmpty[T <: Traversable[_]]( t: T )= if( t isEmpty ) None else Some(t)   
  
  object & {
    def unapply[A](a: A) = Some(a, a)
  }
}
