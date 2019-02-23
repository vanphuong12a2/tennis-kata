package score.gamescore

trait GameScore {
  def next(scoredPlayer: Int): GameScore

  def format(): String
}