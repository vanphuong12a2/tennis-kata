package player

import domain.PlayerPosition
import domain.PlayerPosition.PlayerPosition

case class Player(name: String) {
  var position: PlayerPosition = _

  def setPosition(positionIndex: Int): Unit = {
    this.position = positionIndex match {
      case 0 => PlayerPosition.ONE
      case 1 => PlayerPosition.TWO
    }
  }

  override def toString: String = name
}

object Player {
  implicit def stringToPlayer(name: String): Player = Player(name)
}
