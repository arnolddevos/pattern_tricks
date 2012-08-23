# [Stupid] Pattern Matching Tricks

![Trick](https://github.com/etorreborre/pattern_tricks/raw/master/dog.jpeg)

Arnold deVos

    @a4dev
    adv-contact-trick@langdale.com.au

---
# This is pattern matching:

```scala
case class Song( n: Int, thing: String)
val s = Song( 10, "green bottles")

val Song(10, x) = s
val y = s match { case Song(10, thing) => thing }
val z = for( Song(10, thing) <- Some(s) ) yield thing 
```
---
# This is a partial function:
```scala
val pf: PartialFunction[Song, String] = { 
  case Song(10, thing) => thing 
  case Song(_, thing) => thing + ", but not 10 of them"
}

val a = pf(s)
```
---
# Here is an extractor:
```scala
object ExtractCount {
  def unapply(s: Song) = Some(s.n)
}

val b = s match { case ExtractCount(n) => n + " things" }
```
---
# Here is an optional function:

```scala
object countOption extends (Song => Option[Int]) {
  def apply(s: Song) = Some(s.n)
}
   
val c = countOption(s) map { _ + " things" } getOrElse { throw new MatchError(s) }
```
---
# Different forms, convertable:

![relationships](https://github.com/etorreborre/pattern_tricks/raw/master/relationships.png)

---
# Equipment needed:

![frisbee patent](https://github.com/etorreborre/pattern_tricks/raw/master/frisbee2.jpg)

```scala
class Extractor[A,B]( f: A => Option[B] ) { 
  def unapply( a: A ) = f(a) 
}
```
---
# Equipment needed:

![retriever](https://github.com/etorreborre/pattern_tricks/raw/master/dog2.jpeg)

```scala
def pattern[B](pf: PartialFunction[Name,B]) = 
  new Extractor(pf.lift)
```
---
# Onto the tricks ...

---
# Come again?

## [http://notes.langdale.com.au](http://notes.langdale.com.au/Querying_a_Dataset_with_Scala_s_Pattern_Matching.html)
