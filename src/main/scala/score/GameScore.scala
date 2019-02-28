package score

import domain.PlayerPosition.{ONE, TWO}
import domain.TieBreakPoint
import player.Player

sealed trait GameScore {
  def update(scoredPlayer: Player): GameScore

  def hasGameFinished: Boolean = this == EmptyGameScore

  override def toString: String = ""
}

object EmptyGameScore extends NormalGameScore(ZERO, ZERO)

case class NormalGameScore(point1: GamePoint, point2: GamePoint) extends GameScore {
  override def update(scoredPlayer: Player): GameScore = {
    val updatedScore = scoredPlayer.position match {
      case ONE => NormalGameScore(point1.next(), point2)
      case TWO => NormalGameScore(point1, point2.next())
    }

    updatedScore match {
      case NormalGameScore(FORTY, FORTY) => DeuceGameScore
      case NormalGameScore(GAME, _) | NormalGameScore(_, GAME) => EmptyGameScore
      case _ => updatedScore
    }
  }

  override def toString: String = s"$point1-$point2"
}

object DeuceGameScore extends GameScore {
  override def update(scoredPlayer: Player): GameScore = {
    AdvantageGameScore(scoredPlayer)
  }

  override def toString: String = "Deuce"
}

case class AdvantageGameScore(player: Player) extends GameScore {
  override def update(scoredPlayer: Player): GameScore = {
    if (scoredPlayer == player) return EmptyGameScore
    DeuceGameScore
  }

  override def toString: String = s"Advantage $player"
}

object EmptyTieBreakGameScore extends TieBreakGameScore(0, 0)

case class TieBreakGameScore(point1: TieBreakPoint, point2: TieBreakPoint) extends GameScore {

  private val MINIMUM_POINTS_TO_WIN: TieBreakPoint = 7
  private val MINIMUM_MARGIN_TO_WIN: TieBreakPoint = 2

  override def update(scoredPlayer: Player): GameScore = {
    val updatedScore = scoredPlayer.position match {
      case ONE => TieBreakGameScore(point1 + 1, point2)
      case TWO => TieBreakGameScore(point1, point2 + 1)
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

object GameScore {
  def initialGameScore: GameScore = EmptyGameScore

  def initialTieBreakGameScore: GameScore = EmptyTieBreakGameScore
}