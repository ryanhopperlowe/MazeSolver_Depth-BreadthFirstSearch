import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class MazeTester {
	private Maze				maze;
	private String				untouched, dfs, path;
	
	@Before
	public void setUp() {
		
		maze = new Maze(4);
		System.out.println(maze);
		maze.breakWall(1, 2);
		maze.breakWall(2, 3);
		maze.breakWall(0, 4);
		maze.breakWall(1, 5);
		maze.breakWall(3, 7);
		maze.breakWall(5, 6);
		maze.breakWall(4, 8);
		maze.breakWall(6, 10);
		maze.breakWall(7, 11);
		maze.breakWall(8, 9);
		maze.breakWall(9, 13);
		maze.breakWall(11, 15);
		maze.breakWall(12, 13);
		maze.breakWall(13, 14);
		maze.breakWall(14, 15);
		System.out.println(maze);
		
		
		untouched = 
				  "+   +---+---+---+\n"
				+ "|   |           |\n"
				+ "+   +   +---+   +\n"
				+ "|   |       |   |\n"
				+ "+   +---+   +   +\n"
				+ "|       |   |   |\n"
				+ "+---+   +---+   +\n"
				+ "|               |\n"
				+ "+---+---+---+   +";
		
		dfs = 
				  "+   +---+---+---+\n"
				+ "| 1 |           |\n"
				+ "+   +   +---+   +\n"
				+ "| 2 |       |   |\n"
				+ "+   +---+   +   +\n"
				+ "| 3   4 |   |   |\n"
				+ "+---+   +---+   +\n"
				+ "| 6   5   7   8 |\n"
				+ "+---+---+---+   +";
		
		path = 
				  "+   +---+---+---+\n"
				+ "| # |           |\n"
				+ "+   +   +---+   +\n"
				+ "| # |       |   |\n"
				+ "+   +---+   +   +\n"
				+ "| #   # |   |   |\n"
				+ "+---+   +---+   +\n"
				+ "|     #   #   # |\n"
				+ "+---+---+---+   +";
				
	}
	
	@Test
	public void testMazeGeneration() {
		Maze m1 = new Maze(4);
		assertEquals(m1.getCells().length, 16);
		for (Cell c : m1.getCells())
			assertTrue(c.untouched());
	}
	
	@Test
	public void testSolver() {
		assertEquals(untouched, maze.toString());
		System.out.println(maze);
		DFSMazeSolver dfs = new DFSMazeSolver(maze);
		dfs.run();
		System.out.println(maze);
		dfs.solve();
		System.out.print(maze);
		assertEquals(path, maze.toString());
		
		
		
	}
}
