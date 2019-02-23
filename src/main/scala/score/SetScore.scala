package score

import domain.SetPoint

case class SetScore(score1: SetPoint, score2: SetPoint) {

  private val MINIMUM_POINTS_TO_WIN: SetPoint = 6
  private val MINIMUM_MARGIN_TO_WIN: SetPoint = 2

  def update(scoredPlayerPosition: Int): SetScore = scoredPlayerPosition match {
    case 1 => SetScore(score1 + 1, score2)
    case 2 => SetScore(score1, score2 + 1)
  }

  def isSetFinished: Boolean = {
    val hasPlayer1Won = score1 >= MINIMUM_POINTS_TO_WIN && score1 - score2 >= MINIMUM_MARGIN_TO_WIN
    val hasPlayer2Won = score2 >= MINIMUM_POINTS_TO_WIN && score2 - score1 >= MINIMUM_MARGIN_TO_WIN
    hasPlayer1Won || hasPlayer2Won
  }

  def isTieBreak: Boolean = score1 == 6 && score2 == 6

  override def toString: String = s"$score1-$score2"
}