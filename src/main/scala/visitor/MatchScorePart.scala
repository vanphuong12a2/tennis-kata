package visitor

trait MatchScorePart {
  def accept(matchScoreVisitor: MatchScoreVisitor)
}
