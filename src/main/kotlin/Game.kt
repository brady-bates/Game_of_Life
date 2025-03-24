package main.kotlin

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
    var stretchToTerminal: Boolean = false
    var backgroundOn: Boolean = false
    var aliveChar: Char = '@'
    var deadChar: Char = '.'
    var maxIterations: Int = -1
    var height: Int = 45
    var width: Int = 100
    var updateDelayMS: Long = 400
    var numRows: Int = 45
    var numCols: Int = 85

    fun setWidthHeight(width: Int, height: Int) {
      Settings.width = width
      Settings.height = height
      Board.Origin.x = ( width / 2 )
      Board.Origin.y = ( height / 2 )
    }
  }

  private fun generateSeed(): String {
    var out = ""
    val seedSize = Game.Settings.seedSize
    repeat( seedSize * seedSize ) {
      val rand = Random.nextInt(0, 2)
      out +=
        if(rand == 1) "1" else Random.nextInt(0, 2)
    }
    return out
  }

  fun mainLoop() {
    println("Starting $GAME_NAME")
    if (Settings.stretchToTerminal) {
      val termSize = HelperFunctions.getTermSize()
      println("term: $termSize")
      Settings.setWidthHeight(termSize.second, termSize.first)
    }
    Board.initBoard()
    while (State.numberOfIterations == -1 || State.currentTick < State.numberOfIterations) {
      HelperFunctions.syscall("clear") // Does not work in IDE terminal
      println(Game)
      println(Board)
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
