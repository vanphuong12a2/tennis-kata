package score.gamescore

case class NormalGameScore(point1: Int, point2: Int) extends GameScore {
  override def format(): String = s"$point1-$point2"

  override def next(scoredPlayer: Int): GameScore = {
    val gameScore = nextGameScore(scoredPlayer)
    if (gameScore == NormalGameScore(40, 40)) return DeuceGameScore()
    gameScore
  }

  private def nextGameScore(scoredPlayer: Int): GameScore = {
    scoredPlayer match {
      case 1 =>
        if (point1 == 40) return EmptyGameScore()
        NormalGameScore(nextPoint(point1), point2)
      case 2 =>
        if (point2 == 40) return EmptyGameScore()
        NormalGameScore(point1, nextPoint(point2))
    }
  }

  private def nextPoint(score: Int): Int = {
    score match {
      case 0 => 15
      case 15 => 30
      case 30 => 40
    }
  }
}
