package score

case class MatchScore(score1: Int, score2: Int) {

  def next(scoredPlayer: Int): MatchScore = scoredPlayer match {
    case 1 => MatchScore(score1 + 1, score2)
    case 2 => MatchScore(score1, score2 + 1)
  }

  override def toString: String = s"$score1-$score2"
}
