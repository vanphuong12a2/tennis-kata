package player

case class Player(name: String)

object Player {
  implicit def stringToPlayer(name: String): Player = Player(name)
}