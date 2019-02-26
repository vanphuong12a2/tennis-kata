package score

import domain.Player

case class MatchScore(setScore: SetScore, gameScore: GameScore) {

  def update(scoredPlayer: Player, scoredPlayerPosition: Int): MatchScore = {
    if (setScore.isSetFinished) return this

    val updatedGameScore = gameScore.update(scoredPlayer, scoredPlayerPosition)
    val updatedSetScore = getUpdatedSetState(scoredPlayerPosition, updatedGameScore)

    if (updatedSetScore.isTieBreak && updatedGameScore.hasGameFinished) {
      return MatchScore(updatedSetScore, GameScore.initialTieBreakGameScore)
    }
    MatchScore(updatedSetScore, updatedGameScore)
  }

  private def getUpdatedSetState(scoredPlayerPosition: Int, updatedGameScore: GameScore): SetScore = {
    if (updatedGameScore.hasGameFinished) {
      return setScore.update(scoredPlayerPosition)
    }
    setScore
  }

  override def toString: String = gameScore match {
    case EmptyGameScore => setScore.toString
    case _ => s"$setScore, $gameScore"
  }
}


object MatchScore {
  def initialMatchScore: MatchScore = MatchScore(SetScore.initialSetScore, GameScore.initialGameScore)
}
