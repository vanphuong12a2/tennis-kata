package score

sealed trait GamePoint {
  def next(): GamePoint
}

case object ZERO extends GamePoint {
  def next(): GamePoint = FIFTEEN

  override def toString: String = "0"
}

case object FIFTEEN extends GamePoint {
  def next(): GamePoint = THIRTY

  override def toString: String = "15"
}

case object THIRTY extends GamePoint {
  def next(): GamePoint = FORTY

  override def toString: String = "30"
}

case object FORTY extends GamePoint {
  def next(): GamePoint = GAME

  override def toString: String = "40"
}

case object GAME extends GamePoint {
  def next(): GamePoint = GAME
}