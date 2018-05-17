import java.util.Arrays;

public class HillClimbing {
    public int[] run(int size){
        int[] queens = Board.generateQueens(size) ;
        int cost =getHeuristicost(queens);
        while( 0 < cost){
            boolean isEnd = false;
            int tempCost = cost;
            for(int col = 0; col < size && !isEnd;col++) {

                for (int row = 0; row < size; row++) {
                    if (row == queens[col])
                        continue;
                    int[] array = Arrays.copyOf(queens,size);
                    array[col] = row;
                    int newCost = getHeuristicost(array);
                    if(cost > newCost){
                        queens[col] = row;
                        cost = newCost;
                        isEnd = true;
                        break;
                    }
                }
            }
            if(tempCost == cost){
                queens = Board.generateQueens(size);
            }
        }
        if(cost == 0){
            return queens;
        }else{
            return null;
        }
    }
    private static int getHeuristicost(int[] queens){
        int cost = 0;
        for(int i = 0;i<queens.length;i++){
            for(int j = i + 1;j < queens.length;j++){
                if(queens[i] == queens[j]) {
                    cost++;
                }else if(Math.abs(queens[i]-queens[j]) == j-i){
                    cost++;
                }
            }
        }
        return cost;
    }
}
