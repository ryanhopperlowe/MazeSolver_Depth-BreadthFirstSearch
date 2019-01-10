

public class CellQueue {
	private Cell[]						cells;
	private int							size;
	private int							head, tail;
	
	public CellQueue(int length) {
		cells = new Cell[length];
		size = 0;
		head = 0;
		tail = 0;
		
		for (Cell c : cells) {
			c = null;
		}
	}
	
	
	public boolean enqueue(Cell cell) {
		if (size >= cells.length)
			return false;
		
		cells[tail] = cell;
		tail = (tail + 1) % cells.length;
		size++;
		return true;
	}
	
	public Cell dequeue() {
		if (size == 0)
			return null;
		
		Cell temp = cells[head];
		cells[head] = null;
		head = (head + 1) % cells.length;
		size--;
		return temp;
	}
	
	public Cell peek() {
		return cells[head];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isFull() {
		return size == cells.length;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
