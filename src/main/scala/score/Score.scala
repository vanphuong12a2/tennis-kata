package score

import score.gamescore.{EmptyGameScore, GameScore}

case class Score(matchScore: MatchScore, gameScore: GameScore = EmptyGameScore()) {

  def format(): String = gameScore match {
    case _: EmptyGameScore => matchScore.format()
    case _ => s"${matchScore.format()}, ${gameScore.format()}"
  }

  def next(scoredPlayer: Int): Score = {
    val nextGameScore = gameScore.next(scoredPlayer)
    if (nextGameScore == EmptyGameScore()) return Score(matchScore.next(scoredPlayer), nextGameScore)
    Score(matchScore, nextGameScore)
  }
}
