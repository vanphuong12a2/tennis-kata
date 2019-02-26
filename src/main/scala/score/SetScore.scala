package score

import domain.SetPoint

case class SetScore(point1: SetPoint, point2: SetPoint) {

  private val MINIMUM_POINTS_TO_WIN: SetPoint = 6
  private val MINIMUM_MARGIN_TO_WIN: SetPoint = 2

  def update(scoredPlayerPosition: Int): SetScore = scoredPlayerPosition match {
    case 1 => SetScore(point1 + 1, point2)
    case 2 => SetScore(point1, point2 + 1)
  }

  def isSetFinished: Boolean = {
    val hasPlayer1Won = point1 >= MINIMUM_POINTS_TO_WIN && point1 - point2 >= MINIMUM_MARGIN_TO_WIN
    val hasPlayer2Won = point2 >= MINIMUM_POINTS_TO_WIN && point2 - point1 >= MINIMUM_MARGIN_TO_WIN
    hasPlayer1Won || hasPlayer2Won
  }

  def isTieBreak: Boolean = point1 == MINIMUM_POINTS_TO_WIN && point2 == MINIMUM_POINTS_TO_WIN

  override def toString: String = s"$point1-$point2"
}

object SetScore {
  def initialSetScore: SetScore = SetScore(0,0)
}