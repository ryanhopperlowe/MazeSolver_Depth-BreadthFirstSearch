
import java.util.*;

/**
 * Class defines a cell found in a maze
 * contains an integer value
 * a value of 
 * each cell has references to all of its neighbors (max four)
 * as well as references to all of its neighbors with which it shares walls
 * 
 * @author Ryan
 *
 */
public class Cell {
	public static final int UNTOUCHED =		-1;
	public static final int IS_PATH = 		0;
	
	private ArrayList<Cell>					neighbors;
	private ArrayList<Cell>					walls;
	private int								value;
	
	private Random							rand;
	
	
	/**
	 * Constructor generates an untouched Cell
	 * initialized with no neighbors or walls
	 */
	public Cell() {
		value = UNTOUCHED;
		neighbors = new ArrayList<>();
		walls = new ArrayList<>();
		
		rand = new Random();
	}
	
	
	/**
	 * Check to see if the cell is touched and not verified
	 * 
	 * @return true if cell is not untouched and not a path cell
	 * @return false if the cell is touched or part of the path
	 */
	public boolean hasValue() {
		return value > 0;
	}
	
	/**
	 * check to see if the cell is untouched
	 * 
	 * @return
	 */
	public boolean untouched() {
		return value == -1;
	}
	
	/**
	 * check to see if the cell is verified
	 * @return true if the cell has been verified as part of the path
	 * @return false if the cell is not
	 */
	public boolean isPath() {
		return value == 0;
	}
	
	/**
	 * Check the value of the cell
	 * if value is -1 it is untouched
	 * if value is 0 then it is verified
	 * if value is > 0 then it is touched
	 * 
	 * @return the value of the cell
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Mutator
	 * 
	 * @param val the new value of the cell
	 */
	public void setValue(int val) {
		value = val;
	}
	
	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}
	
	
	/**
	 * Check to see if the cell has all of its walls intact
	 * 
	 * @return true if all walls are intact (number of walls matches number of neighbors)
	 * @return false if there is at least 1 open neighbor
	 */
	public boolean hasAllWalls() {
		return walls.size() == neighbors.size();
	}
	
	/**
	 * adds the reference to a neighboring cell if there is room
	 * and separates it from this cell with a wall
	 * 
	 * @param cell the cell to add to
	 * @return true if the neighbor was successfully added and walled off
	 * @return false if there was no room
	 */
	public boolean addWalledNeighbor(Cell cell) {
		if (neighbors.size() < 4) {
			neighbors.add(cell);
			walls.add(cell);
			return true;
		}
		return false;
	}
	
	/**
	 * Check to see if a wall separates this cell is separated from another
	 * 
	 * @param cell the cell to check is separated by a wall
	 * @return true if there is a wall between the two cells
	 * @return false if the two cells are not separated
	 */
	public boolean hasWall(Cell cell) {
		return walls.contains(cell);
	}
	
	/**
	 * Remove a wall between this cell and another
	 * if a wall separates the two
	 * 
	 * @param cell the cell to remove the wall from
	 * @return true if the wall was successfully removed
	 * @return false if there was no wall separating the two cells to begin with
	 */
	public boolean removeWall(Cell cell) {
		return walls.remove(cell);
	}
	
	/**
	 * Get a list of all neighboring cells that have already been touched
	 * and are not separated from this cell by a wall
	 * 
	 * @return the list of touched open neighboring cells
	 */
	public ArrayList<Cell> getTravelledNeighbors() {
		ArrayList<Cell> nbors = new ArrayList<>();
		for (Cell nbor : neighbors)
			if (!walls.contains(nbor) && nbor.hasValue())
				nbors.add(nbor);
		return nbors;
	}
	
	/**
	 * Get the neighboring cell that was touched by a search algorithm previous to this one
	 * if such a cell exists
	 * 
	 * @return a reference to the open, touched cell with the lowest value
	 * @return null if there is no cell that was touched before this cell
	 */
	public Cell getPreviousCell() {
		
		//get all open, neighboring cells touched by a search algorithm 
		ArrayList<Cell> nbors = getTravelledNeighbors();
		if (nbors.isEmpty())
			return null;
		
		//
		//iterate through each touched open neighbor
		//return the first cell from the list with a value less than the value of this
		//(there should always be one)
		//otherwise return null (should never happen)
		//
		for (Cell nbor : nbors)
			if (nbor.getValue() < this.getValue())
				return nbor;
		return null;
	}
	
	/**
	 * Check to see if the cell has any neighbors that are not
	 * blocked off by walls
	 * 
	 * @return true if this cell has at least one open untouched neighbor
	 * @return false if it has none
	 */
	public boolean hasOpenUntouchedNeighbors() {
		return !getOpenUntouchedNeighbors().isEmpty();
	}
	
	/**
	 * Get all untouched neighbors not blocked off by a wall
	 * 
	 * @return a list of all open, untouched, neighboring cells 
	 */
	public ArrayList<Cell> getOpenUntouchedNeighbors() {
		ArrayList<Cell> openNeighbors = new ArrayList<>();
		for (Cell nbor : neighbors) {
			if (!walls.contains(nbor) && !nbor.hasValue())
				openNeighbors.add(nbor);
		}
		return openNeighbors;
	}
	
	/**
	 * Get one open, untouched, neighboring cell if there is one
	 * 
	 * @return a reference to the first open untouched neighbor if one is found
	 * @return null if no cells with these criterion are found
	 */
	public Cell getNextOpenUntouchedNeighbor() {
		if (!getOpenUntouchedNeighbors().isEmpty())
			return this.getOpenUntouchedNeighbors().get(0);
		return null;
	}
	
	/**
	 * Get a random open untouched neighboring cell
	 * 
	 * @return a reference to a random, open, untouched, neighbor if there is one
	 * @return null if no corresponding cells are found
	 */
	public Cell getNextRandomOpenUntouchedNeighbor() {
		ArrayList<Cell> openNeighbors = getOpenUntouchedNeighbors();
		if (!openNeighbors.isEmpty()) {
			if (openNeighbors.size() > 1) {
				int n = rand.nextInt(openNeighbors.size());
				return openNeighbors.get(n);
			}
			else
				return openNeighbors.get(0);
		}
		return null;
	}
	
	/**
	 * Get all neighboring cells separated from this cell by walls
	 * 
	 * @return a list of references to all walled neighbors
	 */
	public ArrayList<Cell> getWalledNeighbors() {
		ArrayList<Cell> walledNeighbors = new ArrayList<>();
		for (Cell nbor : walls) {
			if (nbor.hasAllWalls())
				walledNeighbors.add(nbor);
		}
		return walledNeighbors;
	}
	
	/**
	 * Get one randomly chosen walled neighbor of this cell
	 * 
	 * @return a reference to a random walled neighboring cell
	 */
	public Cell getRandomWalledNeighbor() {
		ArrayList<Cell> walledNbors = getWalledNeighbors();
		if (walledNbors.isEmpty())
			return null;
		
		int n = rand.nextInt(walledNbors.size());
		return walledNbors.get(n);
	}
}
