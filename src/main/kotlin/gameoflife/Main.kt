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
    // Comments represent the default value
    seedSize = 100     // 5
    numRows = 580      // 45
    numCols = 750      // 100

//    aliveString = "\uD83D\uDD25"    // @
//    deadString = "-"                // .
    backgroundOn = true             // false

    updateDelayMS = Settings.SpeedsInMS.FAST.speed    // MEDIUM (150ms)
//    seed = seedList[]                                 // Random Seeds

    if (testing) maxIterations = 1 // No limit
  }

  Game.mainLoop()
}
