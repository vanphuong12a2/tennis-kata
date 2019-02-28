package score

import player.Player

case class MatchScore(setScore: SetScore, gameScore: GameScore) {

  def update(scoredPlayer: Player): MatchScore = {
    if (setScore.isSetFinished) return this

    val updatedGameScore = gameScore.update(scoredPlayer)
    val updatedSetScore = getUpdatedSetScore(scoredPlayer, updatedGameScore)

    getUpdatedMatchScore(updatedSetScore, updatedGameScore)
  }

  private def getUpdatedMatchScore(updatedSetScore: SetScore, updatedGameScore: GameScore): MatchScore = {
    if (updatedSetScore.isTieBreak && updatedGameScore.hasGameFinished) {
      return MatchScore(updatedSetScore, GameScore.initialTieBreakGameScore)
    }
    MatchScore(updatedSetScore, updatedGameScore)
  }

  private def getUpdatedSetScore(scoredPlayer: Player, updatedGameScore: GameScore): SetScore = {
    if (updatedGameScore.hasGameFinished) {
      return setScore.update(scoredPlayer)
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
