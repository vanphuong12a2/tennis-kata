class Match(player1: String, player2: String) {

  private var score: String = "0-0"

  def currentScore(): String = score

  def pointWonBy(player: String): Unit = {
    if (player == player1) pointWonByPlayer(1)
    if (player == player2) pointWonByPlayer(2)
  }

  private def pointWonByPlayer(index: Int): Unit = {
    (score, index) match {
      case ("0-0", 1) => score = "0-0, 15-0"
      case ("0-0", 2) => score = "0-0, 0-15"
      case ("0-0, 0-15", 1) => score = "0-0, 15-15"
      case ("0-0, 15-0", 2) => score = "0-0, 15-15"
    }
  }
}