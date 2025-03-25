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

  Settings.seedSize = 8 // 5
//  Settings.aliveString = "\uD83D\uDD25" // @
//  Settings.seed = seedList[1]
//  Settings.stretchToTerminal = true // false
//  Settings.backgroundOn = true // false
//  Settings.numberOfIterations = 1 // -1
  Settings.height = 400 // 45
  Settings.width = 240 // 100
  Settings.updateDelayMS = Game.Settings.SpeedsInMS.BLAZINGLYFAST.speedInMS // 400

  if (testing) Settings.maxIterations = 1 // -1

  mainLoop()
}
