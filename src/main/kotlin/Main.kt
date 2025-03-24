package main.kotlin

import main.kotlin.Game.Settings
import main.kotlin.Game.mainLoop

fun main() {
  val testing =
//    true
    false

  val seedList = mutableListOf(
    "1111110101001111111111011", // idk
    "1111011110010101110110010" // 3-cycle oscillation
  )

  if (testing) Settings.maxIterations = 1 // -1

  Settings.seedSize = 8 // 5
  Settings.seed = seedList[1]
//  Settings.stretchToTerminal = true // false
//  Settings.backgroundOn = true
//  Settings.numberOfIterations = 1 // -1
  Settings.height = 239 // 45
  Settings.width = 55 // 100
  Settings.updateDelayMS = 200
  mainLoop()
}
