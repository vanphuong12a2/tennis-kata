package score

import domain.PlayerPosition.{ONE, TWO}
import domain.SetPoint
import player.Player
import visitor.{MatchScorePart, MatchScoreVisitor}

case class SetScore(point1: SetPoint, point2: SetPoint) extends MatchScorePart {

  private val MINIMUM_POINTS_TO_WIN: SetPoint = 6
  private val MINIMUM_MARGIN_TO_WIN: SetPoint = 2

  def update(scoredPlayer: Player): SetScore = scoredPlayer.position match {
    case ONE => SetScore(point1 + 1, point2)
    case TWO => SetScore(point1, point2 + 1)
  }

  def isSetFinished: Boolean = {
    val hasPlayer1Won = point1 >= MINIMUM_POINTS_TO_WIN && point1 - point2 >= MINIMUM_MARGIN_TO_WIN
    val hasPlayer2Won = point2 >= MINIMUM_POINTS_TO_WIN && point2 - point1 >= MINIMUM_MARGIN_TO_WIN
    hasPlayer1Won || hasPlayer2Won
  }

  def isTieBreak: Boolean = point1 == MINIMUM_POINTS_TO_WIN && point2 == MINIMUM_POINTS_TO_WIN

  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
  }
}

object SetScore {
  def initialSetScore: SetScore = SetScore(0, 0)
}