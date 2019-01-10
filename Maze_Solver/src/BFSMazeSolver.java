import java.util.*;

public class BFSMazeSolver {
	private Maze					maze;
	private Cell					start, end;
	private CellQueue				queue;
	private CellStack				path;
	
	
	public BFSMazeSolver(Maze maze) {
		this.maze = maze;
		start = maze.getCells()[0];
		end = maze.getCells()[maze.getCells().length - 1];
		queue = new CellQueue(maze.getCells().length);
		path = new CellStack(maze.getCells().length);
	}
	
	
	public void run() {
		Cell currentCell = start;
		queue.enqueue(start);
		currentCell.setValue(1);
		int count = 2;
		while (currentCell != end) {
			currentCell = queue.dequeue();
			for (Cell c : currentCell.getOpenUntouchedNeighbors()) {
				c.setValue(count++);
				queue.enqueue(c);
			}
		}
		
//		System.out.println("\n \n" + maze + "\n \n");
	}
	
	public void solve() {
		Cell currentCell = end;
		while (currentCell != start) {
			path.push(currentCell);
			currentCell = currentCell.getPreviousCell();
		}
		path.push(start);
		
		for (Cell c : maze.getCells()) {
			c.setValue(-1);
		}
		
		
		while (!path.isEmpty()) {
			Cell cell = path.peek();
			cell.setValue(0);
			path.pop();
		}
		
//		System.out.println(maze);
		
		
	}
}
