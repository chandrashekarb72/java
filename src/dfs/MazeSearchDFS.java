package dfs;

public class MazeSearchDFS {

	char[][] grid = { 
			{ '1', 'X', '1', '1', '1' }, 
			{ '1', '1', '1', 'X', '1' }, 
			{ 'X', 'X', '1', '1', '1' },
			{ '1', '1', '1', 'X', '1' }, 
			{ '1', 'X', '1', '1', 'F' } 
			};

	public void print_maze() {

		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++)
				System.out.print(grid[row][column]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MazeSearchDFS ms = new MazeSearchDFS();

		System.out.println(ms.solveMaze(0, 0, ms.grid));

	}

	/** DFS Search using backtracking **/
	
	boolean solveMaze(int row, int col, char[][] maze) {
		boolean status = false;
		if (isValid(row, col, maze)) {

			//base case
			if (maze[row][col] == 'F')
				return true;

			//base case WALL
			if (maze[row][col] == 'X')
				return false;

			//base case already visited
			if (maze[row][col] == '.')
				return false;

			maze[row][col] = '.'; // choose mark it as visited

			//check all the neighbors 
			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };

			for (int i = 0; i < dx.length; i++) {
				int nr = row + dx[i];
				int nc = col + dy[i];
				if (solveMaze(nr, nc, maze)) {
					status = true;
				}
			}
			//if successfully found a solution return true
			if (status) {
				return true;
			}
			maze[row][col] = '1'; // unchoose
		}
		return status;
	}

	// check the boundaries
	boolean isValid(int row, int col, char[][] maze) {

		if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
			return true;
		}
		return false;
	}

}
