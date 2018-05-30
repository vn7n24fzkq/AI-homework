import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AlphaBetaPruning {
    static int depth, branch;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            depth = Integer.valueOf(br.readLine());
            branch = Integer.valueOf(br.readLine());
            String[] tempNodes = br.readLine().split(",");
            int[] tempNumbers = new int[tempNodes.length];
            for (int i = 0; i < tempNodes.length; i++) {
                tempNumbers[i] = Integer.valueOf(tempNodes[i]);
            }
            AlphaBetaTree tree = new AlphaBetaTree(branch, depth, tempNumbers);
            System.out.println("root :" + tree.pruning());
            System.out.println("修剪次數 :" + tree.cut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AlphaBetaTree {
    Node root;
    int branch;
    int depth;
    ArrayList<Node> lastNodes = new ArrayList();
    int cut = 0;
    public AlphaBetaTree(int branch, int depth, int[] nodes) {
        this.branch = branch;
        this.depth = depth;
        this.root = new Node();
        root.alpha = Integer.MIN_VALUE;
        root.beta = Integer.MAX_VALUE;
        this.instanceTree(1, root);
        insertNumbers(nodes);
    }

    public int pruning() {
        System.out.print("被修剪的數字");
        int i = pruning(root,Integer.MIN_VALUE,Integer.MAX_VALUE,true);
        System.out.println();
        return i;
    }

    private Integer pruning(Node currentNode,int alpha,int beta ,boolean max) {
        int value;
        if(currentNode.childs == null)
            return currentNode.alpha;
        if(max){
            value = alpha;
            for (int i = 0; i < currentNode.childs.length; i++) {
                int childValue = pruning(currentNode.childs[i],value,beta,false);
                value = Math.max(value,childValue);
                if (beta <= value){
                    for(int j = i+1;j < currentNode.childs.length; j++){
                        if(currentNode.childs[j].alpha!=null)
                            System.out.print( currentNode.childs[j].alpha+",");
                        else
                            System.out.print(value);
                        cut++;
                    }
                    break;
                }
            }
        }else{
            value = beta;
            for (int i = 0; i < currentNode.childs.length; i++) {
                int childValue =  pruning(currentNode.childs[i],alpha,value,true);
                value = Math.min(value,childValue);
                if(value <= alpha){
                    for(int j = i+1;j < currentNode.childs.length; j++){
                        if(currentNode.childs[j].alpha!=null)
                            System.out.print( currentNode.childs[j].alpha + ",");
                        else
                            System.out.print(value);
                        cut++;
                    }
                    break;
                }
            }
        }
        return value;
    }

    public void instanceTree(int currentDepth, Node currentNode) {
        if (currentDepth == this.depth) {
            lastNodes.add(currentNode);
            return;
        }
        currentNode.childs = new Node[branch];
        for (int i = 0; i < branch; i++) {
            currentNode.childs[i] = new Node();
            currentNode.childs[i].parent = currentNode;
            instanceTree(currentDepth + 1, currentNode.childs[i]);
        }
    }

    public void insertNumbers(int[] numbers) {
        for (int i = 0; i < lastNodes.size(); i++) {
            lastNodes.get(i).alpha = numbers[i];
        }
    }

    class Node {
        public Node parent;
        public Node[] childs;
        public Integer alpha, beta;

        private Integer getMin() {
            if (childs == null)
                return null;
            int min = Integer.MAX_VALUE;
            for (Node n : childs) {
                if (n.alpha > min)
                    min = n.alpha;
            }
            return min;
        }

        private Integer getMax() {
            if (childs == null)
                return null;
            int max = Integer.MIN_VALUE;
            for (Node n : childs) {
                if (n.alpha < max)
                    max = n.alpha;
            }
            return max;
        }
    }
}
