import java.util.Scanner;

public class Run {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
       Board b =  new Board(sc.nextInt());
       b.printBoard();
    }
}
