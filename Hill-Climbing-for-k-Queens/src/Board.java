import java.util.Arrays;
import java.util.Random;

public class Board {

    public static int[] generateQueens(int size) {
        int[] board = new int[size];
        Random r = new Random();
        for (int i = 0; i < board.length; i++) {
            board[i] = r.nextInt(board.length);
        }
        return board;
    }

    public static boolean[][] queensOnBorad(int[] queens) {
        boolean[][] b = new boolean[queens.length][queens.length];
        for (int i = 0; i < b.length; i++) {
            Arrays.fill(b[i], false);
        }

        for (int i = 0; i < queens.length; i++) {
            b[i][queens[i]] = true;
        }
        return b;
    }


    public static void printBoard(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    System.out.print("\u001B[32m" + "O" + "\u001B[0m");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
