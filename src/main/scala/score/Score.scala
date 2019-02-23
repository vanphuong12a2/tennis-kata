package score

import score.gamescore.{EmptyGameScore, GameScore}

case class Score(matchScore: MatchScore, gameScore: GameScore = EmptyGameScore) {

  def next(scoredPlayer: Int): Score = {
    val nextGameScore = gameScore.next(scoredPlayer)
    if (nextGameScore == EmptyGameScore) return Score(matchScore.next(scoredPlayer), nextGameScore)
    Score(matchScore, nextGameScore)
  }

  override def toString: String = gameScore match {
    case EmptyGameScore => matchScore.toString
    case _ => s"${matchScore}, ${gameScore}"
  }
}
