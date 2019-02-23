package score

sealed trait Point {
  def next(): Point
}

case object ZERO extends Point {
  def next(): Point = FIFTEEN

  override def toString: String = "0"
}

case object FIFTEEN extends Point {
  def next(): Point = THIRTY

  override def toString: String = "15"
}

case object THIRTY extends Point {
  def next(): Point = FORTY

  override def toString: String = "30"
}

case object FORTY extends Point {
  def next(): Point = GAME_POINT

  override def toString: String = "40"
}

case object GAME_POINT extends Point {
  def next(): Point = GAME_POINT
}