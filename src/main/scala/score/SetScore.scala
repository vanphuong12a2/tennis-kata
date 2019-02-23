package score

case class SetScore(score1: Int, score2: Int) {

  def update(scoredPlayerPosition: Int): SetScore = scoredPlayerPosition match {
    case 1 => SetScore(score1 + 1, score2)
    case 2 => SetScore(score1, score2 + 1)
  }

  override def toString: String = s"$score1-$score2"
}
