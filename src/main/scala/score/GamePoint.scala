package score

import visitor.{MatchScorePart, MatchScoreVisitor}

sealed trait GamePoint extends MatchScorePart {
  def next(): GamePoint

  override def accept(matchScoreVisitor: MatchScoreVisitor): Unit = {
    matchScoreVisitor.visit(this)
  }
}

case object ZERO extends GamePoint {
  def next(): GamePoint = FIFTEEN
}

case object FIFTEEN extends GamePoint {
  def next(): GamePoint = THIRTY
}

case object THIRTY extends GamePoint {
  def next(): GamePoint = FORTY
}

case object FORTY extends GamePoint {
  def next(): GamePoint = GAME
}

case object GAME extends GamePoint {
  def next(): GamePoint = GAME
}