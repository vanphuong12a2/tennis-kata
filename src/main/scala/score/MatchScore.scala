package score

case class MatchScore(setScore: SetScore, gameScore: GameScore) {

  def update(scoredPlayerPosition: Int): MatchScore = {
    val updatedGameScore = gameScore.update(scoredPlayerPosition)
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
