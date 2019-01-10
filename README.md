# MazeSolver_Depth-BreadthFirstSearch
Generates a maze using DFS and then solves it using both DFS and BFS

## Directions
simply import the project files to your JRE, run the Main class as a Java project, and the program will execute.

### What to expect
- a perfect* random maze will be generated using Depth-First Search
- the program will run Breadth-First Search on the maze until the end cell is found, marking each cell with the number step modulus 10 (step % 10)
- then the maze is displayed again except every cell that is part of the final path is marked by a '#'
- the same process is repeated except the maze is solved using Depth-First Search
- running the program more than once will result in different mazes to be solved as they are truly randomly generated upon running the program
- the size of the maze can be modified by changing the input value in the constructor of the Maze object in the Main class

### What defines a "Perfect" maze
- no cycles
- only one path from start to finish
- no sections are blocked off
- all cells are reachable by a single path
