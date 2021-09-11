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
        Scanner in =new Scanner(System.in);
        while (true) {
            if (!game.makeMove(in)) {
                break;
            }
        }
    }

    public void display() {
        board.display();;
    }

    public void clear() {
        board.clear();
    }

    public int[] takeInput(Scanner in) {
        String input = in.nextLine();
        String[] parameters = input.split("([^0-9]*,[^0-9]*)");
        //TODO: HANDLE ERROR INPUT: INVALID OR OUT OF BOUND
        int row = Integer.parseInt(parameters[0]), col = Integer.parseInt(parameters[1]);
        while (!board.isValidPos(row, col)) {
            System.out.print("Position taken! Switch to another position: ");
            input = in.nextLine();
            parameters = input.split("( *, *)");
            row = Integer.parseInt(parameters[0]);
            col = Integer.parseInt(parameters[1]);
        }
        return new int[]{row, col};
    }

    public boolean makeMove(Scanner in) {
        int playerNum = playerList.size();

        for (int i = 0; i < playerNum; i++) {
            Player thisPlayer = playerList.get(i);

            System.out.print("Player " + thisPlayer.getName() + " Enter your move: ");
            int[] parameters = takeInput(in);
            thisPlayer.put(parameters[0], parameters[1]);
            display();

            if (thisPlayer.isWinner()) {
                System.out.print("Player " + thisPlayer.getName() + " won this game! Want another round? ");
                if (!ifContinue(in)) {
                    return false;
                }
                else break;
            }
            else if (board.isDraw()) {
                System.out.print("Draw! Want another round? ");
                if (!ifContinue(in)) {
                    return false;
                }
                else break;
            }
        }
        return true;
    }

    private boolean ifContinue(Scanner in) {
        String continueStr = in.nextLine();
        //TODO: HANDLE ERROR INPUT
        if (continueStr.equalsIgnoreCase("yes")) {
            this.clear();
            display();
            return true;
        }
        else {
            return false;
        }
    }
}
