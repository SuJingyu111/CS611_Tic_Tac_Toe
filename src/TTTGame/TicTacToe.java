package TTTGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    private Board board;

    private List<Player> playerList;

    public TicTacToe() {
        board = new Board();
        playerList = new ArrayList<>();
        playerList.add(new Player("O", board));
        playerList.add(new Player("X", board));
    }

    public static void main(String[] args) {
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
        for (Player p : playerList) {
            System.out.println("Player " + p.getName() + " won " + p.getWinCnt() + " time(s). ");
        }
    }

    public int[] takeInput(Scanner in) {
        String input = in.nextLine();
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

    public boolean makeMove(Scanner in) {
        for (Player thisPlayer : playerList) {
            System.out.print("Player " + thisPlayer.getName() + " Enter your move x, y: ");
            int[] parameters = takeInput(in);
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
