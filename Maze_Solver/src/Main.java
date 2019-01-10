

public class Main {
	public static void main(String[] args) {
		Maze maze = Maze.generateMaze(10);
		System.out.println("\n \n" + maze);
		
		BFSMazeSolver solverBFS = new BFSMazeSolver(maze);
		
		solverBFS.run();
		System.out.println("\n \n" + maze);
		
		solverBFS.solve();
		System.out.println("\n \n" + maze);
		
		maze.reset();
		System.out.println("\n\n" + maze);
		
		DFSMazeSolver solverDFS = new DFSMazeSolver(maze);
		
		solverDFS.run();
		System.out.println("\n \n" + maze);
		
		solverDFS.solve();
		System.out.println("\n \n" + maze);
	}
}
