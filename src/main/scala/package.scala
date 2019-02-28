package object domain {
  type SetPoint = Int
  type TieBreakPoint = Int

  object PlayerPosition extends Enumeration {
    type PlayerPosition = Value
    val ONE, TWO = Value
  }
}