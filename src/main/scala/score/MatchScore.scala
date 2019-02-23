package score

import player.Player

case class MatchScore(setScore: SetScore, gameScore: GameScore) {

  def update(scoredPlayer: Player, scoredPlayerPosition: Int): MatchScore = {
    if (setScore.isSetFinished) return this

    val updatedGameScore = getUpdatedGameScore(scoredPlayer, scoredPlayerPosition)
    val updatedSetScore = getUpdatedSetScore(scoredPlayerPosition, updatedGameScore)

    MatchScore(updatedSetScore, updatedGameScore)
  }

  private def getUpdatedSetScore(scoredPlayerPosition: Int, updatedGameScore: GameScore): SetScore = {
    if (updatedGameScore == EmptyGameScore) {
      return setScore.update(scoredPlayerPosition)
    }
    setScore
  }

  private def getUpdatedGameScore(scoredPlayer: Player, scoredPlayerPosition: Int): GameScore = {
    if(setScore.isTieBreak && gameScore == EmptyGameScore) {
      return EmptyTieBreakGameScore.update(scoredPlayer, scoredPlayerPosition)
    }
    gameScore.update(scoredPlayer, scoredPlayerPosition)
  }

  override def toString: String = gameScore match {
    case EmptyGameScore => setScore.toString
    case _ => s"$setScore, $gameScore"
  }
}
