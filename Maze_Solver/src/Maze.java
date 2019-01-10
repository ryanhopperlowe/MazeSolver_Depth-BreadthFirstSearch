
/**
 * Maze class
 * @author Ryan
 *
 */
public class Maze {
	private Cell[]					cells;
	private int						rad;
	
	/**
	 * Constructor generates a basic grid then runs DFS
	 * to convert it into a randomized maze
	 * 
	 * @param r is the desired size of the maze 
	 */
	public Maze(int r) {
		cells = new Cell[r * r]; //the array containing each cell within the maze
		rad = r; //the length of each side of the maze
		
		//initializes the array with untouched cells
		for (int i = 0; i < cells.length; i++) {
			cells[i] = new Cell();
		}
		
		//
		//initialize the maze to an empty grid with all walls intact apart from start and end
		//
		
		int i = 0; //the index of the current cell
		//for each row of the maze
		for (int j = 0; j < rad; j++) {
			
			//for each cell of the current row
			for (int k = 0; k < rad; k++) {
				
				//if cell is not in the top row, add wall below cell
				if (j > 0)
					cells[i].addWalledNeighbor(cells[i - rad]);
				
				//if cell is not in bottom row, add wall above cell
				if (j < rad - 1)
					cells[i].addWalledNeighbor(cells[i + rad]);
				
				//if cell is not in the first column, add wall before cell
				if (k > 0)
					cells[i].addWalledNeighbor(cells[i - 1]);
				
				//if cell is not in the last column, add wall after cell
				if (k < rad - 1)
					cells[i].addWalledNeighbor(cells[i + 1]);
				
				//move to next cell
				i++;
			}
		}
		
//		generateMaze();
	}
	
	public Maze(Maze m) {
		this(m.getSize());
		
		Cell[] mCells = m.getCells();
		
		for (int i = 0; i < rad; i++) {
			for (int j = 0; j < rad; j++) {
				int k = (i * rad) + j;
				
				if (j < rad - 1)
					if (mCells[k].hasWall(m.getCells()[k + 1]))
						this.breakWall(k, k + 1);
				if (k < rad - 1)
					if (mCells[k].hasWall(m.getCells()[k + rad]))
						this.breakWall(k, k + rad);
			}
		}
	}
	
	
	public int getSize() {
		return rad;
	}
	
	/**
	 * @return the array of cells in the maze
	 */
	public Cell[] getCells() {
		return cells;
	}
	
	public Cell get(int index) {
		if (index >= cells.length)
			return null;
		return cells[index];
	}
	
	public int indexOf(Cell c) {
		for (int i = 0; i < cells.length; i++)
			if (cells[i] == c)
				return i;
		return -1;
	}
	
	/**
	 * Checks to see if two cells are separated by a wall
	 * @param c1 first cell
	 * @param c2 second cell
	 * @return true if the cells are separated by a wall
	 * @return false if the cells are not
	 */
	public static boolean hasWall(Cell c1, Cell c2) {
		return c1.hasWall(c2) && c2.hasWall(c1);
	}
	
	/**
	 * Removes the wall separating two cells if there is one
	 * @param c1 first cell
	 * @param c2 second cell
	 * @return true if there was a wall that was successfully broken
	 * @return false if no wall was broken
	 */
	public boolean breakWall(Cell c1, Cell c2) {
		//if wall separates the two cells, remove shared wall from both cells
		if (hasWall(c1, c2)) {
			c1.removeWall(c2);
			c2.removeWall(c1);
			return true;
		}
		return false;
	}
	
	public boolean breakWall(int c1, int c2) {
		Cell first, second;
		first = cells[c1];
		second = cells[c2];
		
		return breakWall(first, second);
	}
	
	
	/**
	 * Uses DFS to break walls in the grid until a maze is fully generated
	 * Maze rules include:
	 * All cells are accessible
	 * There is only one path to get from start to finish
	 * There are no cycles/loops in the maze
	 * There are no open spaces in the maze
	 */
	public static Maze generateMaze(int r) {
		Maze maze = new Maze(r);
		Cell[] cells = maze.getCells();
		//Create a stack of cells that can hold (at maximum) the number of cells in the maze
		CellStack cstack = new CellStack(cells.length);
		//push the first cell to the stack
		cstack.push(cells[0]);
		
		//currentCell is the cell being modified at any given point
		Cell currentCell = cstack.peek();
		//nextCell is the next cell in the DFS that must all four of it's walls intact
		Cell nextCell = null;
		//while the stack is not empty
		while (!cstack.isEmpty()) {
			nextCell = currentCell.getRandomWalledNeighbor();
			//if currentCell has no walled neighbors, remove it from the stack
			if (nextCell == null)
				cstack.pop();
			//otherwise, randomly break a wall, and push the newly opened cell to the stack
			else {
				maze.breakWall(currentCell, nextCell);
				cstack.push(nextCell);
			}
			//move to the next cell in the stack
			currentCell = cstack.peek();
			
			//this continues until no cell in the maze has all of its walls intact
		}
		return maze;
	}
	
	/**
	 * resets the maze so it is unsolved
	 */
	public void reset() {
		for (Cell c : cells)
			c.setValue(Cell.UNTOUCHED);
	}
	
	public void resetGrid() {
		for (Cell c : cells) {
			c = new Cell();
		}
	}
	
	/**
	 * function to create a string representation of the maze
	 * first cell has opening on top, last cell has opening on bottom
	 * remainder of first row has wall on top, remainder of last row has wall on bottom
	 * each row starts and ends with a wall
	 * cells with a value of -1 are untouched cells (print nothing)
	 * cells with a value > 0 are touched cells (print number of steps to reach the cell)
	 * cells with a value of 0 are verified path cells (print "#")
	 */
	public String toString() {
		//first cell has opening at top wall for start
		//the rest of the first row always has a top wall
		String str = "+   +";
		for (int i = 1; i < rad; i++) {
			str += "---+";
		}
		
		//for every row in the maze
		for (int k = 0; k < rad; k++) {
			
			//start every row with a wall and a bottom right corner
			String middleWalls = "\n|";
			String bottomWalls = "\n+";
			
			
			//
			//print the value of each cell in the row
			//as well as all walls between the cells of this row
			//
			for (int j = 0; j < rad; j++) {
				int i = (k * rad) + j; //index of current cell
				
				//if current cell's value has not been changed (value == -1)
				//print nothing in the cell
				//
				//if current cell has been touched during the search (value > 0)
				//print the last digit of the cell's value
				//
				//otherwise, the cell is a verified path (value == 0)
				//print "#"
				//
				if (cells[i].untouched())
					middleWalls += "  ";
				else if (cells[i].hasValue())
					middleWalls += " " + cells[i].getValue() % 10;
				else 
					middleWalls += " #";
				
				//if the current cell is not the last cell in the row
				//and the current cell has a wall between it and the next cell
				//then print a wall after the current cell
				//otherwise leave an opening between the two cells
				//
				//if the current cell is in the last row of the maze
				//and the current cell is not the last cell of the row
				//then print a wall below the cell
				//otherwise if the cell IS the last of the row
				//then print an opening to signal the end of the maze
				if (j < rad - 1) {
					if (hasWall(cells[i], cells[i + 1]))
						middleWalls += " |";
					else
						middleWalls += "  ";
					
					if (k == rad - 1)
						bottomWalls += "---+";
				}
				else if (k == rad - 1)
					bottomWalls += "   +";
				
				//if the current row is not the last of the maze
				//and the current cell has a wall between itself and the cell below it
				//then print a wall below the current cell
				//otherwise leave an opening between the two cells
				if  (k < rad - 1) {
					if (hasWall(cells[i], cells[i + rad]))
						bottomWalls += "---+";
					else
						bottomWalls += "   +";
				}
			}
			
			//end every row with a right wall
			middleWalls += " |";
			
			str += middleWalls;
			str += bottomWalls;
		}
		return str;
	}
}
