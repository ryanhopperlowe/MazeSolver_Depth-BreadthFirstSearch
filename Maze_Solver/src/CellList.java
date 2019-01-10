import java.util.Iterator;

public class CellList implements Iterable<Cell> {

	private Cell[]					cells;
	private int						size;
	
	
	public CellList() {
		cells = new Cell[4];
		size = 0;
	}
	
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean add(Cell cell) {
		if (size < cells.length) {
			cells[size++] = cell;
			return true;
		}
		return false;
	}
	
	public Cell get(int index) {
		if (index >= size) 
			return null;
		return cells[index];
	}
	
	public boolean remove(Cell cell) {
		int index = indexOf(cell);
		if (index != -1) {
			cells[index] = null;
			for (int i = index; i < size - 1; i++)
				cells[i] = cells[i + 1];
			size--;
			return true;
		}
		return false;
	}
	
	public boolean contains(Cell cell) {
		if (size == 0) return false;
		
		for (Cell c : cells)
			if (c == cell)
				return true;
		return false;
	}
	
	public int indexOf(Cell cell) {
		if (size > 0) {
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] == cell) {
					return i;
				}
			}
		}
		return -1;
	}


	private class CellIterator implements Iterator<Cell> {
		
		private int index;

		public CellIterator() {
			index = 0;
		}
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Cell next() {
			Cell cell = cells[index++];
			return cell;
		}
		
	}
	
	@Override
	public Iterator<Cell> iterator() {
		return new CellIterator();
	}
	
}
