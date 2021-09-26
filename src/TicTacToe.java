import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe extends AbstractBoardGame {

    private final int parameterNum = 2;

    public TicTacToe() {
        super(new Board());
        teamListInit(TicTacToePlayer.class);
    }

    public TicTacToe(int r, int c, int winningCriterion) {
        super(new Board(r, c, winningCriterion));
        teamListInit(TicTacToePlayer.class);
    }

    @Override
    public void run() {
        printOpeningMessage();
        display();
        Scanner in = new Scanner(System.in);
        while (true) {
            if (!makeMove(in, parameterNum)) {
                endDisplay();
                break;
            }
        }
    }

    //This method can be put in abstract.
    protected void teamListInit(Class<?> playerClass) {
        teamList = new ArrayList<>();
        teamList.add(new Team(1, playerClass, new String[]{"1"}, board));
        teamList.add(new Team(1, playerClass, new String[]{"2"}, board));
    }

    private AbstractPlayer getPlayer(Object obj) {
        return (AbstractPlayer) obj;
    }

    protected void printOpeningMessage() {
        System.out.println("Welcome to TicTacToe ver 0.2 ");
    }

    @Override
    public void display() {
        board.display();
    }

    @Override
    public void clear() {
        board.clear();
    }

    @Override
    public void endDisplay() {
        System.out.println("End of the game!");
        for (Team team : teamList) {
            AbstractPlayer p = team.getRepresentingPlayer();
            System.out.println("Player " + p.getName() + " won " + ((TicTacToePlayer)p).getWinCnt() + " time(s). ");
        }
    }

    protected boolean makeMove(Scanner in, int expectedParameterNum) {
        for (Team team : teamList) {
            //TODO: Edit for team play
            AbstractPlayer thisPlayer = team.getRepresentingPlayer();
            if (!getRunningInputAndMove(thisPlayer, in, expectedParameterNum)) {
                return false;
            }
            int continuingStatus = getContinuingStatus(thisPlayer, in);
            if (continuingStatus == 1) {
                return true;
            }
            else if (continuingStatus == -1) {
                return false;
            }
        }
        return true;
    }

    protected int[] takeInput(Scanner in, int expectedNum) {
        String[] parameters = getInputStrParameters(in);
        if (parameters.length == 0) {
            return new int[0];
        }
        try {
            parameterNumberCheck(expectedNum, parameters);
            int row = Integer.parseInt(parameters[0]), col = Integer.parseInt(parameters[1]);
            if (!board.inBound(row, col)) {
                throw new ArrayIndexOutOfBoundsException("Number out of bound! Try again: ");
            }
            if (!board.isValidPos(row, col)) {
                throw new PositionTakenException("Position taken! Switch to another position: ");
            }
            String[] otherParameters = checkOtherParameters(parameters);
            return getFinalInput(row, col, otherParameters);
        }
        catch (NumberFormatException e) {
            System.out.print("Invalid input! Try again: ");
            return takeInput(in, expectedNum);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(e.getMessage());
            //e.printStackTrace();
            return takeInput(in, expectedNum);
        }
        catch (PositionTakenException e) {
            System.out.println(e.getMessage());
            return takeInput(in, expectedNum);
        }
        catch (Exception e) {
            return inputExceptionHandler(e, in, expectedNum);
        }
    }

    protected boolean getRunningInputAndMove(AbstractPlayer player, Scanner in, int expectedParameterNum) {
        System.out.print("Player " + player.getName() + " Enter your move x, y: ");
        int[] parameters = takeInput(in, expectedParameterNum);
        if (parameters.length == 0) {
            return false;
        }
        player.put(parameters[0], parameters[1], player.getName().equals("1") ? "O" : "X");
        display();
        return true;
    }

    protected void parameterNumberCheck(int expectedNum, String[] parameters) throws ArrayIndexOutOfBoundsException {
        if (expectedNum != parameters.length) {
            throw new ArrayIndexOutOfBoundsException("Incorrect number of parameters! Expect: " + expectedNum + ", get: " + parameters.length + ", try again: ");
        }
    }

    protected String[] checkOtherParameters(String[] parameters) throws Exception { return new String[0]; }

    protected int[] getFinalInput(int row, int col, String[] otherParameters) {
        return new int[]{row, col};
    }

    protected int[] inputExceptionHandler(Exception e, Scanner in, int expectedNum) { return new int[0]; }

    private String[] getInputStrParameters(Scanner in) {
        String input = in.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            return new String[0];
        }
        return input.split("( *, *)");
    }

    private int getContinuingStatus(AbstractPlayer thisPlayer, Scanner in) {
        if (thisPlayer.isWinner()) {
            System.out.print("Player " + thisPlayer.getName() + " won this game! Want another round? (Yes/No) ");
            if (!ifContinue(in)) {
                return -1;
            } else return 1;
        } else if (board.isDraw()) {
            System.out.print("Draw! Want another round? (Yes/No) ");
            if (!ifContinue(in)) {
                return -1;
            } else return 1;
        }
        return 0;
    }

    private boolean ifContinue(Scanner in) {
        String continueStr = in.nextLine();
        try {
            if (continueStr.equalsIgnoreCase("yes")) {
                this.clear();
                display();
                return true;
            }
            else if (continueStr.equalsIgnoreCase("no")) {
                return false;
            }
            else {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException e) {
            System.out.print("Invalid input! Try again: ");
            return ifContinue(in);
        }
    }
}
