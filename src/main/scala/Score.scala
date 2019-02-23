case class Score(matchScore: String, gameScore: IGameScore = EmptyGameScore()) {

  def format(): String = gameScore match {
    case _: EmptyGameScore => matchScore
    case _ => s"$matchScore, ${gameScore.format()}"
  }

  def next(scoredPlayer: Int): Score = {
    Score(matchScore, gameScore.next(scoredPlayer))
  }
}