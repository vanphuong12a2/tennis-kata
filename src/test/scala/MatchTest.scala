import org.scalatest.MustMatchers

class MatchTest extends org.scalatest.FunSpec with MustMatchers {

  it("initial match score is 0-0") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.currentScore() must be("0-0")
  }

  it("should update match score when player 1 scores a point") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 1")
    aMatch.currentScore() must be("0-0, 15-0")
  }

  it("should update match score when player 2 scores a point") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 2")
    aMatch.currentScore() must be("0-0, 0-15")
  }

  it("should update match score when both players score a point 1->2") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 1")
    aMatch.pointWonBy("player 2")
    aMatch.currentScore() must be("0-0, 15-15")
  }


  it("should update match score when both players score a point 2->1") {
    val aMatch = new Match("player 1", "player 2")
    aMatch.pointWonBy("player 2")
    aMatch.pointWonBy("player 1")
    aMatch.currentScore() must be("0-0, 15-15")
  }

}
