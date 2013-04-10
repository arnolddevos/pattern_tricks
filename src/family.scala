object Family {

  type Name = String
  type Assoc = Map[Name, Set[Name]]


  val male = Set( "John", "David", "Peter", "Arnold", "Chris", "Craig", "James")
  val female = Set( "Julie", "Lisa", "Nalda", "Pauline", "Annette", "Mel", "Candy", "Zoe", "Haley", "Katey" )
  val basis = Map( 
    Set("Nalda", "John") -> Set("Julie", "Lisa", "Arnold", "Annette", "Pauline"), 
    Set("Julie", "Adrian") -> Set("David"),
    Set("Lisa", "Peter") -> Set("Mel", "Candy"),
    Set("Pauline", "Chris") -> Set( "James", "Zoe"),
    Set("Annette", "Craig") -> Set( "Haley", "Katey")
  )

  val children: Assoc = for( (ps, cs) <- basis; p <- ps ) yield p -> cs
  val parents: Assoc = for( (ps, cs) <- basis; c <- cs ) yield c -> ps

}
