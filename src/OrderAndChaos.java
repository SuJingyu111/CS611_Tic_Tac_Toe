import java.util.Scanner;

public class OrderAndChaos extends TicTacToe {
    //TODO: Game message display needs to be altered
    private final int parameterNum = 3;

    public OrderAndChaos() {
        board = new Board(6, 6, 5);
        teamListInit(OACPlayer.class, 2, 1, new String[]{"Order", "Chaos"});
    }

    public OrderAndChaos(int r, int c, int winningCriterion) {
        board = new Board(r, c, winningCriterion);
        teamListInit(OACPlayer.class, 2, 1, new String[]{"Order", "Chaos"});
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
    protected boolean getRunningInputAndMove(Team team, Scanner in, int expectedParameterNum) {
        System.out.print("Player " + team.getTeamName() + " Enter your move x, y, piece type(O/X): ");
        int[] parameters = takeInput(in, 3);
        if (parameters.length == 0) {
            return false;
        }
        team.getRepresentingPlayer().put(parameters[0], parameters[1], parameters[2] == 0 ? "O" : "X");
        display();
        return true;
    }

}
