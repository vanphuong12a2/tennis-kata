trait IGameScore {
  def next(scoredPlayer: Int): IGameScore

  def format(): String
}

case class EmptyGameScore() extends IGameScore {
  override def format(): String = ""

  override def next(scoredPlayer: Int): IGameScore = scoredPlayer match {
    case 1 => GameScore(15, 0)
    case 2 => GameScore(0, 15)
  }
}

case class GameScore(score1: Int, score2: Int) extends IGameScore {
  override def format(): String = s"$score1-$score2"

  override def next(scoredPlayer: Int): IGameScore = scoredPlayer match {
    case 1 => GameScore(nextScore(score1), score2)
    case 2 => GameScore(score1, nextScore(score2))
  }

  private def nextScore(score: Int): Int = {
    score match {
      case 0 => 15
      case 15 => 30
      case 30 => 40
    }
  }
}