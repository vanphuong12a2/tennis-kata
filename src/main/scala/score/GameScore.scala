package score

import domain.PlayerPosition.{ONE, TWO}
import domain.TieBreakPoint
import player.Player
import visitor.{MatchScorePart, MatchScoreVisitor}

sealed trait GameScore extends MatchScorePart{
  def update(scoredPlayer: Player): GameScore

  def hasGameFinished: Boolean = this == EmptyGameScore

  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
  }
}

object EmptyGameScore extends NormalGameScore(ZERO, ZERO) {
  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
  }
}

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

  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
    point1.accept(matchScoreVisitor)
    point2.accept(matchScoreVisitor)
  }
}

object DeuceGameScore extends GameScore {
  override def update(scoredPlayer: Player): GameScore = {
    AdvantageGameScore(scoredPlayer)
  }
}

case class AdvantageGameScore(player: Player) extends GameScore {
  override def update(scoredPlayer: Player): GameScore = {
    if (scoredPlayer == player) return EmptyGameScore
    DeuceGameScore
  }
}

object EmptyTieBreakGameScore extends TieBreakGameScore(0, 0) {
  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
  }
}

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
}

object GameScore {
  def initialGameScore: GameScore = EmptyGameScore

  def initialTieBreakGameScore: GameScore = EmptyTieBreakGameScore
}