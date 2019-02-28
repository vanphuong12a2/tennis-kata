package visitor

import score._

class MatchScorePrinter extends MatchScoreVisitor {

  var setScoreString: String = ""
  var gameScoreString: String = ""

  override def visit(matchScore: MatchScore): Unit = {}

  override def visit(setScore: SetScore): Unit = {
    setScoreString = s"${setScore.point1}-${setScore.point2}"
  }

  override def visit(gameScore: GameScore): Unit = {
    gameScoreString = gameScore match {
      case EmptyGameScore | EmptyTieBreakGameScore => ""
      case TieBreakGameScore(point1, point2) => s"$point1-$point2"
      case DeuceGameScore => "Deuce"
      case AdvantageGameScore(player) => s"Advantage $player"
      case _ => ""
    }
  }

  override def visit(gamePoint: GamePoint): Unit = {
    if(gameScoreString != "") gameScoreString += "-"
    gamePoint match {
      case ZERO => gameScoreString += "0"
      case FIFTEEN => gameScoreString += "15"
      case THIRTY => gameScoreString += "30"
      case FORTY => gameScoreString += "40"
    }
  }

  def score(): String = gameScoreString match {
    case "" => setScoreString.toString
    case _ => s"$setScoreString, $gameScoreString"
  }
}
