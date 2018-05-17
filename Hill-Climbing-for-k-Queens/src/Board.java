import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    boolean[][] board;
    static boolean[] emptyColums;
    public Board(int size) {
        board = new boolean[size][size];
        emptyColums = new boolean[size];
        HillClimbing hill = new HillClimbing();
        queensOnBorad( hill.run(size));
    }

    public static int[] generateQueens(int size) {
        Random r = new Random();
        int[] queenState= new int[size];
        for (int i = 0; i < queenState.length; i++) {
            queenState[i] = r.nextInt(queenState.length);
        }
        return queenState;
    }
    public ArrayList<Integer> getEmptyColums(int[] queenState){
        Arrays.fill(emptyColums,false);
        for (int i = 0; i < queenState.length; i++) {
            emptyColums[queenState[i]] = true;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < emptyColums.length; i++){
            if(!emptyColums[i]){
                list.add(i);
            }
        }
        return list;
    }
    public void queensOnBorad(int[] queens){
        for(int i = 0;i < board.length;i++){
            Arrays.fill(board[i],false);
        }

        for(int i = 0;i < queens.length;i++){
            board[i][queens[i]] = true;
        }
    }


    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    System.out.print("\u001B[32m" + "O" + "\u001B[0m");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}
