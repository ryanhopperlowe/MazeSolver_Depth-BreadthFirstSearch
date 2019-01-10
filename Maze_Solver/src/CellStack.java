/**
 * Class creates a stack to store the cells in the path of a solved maze
 * contains an array of cells
 * contains an int which refers to the number of cells contained in the stack at any given time
 * @author Ryan
 *
 */
public class CellStack {
	private Cell[]						cells;
	private int							size;
	
	
	/**
	 * Constructor generates a CellStack object
	 * stack is initially empty
	 * 
	 * @param n is maximum number of cells that the stack can hold
	 */
	public CellStack(int n) {
		this.cells = new Cell[n];
		this.size = 0;
	}
	
	
	/**
	 * Add a cell to the bottom of the stack if there is room
	 * 
	 * @param cell is the cell to add to the stack
	 * @return true if the cell was successfully added
	 * @return false if there is no room in the stack
	 */
	public boolean push(Cell cell) {
		if (size < cells.length) {
			cells[size++] = cell;
			return true;
		}
		return false;
	}
	
	/**
	 * Remove the bottom cell of the stack if the stack is not already empty
	 * 
	 * @return the cell popped from the stack
	 * @return null if the stack is empty
	 */
	public Cell pop() {
		if (size > 0) {
			return cells[--size] = null; 
		}
		return null;
	}
	
	/**
	 * Peek at the first cell held in the stack
	 * without changing its state
	 * 
	 * @return a reference to the bottom cell in the stack
	 */
	public Cell peek() {
		if (!isEmpty())
			return cells[size - 1];
		return null;
	}
	
	/**
	 * Check how many cells are contained in the stack
	 * 
	 * @return the number of cells contained in the stack 
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Check to see if the stack is empty
	 * 
	 * @return true if there are no cells contained in the stack
	 * @return false if the stack contains at least one cell
	 */
	public boolean isEmpty() {
		return size == 0;
	}
}
