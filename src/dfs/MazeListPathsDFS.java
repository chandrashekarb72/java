package dfs;

import java.util.ArrayList;
import java.util.List;



public class MazeListPathsDFS {

	private static final int N = 4;
	
	public static void main(String[] args) {

		MazeListPathsDFS maze = new MazeListPathsDFS();

		int m[][] = { { 1, 1, 1, 1 }, 
					  { 1, 1, 0, 1 }, 
					  { 0, 1, 0, 1 }, 
					  { 1, 1, 1, 1 } 
					};
	
		List<String> currentPath = new ArrayList<String>();
		currentPath.add("0"+","+"0");
		List<List<String>> allPaths = new ArrayList<List<String>>();
		maze.countPaths(m, 0, 0,currentPath, allPaths);

		for(List<String> path: allPaths) {
			System.out.print("Path --" );
			for (String str: path) {
				System.out.print(str +" ");
			}
			System.out.println();
		}
		System.out.println("Total number of unique paths are " + allPaths.size());

	}

	public boolean isValidCell(int[][] grid, int row, int col) {

		boolean isSafe = false;

		if (row >= 0 && row < N && col >= 0 && col < N 
				&& grid[row][col] == 1  ) {
			isSafe = true;
		}
		return isSafe;
	}

	//recursion
	private void countPaths(int maze[][], int row, int col,List<String> currentPath, List<List<String>> allPaths) {
		
		int[] dx = new int[] { 0, 0, -1, 1 };
		int[] dy = new int[] { 1, -1, 0, 0 };
		
		//base case
		if (row == N - 1 && col == N - 1) {
			List<String> temp = new ArrayList<String>(currentPath);
			allPaths.add(temp);
			currentPath =  new ArrayList<String>(); 
			return;
		}

		if (isValidCell(maze,row, col)) {
			
			for (int i = 0; i < 4; i++) {
			    maze[row][col] = 2; //so that we do not visit again 

				int x = dx[i] + row;
				int y = dy[i] + col;
				currentPath.add(x+","+y);
				countPaths(maze, x , y,currentPath,allPaths);
				
				maze[row][col] = 1; //backtrack
				currentPath.remove(currentPath.size()-1);
			}
		}
		//return count;
	}}
		
