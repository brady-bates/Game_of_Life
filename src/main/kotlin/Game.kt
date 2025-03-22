import kotlin.random.Random

object Game {
  val GAME_NAME = "Conway's Game of Life"
  var currentTick = 0

  fun generateSeed(): String {
    var out = ""
    repeat(9) {
      out += Random.nextInt(0, 2)
    }
    return out
  }

  fun start() {
    println("Starting $GAME_NAME")
    Board.initBoard("010110011")

    mainLoop()
  }

  fun mainLoop() {
    while (true) {
      Runtime.getRuntime().exec("clear")
      println(Board)
      Board.updateGrid()

      Thread.sleep(750)
    }
  }

//  override fun toString() {}
}
