import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe extends AbstractBoardGame {

    public TicTacToe() {
        super(new Board());
        teamList = new ArrayList<>();
        teamList.add(new ArrayList<>());
        teamList.get(0).add(new Player("O", board));
        teamList.add(new ArrayList<>());
        teamList.get(1).add(new Player("X", board));
    }

    public void run() {
        TicTacToe game = new TicTacToe();
        System.out.println("Welcome to TicTacToe ver 0.1");
        game.display();
        Scanner in = new Scanner(System.in);
        while (true) {
            if (!game.makeMove(in)) {
                game.endDisplay();
                break;
            }
        }
    }

    public void display() {
        board.display();
    }

    public void clear() {
        board.clear();
    }

    public void endDisplay() {
        System.out.println("End of the game!");
        for (List<Player> team : teamList) {
            for (Player p : team) {
                System.out.println("Player " + p.getName() + " won " + p.getWinCnt() + " time(s). ");
            }
        }
    }

    private int[] takeInput(Scanner in) {
        String input = in.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            return new int[0];
        }
        String[] parameters = input.split("( *, *)");
        try {
            int row = Integer.parseInt(parameters[0]), col = Integer.parseInt(parameters[1]);
            if (!board.inBound(row, col)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            while (!board.isValidPos(row, col)) {
                System.out.print("Position taken! Switch to another position: ");
                input = in.nextLine();
                parameters = input.split("( *, *)");
                row = Integer.parseInt(parameters[0]);
                col = Integer.parseInt(parameters[1]);
            }
            return new int[]{row, col};
        } catch (NumberFormatException e) {
            System.out.print("Invalid input! Try again: ");
            return takeInput(in);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Number out of bound! Try again: ");
            return takeInput(in);
        }

    }

    private boolean makeMove(Scanner in) {
        for (List<Player> team : teamList) {
            for (Player thisPlayer : team) {
                System.out.print("Player " + thisPlayer.getName() + " Enter your move x, y: ");
                int[] parameters = takeInput(in);
                if (parameters.length == 0) {
                    return false;
                }
                thisPlayer.put(parameters[0], parameters[1]);
                display();

                if (thisPlayer.isWinner()) {
                    System.out.print("Player " + thisPlayer.getName() + " won this game! Want another round? (Yes/No) ");
                    if (!ifContinue(in)) {
                        return false;
                    } else break;
                } else if (board.isDraw()) {
                    System.out.print("Draw! Want another round? (Yes/No) ");
                    if (!ifContinue(in)) {
                        return false;
                    } else break;
                }
            }
        }
        return true;
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
