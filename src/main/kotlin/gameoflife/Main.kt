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

  val aliveChars = mapOf(
    "circle" to "\u25CF",
    "star" to "\u2726",
  )

  Settings.apply {
    // Comments represent the default value
    seedSize = 25     // 5
    numRows = 85      // 45
    numCols = 330     // 100

    aliveString = aliveChars.getValue("star")    // @
//    deadString = "-"                                // .
//    backgroundOn = true                             // false

    updateDelayMS = Settings.SpeedsInMS.MEDIUM.speed    // MEDIUM (150ms)
//    seed = seedList.getValue("idk")                 // Random Seeds

    if (testing) maxIterations = 1 // No limit
  }

  Game.mainLoop()
}
