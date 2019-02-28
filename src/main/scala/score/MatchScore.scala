package score

import player.Player
import visitor.{MatchScorePart, MatchScoreVisitor}

case class MatchScore(setScore: SetScore, gameScore: GameScore) extends MatchScorePart {

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

  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
    setScore.accept(matchScoreVisitor)
    gameScore.accept(matchScoreVisitor)
  }
}


object MatchScore {
  def initialMatchScore: MatchScore = MatchScore(SetScore.initialSetScore, GameScore.initialGameScore)
}
