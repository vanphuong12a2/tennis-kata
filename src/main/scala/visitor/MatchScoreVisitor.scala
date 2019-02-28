package visitor

import score.{GamePoint, GameScore, MatchScore, SetScore}

trait MatchScoreVisitor {
  def visit(matchScore: MatchScore)

  def visit(setScore: SetScore)

  def visit(gameScore: GameScore)

  def visit(gamePoint: GamePoint)
}
