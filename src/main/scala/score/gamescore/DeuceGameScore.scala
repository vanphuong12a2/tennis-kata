package score.gamescore

case class DeuceGameScore() extends GameScore {
  override def format(): String = "Deuce"

  override def next(scoredPlayer: Int): GameScore = {
    EmptyGameScore()
  }
}
