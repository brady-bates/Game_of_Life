package main.kotlin

import kotlin.random.Random
import kotlin.system.exitProcess

object Board {
  var numRows: Int = 40
  var numCols: Int = 80

  data object Origin {
    var x: Int = ( numRows / 2 )
    var y: Int = ( numCols / 2 )
  }

  private var Grid: Array<Array<Int>> = Array(numRows) { Array(numCols) {0} }
  var lastGrid: Array<Array<Int>> = Array(numRows) { Array(numCols) {0} }

  fun initBoard() {
    var count = 0
    val seedSize = Game.Settings.seedSize
    val xLowerBound = (Origin.x - seedSize)
    val xUpperBound = (Origin.x + seedSize)
    val yLowerBound = (Origin.y - seedSize)
    val yUpperBound = (Origin.y + seedSize)

    println("$seedSize, $seedSize, ${Game.Settings.seed}") // Debugging

    for (row in xLowerBound..xUpperBound) {
      for (col in yLowerBound..yUpperBound) {
        if (count == Game.Settings.seed.length) break
        Grid[row][col] = Game.Settings.seed[count].digitToInt()
        count++
      }
    }
  }

  fun calculateGridUpdate() {
    val newGrid: Array<Array<Int>> = Array(Grid.size) { Array(Grid[0].size) {0} }
    for (row in Grid.indices) {
      for (col in Grid[0].indices) {
        newGrid[row][col] = calculateNextCell(row, col)
      }
    }
    if ( newGrid.contentDeepEquals(Array(Grid[0].size) { Array(Grid.size) {0} })) {
      println("All dead :(, exiting process now")
      exitProcess(0)
    }
    if ( newGrid.contentDeepEquals(lastGrid) ) {
      println("Oscillation has been achieved, exiting process now")
      exitProcess(0)
    }
    if ( newGrid.contentDeepEquals(Grid)) {
      println("No more change, exiting process now")
      exitProcess(0)
    }
    lastGrid = Grid
    Grid = newGrid
    Game.currentTick++
  }

  private fun calculateNextCell(x: Int, y: Int): Int {
    var livingCount = 0
    for (row in x-1..x+1) {
      for (col in y-1..y+1) {
        if ( row == x && col == y ) continue // skip the center
        if ( ! inGrid(row, col) ) continue // skip out of grid
        if ( Grid[row][col] == 1 ) livingCount++
      }
    }
    return when {
      Grid[x][y] == 1 && livingCount < 2 -> 0 // Any live cell with fewer than two live neighbours dies, as if by underpopulation
      Grid[x][y] == 1 && livingCount in 2..3 -> Grid[x][y] // Any live cell with two or three live neighbours lives on to the next generation
      Grid[x][y] == 0 && livingCount == 3 -> 1 // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
      Grid[x][y] == 1 && livingCount > 3 -> 0 // Any live cell with more than three live neighbours dies, as if by overpopulation
      else -> Grid[x][y]
    }
  }

  private fun inGrid(x: Int, y: Int): Boolean {
    return x in Grid.indices && y in Grid[0].indices
  }

  fun generateSeed(): String {
    var out = ""
    val seedSize = Game.Settings.seedSize
    repeat( seedSize * seedSize ) {
      val rand = Random.nextInt(0, 2)
      out +=
        if(rand == 1) "1" else Random.nextInt(0, 2)
//        rand
    }
    return out
  }

  override fun toString(): String {
    var out = ""
    for (row in Grid.indices) {
      for (col in Grid[0].indices) {
        val current = Grid[row][col]
        out += if (current == 0) Game.Settings.deadChar else Game.Settings.aliveChar
        out += " "
      }
      out += "\n"
    }
    return out
  }

}
