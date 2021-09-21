import java.util.List;
import java.util.Scanner;

public class OrderAndChaos extends TicTacToe {

    public OrderAndChaos() {
        board = new OACBoard();
        teamListInit(OACPlayer.class);
    }

    public OrderAndChaos(int r, int c, int winningCriterion) {
        board = new OACBoard(r, c, winningCriterion);
        teamListInit(OACPlayer.class);
    }

    public void run() {
        OrderAndChaos game = new OrderAndChaos();
        System.out.println("Welcome to Order And Chaos ver 0.1 ");
        game.display();
        Scanner in = new Scanner(System.in);
        while (true) {
            if (!game.makeMove(in, 3)) {
                game.endDisplay();
                break;
            }
        }
    }

    protected String[] checkOtherParameters(String[] parameters) throws Exception {
        if (!(parameters[2].equals("O") || parameters[2].equals("X"))) {
            throw new InvalidPieceException();
        }
        String[] otherParameters = new String[1];
        otherParameters[0] = parameters[2];
        return otherParameters;
    }

    protected int[] getFinalInput(int row, int col, String[] otherParameters) {
        return new int[]{row, col, otherParameters[0].equals("O") ? 0 : 1};
    }

    protected int[] inputExceptionHandler(Exception e, Scanner in, int expectedNum) {
        if (e instanceof InvalidPieceException) {
            System.out.println(e.getMessage());
            return takeInput(in, expectedNum);
        }
        else {
            return new int[0];
        }
    }

    protected boolean getRunningInputAndMove(AbstractPlayer player, Scanner in, int expectedParameterNum) {
        System.out.print("Player " + player.getName() + " Enter your move x, y, piece type(O/X): ");
        int[] parameters = takeInput(in, 3);
        if (parameters.length == 0) {
            return false;
        }
        player.put(parameters[0], parameters[1], parameters[2] == 0 ? "O" : "X");
        display();
        return true;
    }

}
