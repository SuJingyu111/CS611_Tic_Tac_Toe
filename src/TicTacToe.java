import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe extends AbstractBoardGame {

    private final int parameterNum = 2;

    int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public TicTacToe() {
        super(new Board());
        teamListInit(TicTacToePlayer.class, 2, 1, new String[]{"O", "X"});
    }

    public TicTacToe(int r, int c, int winningCriterion) {
        super(new Board(r, c, winningCriterion));
        teamListInit(TicTacToePlayer.class,2, 1, new String[]{"O", "X"});
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

    protected void teamListInit(Class<?> playerClass, int teamNum, int playerNum, String[] teamNames) {
        teamList = new ArrayList<>();
        for (int i = 0; i < teamNum; i++) {
            Team newTeam = new Team(playerNum, playerClass, new String[]{teamNames[i]}, board);
            newTeam.setTeamName(teamNames[i]);
            teamList.add(newTeam);
        }
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
            System.out.println(team.getTeamName() + " won " + team.getWinCnt() + " time(s). ");
        }
    }

    protected boolean makeMove(Scanner in, int expectedParameterNum) {
        for (Team team : teamList) {
            System.out.println(team.getTeamName());
            if (!getRunningInputAndMove(team, in, expectedParameterNum)) {
                return false;
            }
            int continuingStatus = getContinuingStatus(team.getRepresentingPlayer(), in);
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
        String[] parameters = Utils.getInputStrParameters(in);
        if (parameters.length == 0) {
            return new int[0];
        }
        try {
            Utils.parameterNumberCheck(expectedNum, parameters);
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

    protected boolean getRunningInputAndMove(Team team, Scanner in, int expectedParameterNum) {
        AbstractPlayer player = team.getRepresentingPlayer();
        System.out.print("Player " + team.getTeamName() + " Enter your move x, y: ");
        int[] parameters = takeInput(in, expectedParameterNum);
        if (parameters.length == 0) {
            return false;
        }
        player.put(parameters[0], parameters[1], team.getTeamName().equals("O") ? "O" : "X");
        display();
        return true;
    }

    protected String[] checkOtherParameters(String[] parameters) throws Exception { return new String[0]; }

    protected int[] getFinalInput(int row, int col, String[] otherParameters) {
        return new int[]{row, col};
    }

    protected int[] inputExceptionHandler(Exception e, Scanner in, int expectedNum) { return new int[0]; }

    //TODO: Alter this for OAC
    private int getContinuingStatus(AbstractPlayer thisPlayer, Scanner in) {
        if (isWinner(thisPlayer)) {
            System.out.print("Player " + thisPlayer.getName() + " won this game! Want another round? (Yes/No) ");
            if (!Utils.takeYes(in)) {
                return -1;
            } else {
                clear();
                display();
                return 1;
            }
        } else if (board.isDraw()) {
            System.out.print("Draw! Want another round? (Yes/No) ");
            if (!Utils.takeYes(in)) {
                return -1;
            } else {
                clear();
                display();
                return 1;
            }
        }
        return 0;
    }

    public boolean isWinner(AbstractPlayer thisPlayer) {
        int[] parameters = board.getParameters();
        int[] playerLastPos = ((TicTacToePlayer)thisPlayer).getLastPos();
        int r = parameters[0], c = parameters[1];
        int step1 = 0, step2 = 0, winningCriterion = board.getWinningCriterion();
        for (int i = 0; i < 4; i++) {
            int[] direction1 = directions[2 * i], direction2 = directions[2 * i + 1];
            step1 = getLength(direction1, r, c ,playerLastPos);
            step2 = getLength(direction2, r, c, playerLastPos);
            if (step1 + step2 + 1 >= winningCriterion) {
                thisPlayer.incrementWinCnt();
                return true;
            }
        }
        return false;
    }

    protected int getLength(int[] direction, int r, int c, int[] playerLastPos) {
        int step = 0, lastMoveRow = playerLastPos[0], lastMoveCol = playerLastPos[1];
        while (lastMoveRow + (step + 1) * direction[0] < r && lastMoveRow + (step + 1) * direction[0] >= 0
                && lastMoveCol + (step + 1) * direction[1] < c && lastMoveCol + (step + 1) * direction[1] >= 0) {
            if (board.getPieceName(lastMoveRow + (step + 1) * direction[0], lastMoveCol + (step + 1) * direction[1])
                    .equals(board.getPieceName(lastMoveRow, lastMoveCol))) {
                step++;
            }
            else break;
        }
        return step;
    }
}
