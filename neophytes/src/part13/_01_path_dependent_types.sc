package part13

object _01_path_dependent_types {
  class Franchise(name: String) {
    case class Character(name: String)
    def createFanFictionWith(
      lovestruct: Character,
      objectOfDesire: Character): (Character, Character) = (lovestruct, objectOfDesire)
  }

  val starTrek = new Franchise("Star Trek")
  val starWars = new Franchise("Star Wars")

  val quark = starTrek.Character("Quark")
  val jadzia = starTrek.Character("Jadzia Dax")

  val luke = starWars.Character("Luke Skywalker")
  val yoda = starWars.Character("Yoda")
  starTrek.createFanFictionWith(quark, jadzia)
  starWars.createFanFictionWith(luke, yoda)
  //next will fail 'cause yoda is not from starTrek franchise
  // starTrek.createFanFictionWith(quark, yoda)

}