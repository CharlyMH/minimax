import java.util.Scanner;

/**
 * Creado por Carlos el 03/06/2017.
 */
public class MiniMax {
    private static int numRec = 0;
    private Tree tablero;
    private static final boolean MAX = true;
    private static final boolean MIN = false;
    private boolean primerJugador;

    public MiniMax(Tree tree) throws Exception {
        Scanner s = new Scanner(System.in);
        this.tablero = tree;
        System.out.println("Introduce quien hará el primer movimiento (false -> Min; true-> Max)");
        primerJugador = s.nextBoolean();
    }

    public void game() {
        long t0;
        long tF;
        System.out.println("Algoritmo Min-Max");
        t0 = System.nanoTime();
        System.out.println("El número que ha salido es: " + Mini_Max(tablero, primerJugador));
        tF = System.nanoTime();
        System.out.println("El número de recursiones ha sido: " + numRec);
        System.out.println("El tiempo que ha tardado en ejecutarse ha sido: " + (tF - t0) + " nanosegundos.");
        numRec = 0;

        System.out.println("Algoritmo Alpha-Beta");
        t0 = System.nanoTime();
        System.out.println("El número que ha salido es: " + Alpha_Beta(tablero, Integer.MIN_VALUE, Integer.MAX_VALUE, primerJugador));
        tF = System.nanoTime();
        System.out.println("El número de recursiones ha sido: " + numRec);
        System.out.println("El tiempo que ha tardado en ejecutarse ha sido: " + (tF - t0) + " nanosegundos.");
    }

    private int Mini_Max(Tree board, boolean player) {
        int i;
        int j;
        if (board.isLeaf()) {
            return board.getValue();
        }
        if (player == MAX) {
            i = Mini_Max(board.getRight(), MIN);
            j = Mini_Max(board.getLeft(), MIN);
            numRec += 2;
            return maximizar(i, j);
        } else {
            i = Mini_Max(board.getRight(), MAX);
            j = Mini_Max(board.getLeft(), MAX);
            numRec += 2;
            return minimizar(i, j);
        }
    }

    private int Alpha_Beta(Tree board, int a, int b, boolean player) {
        if (board.isLeaf()) {
            return board.getValue();
        } else if (player == MAX) {
            a = maximizar(a, Alpha_Beta(board.getRight(), a, b, MIN));
            numRec++;
            if (a < b) {
                a = maximizar(a, Alpha_Beta(board.getLeft(), a, b, MIN));
                numRec++;
            }
            return a;
        } else {
            b = minimizar(b, Alpha_Beta(board.getRight(), a, b, MAX));
            numRec++;
            if (a < b) {
                b = minimizar(b, Alpha_Beta(board.getLeft(), a, b, MAX));
                numRec++;
            }
            return b;
        }
    }

    private int maximizar(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    private int minimizar(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }
}
