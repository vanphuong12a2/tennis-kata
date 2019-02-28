package score

import org.scalatest.{FunSpec, MustMatchers}
import visitor.MatchScorePrinter

class MatchScoreTest extends FunSpec with MustMatchers{

  it("empty game score") {
    val matchScore = MatchScore(SetScore(3,4), EmptyGameScore)

    val printer = new MatchScorePrinter()
    matchScore.accept(printer)

    printer.score must be ("3-4")
  }

  it("print Deuce") {
    val matchScore = MatchScore(SetScore(3,4), DeuceGameScore)

    val printer = new MatchScorePrinter()
    matchScore.accept(printer)

    printer.score must be ("3-4, Deuce")
  }

  it("print normal score") {
    val matchScore = MatchScore(SetScore(3,4), NormalGameScore(THIRTY, FIFTEEN))

    val printer = new MatchScorePrinter()
    matchScore.accept(printer)

    printer.score must be ("3-4, 30-15")
  }
}
