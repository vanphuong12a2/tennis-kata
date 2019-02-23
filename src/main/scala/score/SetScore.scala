package score

case class SetScore(score1: Int, score2: Int) {

  def update(scoredPlayerPosition: Int): SetScore = scoredPlayerPosition match {
    case 1 => SetScore(score1 + 1, score2)
    case 2 => SetScore(score1, score2 + 1)
  }

  def isSetFinished: Boolean = score1 + score2 != 11 && (score2 == 6 || score1 == 6)

  override def toString: String = s"$score1-$score2"
}