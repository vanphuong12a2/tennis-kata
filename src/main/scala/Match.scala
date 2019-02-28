import player.Player
import score.MatchScore

class Match(players: Player*) {

  private var currentScore: MatchScore = MatchScore.initialMatchScore

  def score(): String = currentScore.toString

  def pointWonBy(player: Player): Unit = {
    player.setPosition(players.indexOf(player))
    currentScore = currentScore.update(player)
  }
}