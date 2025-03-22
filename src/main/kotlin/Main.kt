package main.kotlin

import main.kotlin.Game.GAME_NAME
import main.kotlin.Game.Settings
import main.kotlin.Game.mainLoop

fun main() {
  Settings.seedSize = 4 // 3
//  Settings.seed = "0111011101000100" // Board.generateSeed()
//  Settings.stretchToTerminal = true // false
  Settings.setBackground(
//    false,
//    ' '
    true,
    '\''
  )
//  Settings.aliveChar = // '@'
//  Settings.deadChar = // '.'
//  Settings.numberOfIterations = // -1
//  Settings.height = // 45
//  Settings.width = // 100

  println("Seed: ${Settings.seed}")
  println("Starting $GAME_NAME")
  if (Settings.stretchToTerminal) {
    val termSize = HelperFunctions.getTermSize()
    Settings.height = termSize.first
    Settings.width = termSize.second
  }

  Board.initBoard()
  mainLoop(Settings.numberOfIterations)
}
