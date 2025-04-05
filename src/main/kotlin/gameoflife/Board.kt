package gameoflife

import kotlin.system.exitProcess

object Board {
  private var grid: Array<Array<Int>> = Array(Game.Settings.numRows) { Array(Game.Settings.numCols) {0} }
  private var lastGrid: Array<Array<Int>> = Array(Game.Settings.numRows) { Array(Game.Settings.numCols) {0} }
  private var newGrid: Array<Array<Int>> = Array(Game.Settings.numRows) { Array(Game.Settings.numCols) {0} }

  data object Origin {
    var x: Int = ( Game.Settings.numRows / 2 )
    var y: Int = ( Game.Settings.numCols / 2 )
  }

  fun initializeBoard() {
    var count = 0
    val seedSize = Game.Settings.seedSize
    val xLowerBound = (Origin.x - seedSize)
    val xUpperBound = (Origin.x + seedSize)
    val yLowerBound = (Origin.y - seedSize)
    val yUpperBound = (Origin.y + seedSize)
//    println("$seedSize, $seedSize, ${Game.Settings.seed}")  // Debugging
    for (row in xLowerBound..xUpperBound) {
      for (col in yLowerBound..yUpperBound) {
        if (count == Game.Settings.seed.length) break
        grid[row][col] = Game.Settings.seed[count].digitToInt()
        count++
      }
    }
  }

  fun calculateGridUpdate() {
    newGrid = Array(grid.size) { Array(grid[0].size) {0} }
    for (row in grid.indices) {
      for (col in grid[0].indices) {
        newGrid[row][col] = calculateNextCell(row, col)
      }
    }
    isGameDone()
    lastGrid = grid
    grid = newGrid
    Game.State.currentTick++
  }

  private fun isGameDone() {
    if ( newGrid.contentDeepEquals(Array(grid.size) { Array(grid[0].size) {0} })) {
      println("All dead :(, exiting process now")
      exitProcess(0)
    }
    if ( newGrid.contentDeepEquals(lastGrid) ) {
      println("2-cycle oscillation has been achieved, exiting process now")
      exitProcess(0)
    }
    if ( newGrid.contentDeepEquals(grid)) {
      println("No more change, exiting process now")
      exitProcess(0)
    }
  }

  private fun calculateNextCell(x: Int, y: Int): Int {
    var livingCount = 0
    for (row in x-1..x+1) {
      for (col in y-1..y+1) {
        if ( row == x && col == y ) continue // skip the center
        if ( ! inGrid(row, col) ) continue // skip out of grid
        if ( grid[row][col] == 1 ) livingCount++
      }
    }
    return when {
      // Any live cell with fewer than two live neighbours dies, as if by underpopulation
      grid[x][y] == 1 && livingCount < 2 -> 0
      // Any live cell with two or three live neighbours lives on to the next generation
      grid[x][y] == 1 && livingCount in 2..3 -> grid[x][y]
      // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
      grid[x][y] == 0 && livingCount == 3 -> 1
      // Any live cell with more than three live neighbours dies, as if by overpopulation
      grid[x][y] == 1 && livingCount > 3 -> 0
      else -> grid[x][y]
    }
  }

  private fun inGrid(x: Int, y: Int): Boolean {
    return x in grid.indices && y in grid[0].indices
  }

  fun convertToStringGrid(): Array<Array<String>> {
    val outGrid: Array<Array<String>> = Array(grid.size) { Array(grid[0].size) {""} }
    val deadString = if (Game.Settings.backgroundOn) Game.Settings.deadString else " "
    for (row in grid.indices) {
      for (col in grid[0].indices) {
        outGrid[row][col] = (if (grid[row][col] == 1) Game.Settings.aliveString else deadString) + " "
        if (col == grid[0].size-1) outGrid[row][col] += "\n"
      }
    }
    return outGrid
  }

  fun concatenateStringGrid(stringGrid: Array<Array<String>>): String {
    var out = ""
    for (row in grid.indices) {
      for (col in grid[0].indices) {
        out += stringGrid[row][col]
      }
    }
    return out
  }

}
