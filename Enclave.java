/* You are given m x n matrix containing cells represented with zero's as water and one's with land... Find the number of Enclaves or Islands on the matrix... Remember a continuos sequence of 1s' is described as an Island...
 * Eg 1: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]      Output = 2 Islands
 * Eg 2: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]      Output = 1 Islands
 * Eg 3: grid = [[0,1,1,1],[0,1,0,1],[1,0,1,0],[1,1,0,0]]      Output = 3 Islands
*/
import java.util.*;
public class Enclave
{
    public int TotalNumberOfEnclaves(int grid[][])
    {
        boolean[][] Island = new boolean[grid.length][grid[0].length];     // Creating a matrix for the land cell visited...
        int found = 0, count = 0;
        for(int i = 0; i < Island.length; i++)
            Arrays.fill(Island[i], false);     // Filling the entire matrix as unvisited...
        for(int i = 0; i < Island.length; i++)
        {
            for(int j = 0; j < Island[0].length; j++)
            {
                if(grid[i][j] == 1 && Island[i][j] == false)   // Only called till the number of Islands counter...
                {
                    if(DepthFirstSearch(i, j, grid, Island, found) > 0)    // Checking for the Island from the current cell...
                        count++;     // If there is an Island we increment the Island Counter...
                }
            }
        }
        return count;
    }
    public int DepthFirstSearch(int x, int y, int[][] grid, boolean[][] Island, int present)
    {    // Used the concept of Dynamic Programming...
        if((x < 0) || (y < 0) || (x == grid.length) || (y == grid[0].length) || (grid[x][y] == 0) || (Island[x][y] == true))    // If the pointer jumps to the boundary or the current cell is visited already or the current cell is an Ocean, we backtrack...
            return 0;
        if((grid[x][y] == 1) && (Island[x][y] == false))
        {
            Island[x][y] = true;      // If the current cell is land and it is not previously visited...
            present = 1;     // Change the present counter...
        }
        DepthFirstSearch(x + 1, y, grid, Island, present);      // Moving Downwards...
        DepthFirstSearch(x - 1, y, grid, Island, present);      // Moving Upwards...
        DepthFirstSearch(x, y + 1, grid, Island, present);      // Moving Rightwards...
        DepthFirstSearch(x, y - 1, grid, Island, present);      // Moving Leftwards...
        return present;     // Returning the present counter as the final return to the function...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int m, n;
        System.out.print("Enter the number of rows (n) : ");
        n = sc.nextInt();
        System.out.print("Enter the number of columns (m) : ");
        m = sc.nextInt();
        int matrix[][] = new int[n][m];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                System.out.print("Enter state of cell of "+(i+1)+" row and "+(j+1)+" column : ");
                matrix[i][j] = sc.nextInt();
            }
        }
        Enclave enclave = new Enclave();        // Object creation...
        System.out.print("The number of Enclaves : "+enclave.TotalNumberOfEnclaves(matrix));
        sc.close();
    }
}

// Time Complexity  - O(n^2) time...
// Space Complexity - O(n^2) space...

/* DEDUCTIONS :- 
 * 1. We create a matrix for checking the lands which we have visited and which we have not, then we iterate for all the land by using Depth-First-Search (DFS)...
 * 2. Whenever there is no land that we can traverse further, we update the Island counter by one...
 * 3. Since the subproblems have many similar the time complexity is reduced by using Dynamic Programming from O(4^n) to O(n^2) time...
*/