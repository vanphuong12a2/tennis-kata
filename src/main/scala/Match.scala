class Match(player1: String, player2: String) {

  private var score: Score = Score("0-0")

  def currentScore(): String = score.format()

  def pointWonBy(player: String): Unit = {
    if (player == player1) score = score.next(1)
    if (player == player2) score = score.next(2)
  }
}