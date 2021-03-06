import org.scalatest.MustMatchers

class MatchTest extends org.scalatest.FunSpec with MustMatchers {

  it("initial match score is 0-0") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.score() must be("0-0")
  }

  it("should update match score when player 1 scores a point") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 1")
    aMatch.score() must be("0-0, 15-0")
  }

  it("should update match score when player 2 scores a point") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 2")
    aMatch.score() must be("0-0, 0-15")
  }

  it("should update match score when both players score a point 1->2") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 1")
    aMatch.pointWonBy("player 2")
    aMatch.score() must be("0-0, 15-15")
  }


  it("should update match score when both players score a point 2->1") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 2")
    aMatch.pointWonBy("player 1")
    aMatch.score() must be("0-0, 15-15")
  }

  it("should update match score when player 1 scores 2 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 2)
    aMatch.score() must be("0-0, 30-0")
  }

  it("should update match score when player 2 scores 2 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 2)
    aMatch.score() must be("0-0, 0-30")
  }

  it("should update match score when player 1 scores 1 points & player 2 scores 2 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 1)
    pointsWonBy(aMatch, "player 2", 2)
    aMatch.score() must be("0-0, 15-30")
  }

  it("should update match score when player 1 scores 2 points & player 2 scores 1 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 2)
    pointsWonBy(aMatch, "player 2", 1)
    aMatch.score() must be("0-0, 30-15")
  }

  it("should update match score when player 1 scores 3 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    aMatch.score() must be("0-0, 40-0")
  }

  it("should update match score when player 1 scores 4 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 4)
    aMatch.score() must be("1-0")
  }

  it("should update match score when player 2 scores 4 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 4)
    aMatch.score() must be("0-1")
  }

  it("should update match score to Deuce when both players score 3 points") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    aMatch.score() must be("0-0, Deuce")
  }

  it("should update match score to Advantage when player 1 scores after Deuce") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("0-0, Advantage player 1")
  }

  it("should update match score to Advantage when player 2 scores after Deuce") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 2", 1)
    aMatch.score() must be("0-0, Advantage player 2")
  }

  it("should update match score to Deuce when player 2 scores after player 1 has advantage") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 1", 1)
    pointsWonBy(aMatch, "player 2", 1)
    aMatch.score() must be("0-0, Deuce")
  }

  it("should update match score to Deuce when player 1 scores after player 2 has advantage") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 2", 1)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("0-0, Deuce")
  }

  it("should update match score when player 1 scores after they has advantage") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 1", 5)
    aMatch.score() must be("1-0")
  }

  it("should update match score when player 2 scores after they has advantage") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 3)
    pointsWonBy(aMatch, "player 2", 3)
    pointsWonBy(aMatch, "player 2", 2)
    aMatch.score() must be("0-1")
  }

  it("should not update match score when player 1 wins with 6 games") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 24)
    pointsWonBy(aMatch, "player 2", 1)
    aMatch.score() must be("6-0")
  }

  it("should not update match score when player 2 wins with 6 games") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 24)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("0-6")
  }

  it("should update match score when player 1 wins with 6 games and player 2 wins 5 games ") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 20)
    pointsWonBy(aMatch, "player 1", 24)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("6-5, 15-0")
  }

  it("should update match score when player 2 wins with 6 games and player 1 wins 5 games ") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 20)
    pointsWonBy(aMatch, "player 2", 24)
    pointsWonBy(aMatch, "player 2", 1)
    aMatch.score() must be("5-6, 0-15")
  }

  it("should not update match score when player 1 wins with 7-5") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 20)
    pointsWonBy(aMatch, "player 1", 28)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("7-5")
  }

  it("should not update match score when player 2 wins with 7-5") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 20)
    pointsWonBy(aMatch, "player 2", 28)
    pointsWonBy(aMatch, "player 1", 1)
    aMatch.score() must be("5-7")
  }

  it("should update match score when player 1 scores after match score is tiebreak") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 20)
    pointsWonBy(aMatch, "player 2", 24)
    pointsWonBy(aMatch, "player 1", 5)
    aMatch.score() must be("6-6, 1-0")
  }

  it("should update match score when player 2 scores after match score is tiebreak") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 20)
    pointsWonBy(aMatch, "player 1", 24)
    pointsWonBy(aMatch, "player 2", 5)
    aMatch.score() must be("6-6, 0-1")
  }

  it("should not update match score when player 1 wins the tie-break") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 1", 20)
    pointsWonBy(aMatch, "player 2", 24)
    pointsWonBy(aMatch, "player 1", 11)
    aMatch.score() must be("7-6")
  }

  it("should not update match score when player 2 wins the tie-break") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 20)
    pointsWonBy(aMatch, "player 1", 24)
    pointsWonBy(aMatch, "player 2", 11)
    aMatch.score() must be("6-7")
  }

  it("should update match score when the margin in the tie-break is 1") {
    val aMatch = new Match("player 1", "player 2")
    pointsWonBy(aMatch, "player 2", 20)
    pointsWonBy(aMatch, "player 1", 24)
    pointsWonBy(aMatch, "player 2", 10)
    pointsWonBy(aMatch, "player 1", 7)
    aMatch.score() must be("6-6, 7-6")
  }

  private def pointsWonBy(aMatch: Match, player: String, points: Int): Unit = {
    (1 to points).foreach(
      _ => aMatch.pointWonBy(player)
    )
  }
}
