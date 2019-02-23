package score.gamescore

case object DeuceGameScore extends GameScore {

  override def next(scoredPlayer: Int): GameScore = {
    EmptyGameScore
  }

  override def toString: String = "Deuce"
}
