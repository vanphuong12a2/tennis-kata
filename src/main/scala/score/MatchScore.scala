package score

import score.gamescore.{EmptyGameScore, GameScore}

case class MatchScore(matchScore: String, gameScore: GameScore = EmptyGameScore()) {

  def format(): String = gameScore match {
    case _: EmptyGameScore => matchScore
    case _ => s"$matchScore, ${gameScore.format()}"
  }

  def next(scoredPlayer: Int): MatchScore = {
    MatchScore(matchScore, gameScore.next(scoredPlayer))
  }
}
