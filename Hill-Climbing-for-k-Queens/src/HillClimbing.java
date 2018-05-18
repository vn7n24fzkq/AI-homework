import java.util.*;

public class HillClimbing {
    public static void main(String[] args) {
        System.out.println("Input count of queen");
        Scanner sc = new Scanner(System.in);
        new HillClimbing().run(sc.nextInt());
    }


    private int getHeuristiCost(int[] board) {
        int cost = 0;
        int limit = board.length - 1;
        for (int i = 0; i < limit; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] == board[j]) {
                    cost++;
                } else if (Math.abs(board[i] - board[j]) == Math.abs( i - j)) {
                    cost++;
                }
            }
        }
        return cost;
    }

    private int[] generateBoard(int[] board) {
        ArrayList<Integer> list = new ArrayList<>();

        int cost = getHeuristiCost(board);
        int newCost;

        int[] newBoard;
        newBoard = board.clone();

        for (int i = 0; i < board.length; i++) {
            list.clear();
            list.add(newBoard[i]);
            for (int j = 0; j < board.length; j++) {
                newBoard[i] = j;

                newCost = getHeuristiCost(newBoard);

                if (newCost == cost) {
                    list.add(j);
                }

                if (newCost < cost) {
                    list.clear();
                    list.add(j);
                    cost = newCost;
                }
            }
            Random r = new Random();
            newBoard[i] = list.get(r.nextInt(list.size()));
        }

        return newBoard;
    }

    private int[] nextState(int[] board) {
        int cost = getHeuristiCost(board);
        int[] tempBoard= generateBoard(board);
        if (getHeuristiCost(tempBoard) < cost) {
            return tempBoard;
        }
        return null;
    }

    public void run(int size) {
        long startTIme = System.currentTimeMillis();
        int[] board;
        int cc =0;
        board = Board.generateQueens(size);
        while (getHeuristiCost(board) != 0) {
            if (null == (board = nextState(board))) {
                //restart
                board = Board.generateQueens(size);
                cc++;
            }
        }
        Board.printBoard(Board.queensOnBorad(board));
        System.out.println("reset count+cc);
        System.out.println("Spend TIme : " + (System.currentTimeMillis()-startTIme) + "ms");
    }
}


