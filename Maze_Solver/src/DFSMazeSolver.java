

/**
 * Maze solver class that uses Random DFS to find the only path from the start to end
 * contains a Maze object that must be solved
 * contains references to the start and end cells of the maze
 * generates a Stack containing the path of the solved maze (ordered from end to start)
 * 
 * @author Ryan
 *
 */
public class DFSMazeSolver {
	private Maze				maze;
	private Cell				start, end; 
	private CellStack			path;
	
	/**
	 * Constructor generates a solver for the maze
	 * 
	 * @param maze is the maze to solve
	 */
	public DFSMazeSolver(Maze maze) {
		this.maze = maze;
		
		//initialize start and end to the first and last cells in the maze
		start = maze.getCells()[0];
		end = maze.getCells()[maze.getCells().length - 1];
		
		//initialize path stack so it is able to hold up to the number of cells in the maze
		path = new CellStack(maze.getCells().length);
	}
	
	
	/**
	 * Run Randomized DFS on the maze until the exit cell is found
	 */
	public void run() {
		
		//begin with the first cell in the maze
		path.push(start);
		start.setValue(1);
		
		Cell currentCell = path.peek(); //the cell being analyzed at any given time in the algorithm
		int count = 2; //the number of iterations since the start of DFS
		
		//Run DFS until the end cell is found
		while (currentCell != end) {
			
			//randomly select an untouched neighbor of the current cell not blocked by a wall
			//add it to the stack, and mark it as touched
			//then run DFS on it
			//
			//if the current cell has no open, untouched neighbors
			//then remove it from the stack
			//then run DFS on the previous cell to see if it has any other untouched neighbors
			Cell nextCell = currentCell.getNextRandomOpenUntouchedNeighbor();
			if (nextCell != null) {
				nextCell.setValue(count++);
				path.push(nextCell);
			}
			else
				path.pop();
			
			//currentCell becomes the next cell in the stack
			currentCell = path.peek();
		}
		//maze is solved
	}
	
	public Maze runFullDfs() {
		
		
		return maze;
	}
		
	/**
	 * modify maze to only display the path from start to finish
	 */
	public void solve() {

		//reset the values of all cells in the maze to appear untouched
		for (Cell c : maze.getCells())
			c.setValue(Cell.UNTOUCHED);
		
		//mark each cell in the stack as a verified path cell
		while (!path.isEmpty()) {
			Cell cell = path.peek();
			cell.setValue(Cell.IS_PATH);
			path.pop();
		}
	}
}
