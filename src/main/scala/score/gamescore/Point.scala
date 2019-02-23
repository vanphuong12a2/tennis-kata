package score.gamescore

sealed trait Point {
  def next(): Point
}

case object ZERO extends Point {
  val value = 0

  def next(): Point = FIFTEEN

  override def toString: String = "0"
}

case object FIFTEEN extends Point {
  val value = 1

  def next(): Point = THIRTY

  override def toString: String = "15"
}

case object THIRTY extends Point {
  val value = 2

  def next(): Point = FORTY

  override def toString: String = "30"
}

case object FORTY extends Point {
  val value = 3

  def next(): Point = GAME_POINT

  override def toString: String = "40"
}

case object GAME_POINT extends Point {
  val value = 4

  def next(): Point = GAME_POINT
}