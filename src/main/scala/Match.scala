import player.Player
import score.{EmptyGameScore, MatchScore, SetScore}

class Match(player1: Player, player2: Player) {

  private var currentScore: MatchScore = MatchScore(SetScore(0, 0), EmptyGameScore)

  def score(): String = currentScore.toString

  def pointWonBy(player: Player): Unit = {
    if (player == player1) currentScore = currentScore.update(player1, 1)
    if (player == player2) currentScore = currentScore.update(player2, 2)
  }
}