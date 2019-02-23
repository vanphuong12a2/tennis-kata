package score

import player.Player

sealed trait GameScore {
  def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore
}


object EmptyGameScore extends GameScore {
  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore =
    NormalGameScore(ZERO, ZERO).update(scoredPlayer, scoredPlayerPosition)

  override def toString: String = ""
}


object DeuceGameScore extends GameScore {
  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore = {
    AdvantageGameScore(scoredPlayer)
  }

  override def toString: String = "Deuce"
}


case class AdvantageGameScore(player: Player) extends GameScore {
  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore = {
    if (scoredPlayer == player) return EmptyGameScore
    DeuceGameScore
  }

  override def toString: String = s"Advantage ${player.name}"
}


case class NormalGameScore(point1: Point, point2: Point) extends GameScore {
  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore = {
    val updatedScore = scoredPlayerPosition match {
      case 1 => NormalGameScore(point1.next(), point2)
      case 2 => NormalGameScore(point1, point2.next())
    }

    updatedScore match {
      case NormalGameScore(FORTY, FORTY) => DeuceGameScore
      case NormalGameScore(GAME_POINT, _) | NormalGameScore(_, GAME_POINT) => EmptyGameScore
      case _ => updatedScore
    }
  }

  override def toString: String = s"$point1-$point2"
}
