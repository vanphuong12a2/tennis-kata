package score.gamescore

case object EmptyGameScore extends GameScore {

  override def next(scoredPlayer: Int): GameScore =
    NormalGameScore(ZERO, ZERO).next(scoredPlayer)

  override def toString: String = ""
}
