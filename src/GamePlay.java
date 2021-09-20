//This is the class where the games run

import java.util.Scanner;

public class GamePlay {

    static String[] gameNames = new String[]{"TicTacToe", "OrderAndChaos"};

    public static void main (String[] args) {
        //TODO: What if team take turns?
        //TODO: Add comprehensible comments
        while (true) {
            System.out.println("Games available: ");
            for (String gameName : gameNames) {
                System.out.println(gameName + " ");
            }
            System.out.println();
            System.out.println("What game would you want to play? ");
            Scanner in = new Scanner(System.in);
            while (true) {
                String gameName = in.nextLine();
                if (gameName.equalsIgnoreCase("TicTacToe")) {
                    AbstractBoardGame game = new TicTacToe();
                    game.run();
                    break;
                }
                else if (gameName.equalsIgnoreCase("OrderAndChaos")) {
                    AbstractBoardGame game = new OrderAndChaos();
                    game.run();
                    break;
                }
                else {
                    System.out.println("No such game! Enter game name again: ");
                }
            }
            System.out.println("Do you want to play another game? (yes/no) ");
            boolean ifExit = false;
            while (true) {
                String ifAnotherGame = in.nextLine();
                if (ifAnotherGame.equalsIgnoreCase("yes")) {
                    continue;
                }
                else if (ifAnotherGame.equalsIgnoreCase("no")) {
                    ifExit = true;
                    break;
                }
                else {
                    System.out.println("Invalid Input! Try again! ");
                }
            }
            if (ifExit) {
                System.out.println("Thank you!");
                break;
            }
        }
    }
}
