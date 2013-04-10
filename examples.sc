object examples {

import Pattern._
import Family._

def pattern[B](pf: PartialFunction[Name,B]) = new Extractor(pf.lift)
                                                  //> pattern: [B](pf: PartialFunction[Family.Name,B])Pattern.Extractor[Family.Nam
                                                  //| e,B]

val Parents = new Extractor(parents.get)          //> Parents  : Pattern.Extractor[Family.Name,Set[Family.Name]] = Pattern$Extract
                                                  //| or@3051c45f
val Children = new Extractor(children.get)        //> Children  : Pattern.Extractor[Family.Name,Set[Family.Name]] = Pattern$Extrac
                                                  //| tor@9aaf1f5


"Nalda" match {
  case Parents(p) => "The parents are: " + p
  case Children(c) => "The parents are unknown but children are: " + c
  case _ => "Don't know any relatives"
}                                                 //> res0: String = The parents are unknown but children are: Set(Pauline, Arnold
                                                  //| , Annette, Julie, Lisa)
def ex2 = parents.get("Julie") map { p => "The parents are: " + p } getOrElse {
  children.get("Julie") map { c => "Parents are unknown but children are: " + c } getOrElse {
    "Don't know any of Julie's relatives"
  }
}                                                 //> ex2: => String
ex2                                               //> res1: String = The parents are: Set(Nalda, John)

"Nalda" match {
  case Children(Children(c)) => "Nalda's grandchildren are: " + c
  case Children(_) => "Nalda has children but no grandchildren"
  case _ => "Nalda is childless"
}                                                 //> res2: String = Nalda's grandchildren are: Set(Zoe, Haley, James, David, Cand
                                                  //| y, Katey, Mel)

}