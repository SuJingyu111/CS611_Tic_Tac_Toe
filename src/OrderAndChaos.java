import java.util.List;
import java.util.Scanner;

public class OrderAndChaos extends TicTacToe {
    //Game message display needs to be altered
    private final int parameterNum = 3;

    public OrderAndChaos() {
        board = new OACBoard();
        //TODO: MIGHT BE CHANGED
        teamListInit(OACPlayer.class);
    }

    public OrderAndChaos(int r, int c, int winningCriterion) {
        board = new OACBoard(r, c, winningCriterion);
        //TODO: MIGHT BE CHANGED
        teamListInit(OACPlayer.class);
    }

    @Override
    protected void printOpeningMessage() {
        System.out.println("Welcome to Order And Chaos ver 0.1 ");
    }

    @Override
    protected String[] checkOtherParameters(String[] parameters) throws Exception {
        if (!(parameters[2].equals("O") || parameters[2].equals("X"))) {
            throw new InvalidPieceException();
        }
        String[] otherParameters = new String[1];
        otherParameters[0] = parameters[2];
        return otherParameters;
    }

    @Override
    protected int[] getFinalInput(int row, int col, String[] otherParameters) {
        return new int[]{row, col, otherParameters[0].equals("O") ? 0 : 1};
    }

    @Override
    protected int[] inputExceptionHandler(Exception e, Scanner in, int expectedNum) {
        if (e instanceof InvalidPieceException) {
            System.out.println(e.getMessage());
            return takeInput(in, expectedNum);
        }
        else {
            return new int[0];
        }
    }

    @Override
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
