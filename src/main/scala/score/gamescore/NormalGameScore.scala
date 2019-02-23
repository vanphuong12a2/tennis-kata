package score.gamescore

case class NormalGameScore(point1: Point, point2: Point) extends GameScore {

  override def next(scoredPlayer: Int): GameScore = {
    val gameScore = nextGameScore(scoredPlayer)
    if (gameScore == NormalGameScore(FORTY, FORTY)) return DeuceGameScore
    gameScore
  }

  override def toString: String = s"$point1-$point2"

  private def nextGameScore(scoredPlayer: Int): GameScore = {
    scoredPlayer match {
      case 1 =>
        if (point1 == FORTY) return EmptyGameScore
        NormalGameScore(point1.next(), point2)
      case 2 =>
        if (point2 == FORTY) return EmptyGameScore
        NormalGameScore(point1, point2.next())
    }
  }
}
