import java.util.Scanner;

/**
 * Creado por Carlos el 03/06/2017.
 */
public class MiniMax {
    static int numRec = 0;
    private Tree tablero;
    private final int MAX = 1;
    private final int MIN = 0;
    private int primerJugador;

    public MiniMax(Tree tree) throws Exception {
        Scanner s = new Scanner(System.in);
        this.tablero = tree;
        System.out.println("Introduce quien hará el primer movimiento (0 -> Min; 1-> Max)");
        primerJugador = s.nextInt();
        if (primerJugador != 1 && primerJugador != 0) {
            throw new Exception();
        }
    }

    public void game() {
        Tree resultado = new Tree();
        long t0;
        long tF;
        t0 = System.nanoTime();
        if (primerJugador == MAX) {
            resultado = MAX(tablero);
        }
        if (primerJugador == MIN) {
            resultado = MIN(tablero);
        }
        tF = System.nanoTime();
        System.out.println("Algoritmo Min-Max");
        System.out.println("El número que ha salido es: " + resultado.getValue());
        System.out.println("El número de recursiones ha sido: " + numRec);
        System.out.println("El tiempo que ha tardado en ejecutarse ha sido: " + (tF-t0) + " nanosegundos.");
        numRec = 0;
    }

    private Tree MAX(Tree board) {
        if (board.getRight().getValue() != null) {
            if (board.getRight().getValue() < board.getLeft().getValue()) {
                board.setValue(board.getLeft().getValue());
                return board;
            } else {
                board.setValue(board.getRight().getValue());
                return board;
            }
        } else {
            board.setLeft(MIN(board.getLeft()));
            numRec++;
            board.setRight(MIN(board.getRight()));
            numRec++;
            board = MAX(board);
            numRec++;
        }
        return board;
    }

    private Tree MIN(Tree board) {
        if (board.getRight().getValue() != null) {
            if (board.getRight().getValue() > board.getLeft().getValue()) {
                board.setValue(board.getLeft().getValue());
                return board;
            } else {
                board.setValue(board.getRight().getValue());
                return board;
            }
        } else {
            board.setLeft(MAX(board.getLeft()));
            numRec++;
            board.setRight(MAX(board.getRight()));
            numRec++;
            board = MIN(board);
            numRec++;
        }
        return board;
    }
}
