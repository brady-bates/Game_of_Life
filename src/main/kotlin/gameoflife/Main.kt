package gameoflife

import gameoflife.Game.Settings

fun main() {

  val testing =
//    true
    false

  val seedList = mapOf(
    "idk" to "1111110101001111111111011",
    "3-cycle oscillation" to "1111011110010101110110010",
  )
  
  Settings.apply {
    // Comments are the default value
    seedSize = 8     // 5
    height = 45      // 45
    width = 100      // 100

//    aliveString = "\uD83D\uDD25"    // @
//    deadString = "-"                // .
//    backgroundOn = true             // false
//    stretchToTerminal = true        // false

    updateDelayMS = Settings.SpeedsInMS.FAST.speed    // MEDIUM (150ms)
    //    Settings.numberOfIterations = 1               // Run until finished
    //    seed = seedList[]                             // Random Seeds
  }


  if (testing) Settings.maxIterations = 1 // -1

  Game.mainLoop()
}
