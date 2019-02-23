package score.gamescore

case class NormalGameScore(score1: Int, score2: Int) extends GameScore {
  override def format(): String = s"$score1-$score2"

  override def next(scoredPlayer: Int): GameScore = {
    val gameScore = nextGameScore(scoredPlayer)
    if (gameScore == NormalGameScore(40, 40)) return DeuceGameScore()
    gameScore
  }

  private def nextGameScore(scoredPlayer: Int) = {
    scoredPlayer match {
      case 1 => NormalGameScore(nextScore(score1), score2)
      case 2 => NormalGameScore(score1, nextScore(score2))
    }
  }

  private def nextScore(score: Int): Int = {
    score match {
      case 0 => 15
      case 15 => 30
      case 30 => 40
    }
  }
}
