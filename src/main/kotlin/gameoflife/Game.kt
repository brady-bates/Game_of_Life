package gameoflife

import kotlin.random.Random

object Game {
  private const val GAME_NAME = "Conway's Game of Life"

  data object State {
    var currentTick = 0
    var numberOfIterations: Int = Settings.maxIterations
  }

  data object Settings {
    var seedSize: Int = 5
    var seed: String = generateSeed()
    var backgroundOn: Boolean = false
    var aliveString: String = "@"
    var deadString: String = "."
    var maxIterations: Int = -1
    var height: Int = 45
    var width: Int = 100
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

  private fun generateSeed(): String {
    val out = StringBuilder("")
    val seedSize = Settings.seedSize
    repeat( seedSize * seedSize ) {
      val rand = Random.nextInt(0, 2)
      out.append(if(rand == 1) "1" else Random.nextInt(0, 2))
    }
    return out.toString()
  }

  fun mainLoop() {
    println("Starting $GAME_NAME")
    Board.initializeBoard()
    while (State.numberOfIterations == -1 || State.currentTick < State.numberOfIterations) {
      println("\u001Bc" + Game + "\n" + Board.concatenateStringGrid(Board.convertToStringGrid()))
      System.out.flush()
      Board.calculateGridUpdate()
      Thread.sleep(Settings.updateDelayMS)
    }
  }

  override fun toString(): String {
    return (
      "Seed: ${Settings.seed}"
      + "\nCurrent Tick: ${State.currentTick}"
    )
  }

}
