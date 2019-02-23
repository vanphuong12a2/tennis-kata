package score

import domain.{Player, TieBreakPoint}

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

  override def toString: String = s"Advantage $player"
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


object EmptyTieBreakGameScore extends GameScore {
  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore =
    TieBreakGameScore(0, 0).update(scoredPlayer, scoredPlayerPosition)

  override def toString: String = ""
}


case class TieBreakGameScore(point1: TieBreakPoint, point2: TieBreakPoint) extends GameScore {

  private val MINIMUM_POINTS_TO_WIN: TieBreakPoint = 7
  private val MINIMUM_MARGIN_TO_WIN: TieBreakPoint = 2

  override def update(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore = {
    val updatedScore = scoredPlayerPosition match {
      case 1 => TieBreakGameScore(point1 + 1, point2)
      case 2 => TieBreakGameScore(point1, point2 + 1)
    }

    if (updatedScore.isTieBreakFinished) return EmptyGameScore
    updatedScore
  }

  private def isTieBreakFinished: Boolean = {
    val hasPlayer1Won = point1 >= MINIMUM_POINTS_TO_WIN && point1 - point2 >= MINIMUM_MARGIN_TO_WIN
    val hasPlayer2Won = point2 >= MINIMUM_POINTS_TO_WIN && point2 - point1 >= MINIMUM_MARGIN_TO_WIN
    hasPlayer1Won || hasPlayer2Won
  }

  override def toString: String = s"$point1-$point2"
}