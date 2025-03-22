package main.kotlin

object Game {
  const val GAME_NAME = "Conway's Game of Life"
  var currentTick = 0

  data object Settings {
    var seedSize: Int = 5
    var seed: String = Board.generateSeed()
    var stretchToTerminal: Boolean = false
    private var backgroundOn: Boolean = true
    var aliveChar: Char = '@'
    var deadChar: Char = '.'
    var numberOfIterations: Int = -1
    var height: Int = 45
    var width: Int = 100
    var updateSleepTimerMS: Long = 400

    fun setBackground(background: Boolean, backgroundChar: Char) {
      backgroundOn = background
      deadChar = if (backgroundOn) backgroundChar else ' '
    }

    fun setWidthHeight(width: Int, height: Int) {
      Settings.width = width
      Settings.height = height
      Board.Origin.x = ( width / 2 )
      Board.Origin.y = ( height / 2 )
    }
  }

  fun mainLoop() {
    println("Starting $GAME_NAME")
    if (Settings.stretchToTerminal) {
      val termSize = HelperFunctions.getTermSize()
      println("term: $termSize")
      Settings.setWidthHeight(termSize.second, termSize.first)
    }
    Board.initBoard()
    while (Settings.numberOfIterations == -1 || currentTick < Settings.numberOfIterations) {
      HelperFunctions.syscall("clear") // Does not work in IDE terminal
      println("Seed: ${Settings.seed}")
//      println(Game)
      println(Board)
      Board.calculateGridUpdate()
      Thread.sleep(Settings.updateSleepTimerMS)
    }
  }

//  override fun toString(): String { }

}
