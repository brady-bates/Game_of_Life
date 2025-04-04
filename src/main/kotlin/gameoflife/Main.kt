package gameoflife

fun main() {

  val testing =
//    true
    false

  val seedList = mutableListOf(
    "1111110101001111111111011", // idk
    "1111011110010101110110010" // 3-cycle oscillation
  )

  Game.Settings.seedSize = 8 // 5
//  Settings.aliveString = "\uD83D\uDD25" // @
//  Settings.seed = seedList[1]
//  Settings.stretchToTerminal = true // false
  Game.Settings.backgroundOn = true // false
//  Settings.numberOfIterations = 1 // -1
  Game.Settings.height = 45 // 45
  Game.Settings.width = 100 // 100
  Game.Settings.updateDelayMS = Game.Settings.SpeedsInMS.MEDIUM.speedInMS // 400

  if (testing) Game.Settings.maxIterations = 1 // -1

  Game.mainLoop()
}
