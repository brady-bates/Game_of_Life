package main.kotlin

import main.kotlin.Game.Settings
import main.kotlin.Game.mainLoop

fun main() {
  val testing =
//    true
    false

  if (testing) Settings.maxIterations = 1 // -1

  Settings.seedSize = 8 // 5
//  Settings.seed =
//  "1111110101001111111111011"
//  Settings.stretchToTerminal = true // false
  Settings.backgroundOn = true
//  Settings.numberOfIterations = 1 // -1
  Settings.height = 239 // 45
  Settings.width = 55 // 100
  Settings.updateDelayMS = 2
  mainLoop()
}
