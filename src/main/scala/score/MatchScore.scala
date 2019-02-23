package score

import player.Player

case class MatchScore(setScore: SetScore, gameScore: GameScore) {

  def update(scoredPlayer: Player, scoredPlayerPosition: Int): MatchScore = {
    if (setScore.isSetFinished) return this
    val updatedGameScore = gameScore.update(scoredPlayer, scoredPlayerPosition)
    val updatedSetScore = getUpdatedSetScore(scoredPlayerPosition, updatedGameScore)

    MatchScore(updatedSetScore, updatedGameScore)
  }

  private def getUpdatedSetScore(scoredPlayerPosition: Int, updatedGameScore: GameScore): SetScore = {
    if (updatedGameScore == EmptyGameScore) {
      return setScore.update(scoredPlayerPosition)
    }
    setScore
  }

  override def toString: String = gameScore match {
    case EmptyGameScore => setScore.toString
    case _ => s"$setScore, $gameScore"
  }
}
