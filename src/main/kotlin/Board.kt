object Board {
  var numRows: Int = 25
  var numCols: Int = 25

  var Grid: Array<Array<Int>> = Array(numRows) { Array(numCols) {0} }

  data object Origin {
    var x: Int = ( numRows / 2 )
    var y: Int = ( numCols / 2 )
  }

  fun initBoard(seed: String = Game.generateSeed()) {
    var count = 0
    for (row in Origin.x-1..Origin.x+1) {
      for (col in Origin.y-1..Origin.y+1) {
        Grid[row][col] = seed[count].digitToInt()
        count++
      }
    }
  }

  fun updateGrid() {
    val newGrid: Array<Array<Int>> = Array(Grid.size) { Array(Grid[0].size) {0} }
    for (row in Grid.indices) {
      for (col in Grid[0].indices) {
        newGrid[row][col] = getNextState(row, col)
      }
    }
    Grid = newGrid
    Game.currentTick++
  }

  fun getNextState(x: Int, y: Int): Int {
    var livingCount = 0
    for (row in x-1..x+1) {
      for (col in y-1..y+1) {
        if ( row == x && col == y ) continue
        if ( ! inGrid(row, col) ) continue
        if (Grid[row][col] == 1) livingCount++
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

  fun inGrid(x: Int, y: Int): Boolean {
    return x in Grid.indices && y in Grid[0].indices
  }

  override fun toString(): String {
    var out = ""
    for (row in Grid.indices) {
      for (col in Grid[0].indices) {
        val current = Grid[row][col]
        out += if (current == 0) "." else current
        out += " "
      }
      out += "\n"
    }
    return out
  }

}
