package gameoflife

import kotlin.random.Random

object Game {
  private const val GAME_NAME = "Conway's Game of Life"

  var currentTick = 0

  data object Settings {
    var seedSize: Int = 16
    var seed: String = generateSeed(seedSize)
    var backgroundOn: Boolean = false
    var aliveString: String = "@"
    var deadString: String = "."
    var maxIterations: Int = -1
    var updateDelayMS: Long = SpeedsInMS.MEDIUM.speed
    var numRows: Int = 45
    var numCols: Int = 85

    enum class SpeedsInMS(val speed: Long) {
      SLOW(400),
      MEDIUM(150),
      FAST(50),
      BLAZINGLYFAST(10)
    }

  }

  private fun generateSeed(seedSize: Int): String {
    val out = StringBuilder("")
    repeat( seedSize * seedSize ) {
      val rand = Random.nextInt(0, 2)
      out.append(if(rand == 1) "1" else Random.nextInt(0, 2))
    }
    return out.toString()
  }

  fun mainLoop() {
    println("Starting $GAME_NAME")
    Board.initializeBoard()
    val clearTerminalString = "\u001B[2J\u001B[H"
    while (Settings.maxIterations == -1 || currentTick < Settings.maxIterations) {
      val printString = clearTerminalString + Game + "\n" + Board.concatenateStringGrid(Board.convertToStringGrid())
      print(printString)
      System.out.flush()
      Board.calculateGridUpdate()
      Thread.sleep(Settings.updateDelayMS)
    }
  }

  override fun toString(): String {
    return (
      "Seed: ${Settings.seed}"
      + "\nCurrent Tick: ${currentTick}"
    )
  }

}
