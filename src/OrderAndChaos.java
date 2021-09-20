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
            if (!game.makeMove(in)) {
                game.endDisplay();
                break;
            }
        }
    }

    protected int[] takeInput(Scanner in) {
        String input = in.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            return new int[0];
        }
        String[] parameters = input.split("( *, *)");
        try {
            if (parameters.length < 3) {
                throw new NumberFormatException();
            }
            int row = Integer.parseInt(parameters[0]), col = Integer.parseInt(parameters[1]);
            String priceTypeStr = parameters[2];
            while (!(priceTypeStr.equals("O") || priceTypeStr.equals("X"))) {
                System.out.print("Invalid piece type! ");
                input = in.nextLine();
                parameters = input.split("( *, *)");
                row = Integer.parseInt(parameters[0]);
                col = Integer.parseInt(parameters[1]);
                priceTypeStr = parameters[2];
            }
            if (!board.inBound(row, col)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            while (!board.isValidPos(row, col)) {
                System.out.print("Position taken! Input row, column and piece type again: ");
                input = in.nextLine();
                parameters = input.split("( *, *)");
                row = Integer.parseInt(parameters[0]);
                col = Integer.parseInt(parameters[1]);
                priceTypeStr = parameters[2];
            }
            return new int[]{row, col, priceTypeStr.equals("O") ? 0 : 1};
        } catch (NumberFormatException e) {
            System.out.print("Invalid input! Try again: ");
            return takeInput(in);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Number out of bound! Try again: ");
            return takeInput(in);
        }
    }

    protected boolean getRunningInputAndRun(AbstractPlayer player, Scanner in) {
        System.out.print("Player " + player.getName() + " Enter your move x, y, piece type(O/X): ");
        int[] parameters = takeInput(in);
        if (parameters.length == 0) {
            return false;
        }
        player.put(parameters[0], parameters[1], parameters[2] == 0 ? "O" : "X");
        display();
        return true;
    }

}
