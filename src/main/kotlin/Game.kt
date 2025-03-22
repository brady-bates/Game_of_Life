package main.kotlin

//import kotlinx.serialization.*

object Game {
  const val GAME_NAME = "Conway's Game of Life"
  var currentTick = 0

  data object Settings {
    var seedSize: Int = 3
    var seed: String = Board.generateSeed()
    var stretchToTerminal: Boolean = false
    private var backgroundOn: Boolean = true
    var aliveChar: Char = '@'
    var deadChar: Char = '.'
    var numberOfIterations: Int = -1
    var height: Int = 45
    var width: Int = 100

    fun setBackground(background: Boolean, backgroundChar: Char) {
      backgroundOn = background
      deadChar = if (backgroundOn) backgroundChar else ' '
    }
  }

  fun mainLoop(numberOfIterations: Int) {
    while (numberOfIterations == -1 || currentTick < numberOfIterations) {
      HelperFunctions.syscall("clear") // Does not work in IDE terminal
      println(Game)
      println(Board)
      Board.calculateGridUpdate()
      Thread.sleep(350)
    }
  }

//  override fun toString(): String {
//
//  }
}
