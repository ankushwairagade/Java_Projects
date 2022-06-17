package Sudoku;

public class SudokuSolver {


    public static void main(String[] args) {

        //  1-9
        int [][] board=
                      { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0} };


        if(solve(board))
        {
            displayboard(board);   // n^2
        }else{
            System.out.println("cannot solved");
        }


    }

    static boolean isSafe(int [][] board, int row ,int col, int num)
    {
        // check for row
        for (int i = 0; i < board.length; i++) {

            if(board[row][col] == num)  return false;

        }

        // check for column
        for(int [] nums: board)
        {
            if(nums[col] == num) return false;
        }

        // finding the chunk or small cube to be place element

        int sqrt = (int)(Math.sqrt(board.length));
        int rstart = row - row % sqrt;
        int cstart = col - col % sqrt;

        for (int r = rstart; r < rstart + sqrt; r++) {

            for (int c = cstart; c < cstart + sqrt ; c++) {

                if(board[r][c] == num)
                {
                    return false;
                }
            }
        }

        return true;
    }


    static boolean solve(int [][] board)
    {
        int n =board.length;
        int row= -1;
        int col= -1;
        boolean emptyleft=true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0)
                {
                    row=i;
                    col=j;
                    emptyleft=false;
                    break;
                }
            }

            if(!emptyleft)
            {
                break;
            }
        }

        if(emptyleft) return true;

        // backtrack       // re // bitwise

        for(int number =1 ; number <=9 ; number++)      // 9
        {
            if(isSafe(board,row,col,number))    // n^2
            {
                board[row][col]=number;
                if(solve(board))
                {
                    return true;
                }else{
                    board[row][col]=0;
                }
            }
        }
        return false;
    }

    static void displayboard(int [][] board)
    {
        for (int[] row: board) {
            for(int num: row)
            {
                System.out.print(num+"  ");
            }
            System.out.println();
        }
    }
}


/*
 *
 *  need to find time complexity
 *   im using two function in the SudokuSolver
 *
 *   Issafe is check the block(r,c) wheather is placeable or not
 *       T(n) = n + n + n^2
 *       T(n) = n^2
 *
 *  solve function is doing
 *       1 finding empty block board
 *       2 if block is find then let hit and try using backtracking
 *       3 loop 1-9 rule apply
 *           3.2  check if number is placed or not using Issafe(i) T(n^2)
 *
 *    total time complexity   T(n) = (9)^(n^2)
 *
 * */