package main.kotlin

import main.kotlin.Game.Settings
import main.kotlin.Game.mainLoop

fun main() {
  var testing =
//    true
    false

  if (testing) Settings.numberOfIterations = 1 // -1

  Settings.seedSize = 8 // 5
//  Settings.seed =
//  1101110001111110010011011
//  "0111110001001111001111001110100101010000010110011101010101100010"
//  "1010000010110101011100011"
//  Settings.stretchToTerminal = true // false
  Settings.setBackground(
    false,
    ' '
//    true,
//    '\''
  )
//  Settings.aliveChar = // '@'
//  Settings.deadChar = // '.'
//  Settings.numberOfIterations = 1 // -1
  Settings.height = 239 // 45
  Settings.width = 55 // 100
  Settings.updateSleepTimerMS = 10
  mainLoop()
}
