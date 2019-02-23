package score.gamescore

case class EmptyGameScore() extends GameScore {
  override def format(): String = ""

  override def next(scoredPlayer: Int): GameScore = scoredPlayer match {
    case 1 => NormalGameScore(15, 0)
    case 2 => NormalGameScore(0, 15)
  }
}
