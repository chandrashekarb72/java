package dfs;

public class MazeCountPaths {

	int[][] grid = { 
			{ 0, 1, 1, 0, 1, 0, 1 }, 
			{ 0, 0, 1, 1, 1, 0, 1 }, 
			{ 1, 1, 1, 1, 1, 1, 1 }, 
			{ 1, 1, 0, 1, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1 }, 
			{ 1, 1, 0, 0, 1, 1, 1 } 
			};

	public void print_maze() {

		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++)
				System.out.print(grid[row][column]);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MazeCountPaths ms = new MazeCountPaths();
		ms.print_maze();

		System.out.println(ms.countMazePaths(ms.grid, 5, 1, 1, 3));
		System.out.println(ms.countPaths(ms.grid, 5, 1, 1, 3,0));
	
	}

	boolean isValid(int row, int col, int[][] maze) {

		if (row >= 0 && row < maze.length && col >= 0 && col < maze[0].length) {
			return true;
		}
		return false;
	}

	public int countMazePaths(int[][] maze, int startRow, int startCol, int endRow, int endCol) {

		if (startRow < 0 || startRow >= maze.length || startCol < 0 || startCol >= maze[0].length) {
			return 0;
		}
		if (maze[startRow][startCol] == 0 || maze[startRow][startCol] == 2) {
			return 0;
		}
		if (startRow == endRow && startCol == endCol) {
			return 1;
		}

		maze[startRow][startCol] = 2; // choose
		int total = 0;

		total = countMazePaths(maze, startRow + 1, startCol, endRow, endCol);
		total += countMazePaths(maze, startRow - 1, startCol, endRow, endCol);
		total += countMazePaths(maze, startRow, startCol + 1, endRow, endCol);
		total += countMazePaths(maze, startRow, startCol - 1, endRow, endCol);
		maze[startRow][startCol] = 1; // unchoose

		return total;

	}
	
	// recursion
		private int countPaths(int maze[][], int row, int col, int endRow, int endCol, int count) {

			int[] dx = new int[] { 0, 0, -1, 1 };
			int[] dy = new int[] { 1, -1, 0, 0 };

			if (row == endRow && col == endCol) {
				count++;
				return count;
			}

			if (isValidCell(maze, row, col)) {

				for (int i = 0; i < 4; i++) {
					maze[row][col] = 2; // so that we do not visit again
					int x = dx[i] + row;
					int y = dy[i] + col;
					count = countPaths(maze, x, y, endRow, endCol, count);
					maze[row][col] = 1; // backtrack
				}
			}
			return count;
		}
		
		public boolean isValidCell(int[][] grid, int row, int col) {

			boolean isSafe = false;
			if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
				isSafe = true;
			}
			return isSafe;
		}

}
