package score

trait GameScore {
  def update(scoredPlayerPosition: Int): GameScore
}

case object EmptyGameScore extends GameScore {
  override def update(scoredPlayer: Int): GameScore =
    NormalGameScore(ZERO, ZERO).update(scoredPlayer)

  override def toString: String = ""
}

case object DeuceGameScore extends GameScore {
  override def update(scoredPlayer: Int): GameScore = {
    EmptyGameScore
  }

  override def toString: String = "Deuce"
}

case class NormalGameScore(point1: Point, point2: Point) extends GameScore {

  override def update(scoredPlayerPosition: Int): GameScore = {
    scoredPlayerPosition match {
      case 1 => nextPoint1
      case 2 => nextPoint2
    }
  }

  private def nextPoint2 = {
    (point1, point2) match {
      case (FORTY, THIRTY) => DeuceGameScore
      case (_, FORTY) => EmptyGameScore
      case _ => NormalGameScore(point1, point2.next())
    }
  }

  private def nextPoint1 = {
    (point1, point2) match {
      case (THIRTY, FORTY) => DeuceGameScore
      case (FORTY, _) => EmptyGameScore
      case _ => NormalGameScore(point1.next(), point2)
    }
  }

  override def toString: String = s"$point1-$point2"
}
