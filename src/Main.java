import java.util.*;

/**
 * Creado por Carlos el 03/06/2017.
 */
public class Main {
    static ArrayList<Integer> nums = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> auxList = new ArrayList<>();
        Random r = new Random(System.nanoTime());
        Tree tree = new Tree();
        int aux;

        System.out.println("Introduce la profundidad del árbol");
        int depth = s.nextInt();

        if(depth <1){
            throw new Exception();
        }

        int e = potencia(depth);

        for (int i = 1; i <= e; i++){
            auxList.add(i);
        }

        for (int i = 0; i < e; i++){
            aux = r.nextInt(auxList.size());
            nums.add(auxList.get(aux));
            auxList.remove(aux);
        }

        tree = makeTree(tree, depth);

        System.out.printf(tree.toString());

        System.out.println();

        MiniMax juego = new MiniMax(tree);
        juego.game();
    }


    private static Tree makeTree(Tree tree, int depth){
        if (depth < 1){
            tree.setValue(nums.get(0));
            nums.remove(0);
            return tree;
        }
        else {
            tree.setRight(new Tree());
            tree.setLeft(new Tree());
            tree.setRight(makeTree(tree.getRight(),depth-1));
            tree.setLeft(makeTree(tree.getLeft(),depth-1));
        }
        return tree;
    }


    private static int potencia(int a){
        int b = 1;
        for (int i = 0; i < a ; i++){
            b=2*b;
        }
        return b;
    }
}