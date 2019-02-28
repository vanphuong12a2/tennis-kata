import player.Player
import score.MatchScore
import visitor.MatchScorePrinter

class Match(players: Player*) {

  private var currentScore: MatchScore = MatchScore.initialMatchScore

  def score(): String = {
    val printer = new MatchScorePrinter
    currentScore.accept(printer)
    printer.score()
  }

  def pointWonBy(player: Player): Unit = {
    player.setPosition(players.indexOf(player))
    currentScore = currentScore.update(player)
  }
}