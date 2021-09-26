//This is the class where the games run

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GamePlay {

    private static Set<String> gameNames;

    public GamePlay() {
        gameNames = new HashSet<>(Arrays.asList("TicTacToe", "OrderAndChaos"));
    }

    public void gameplay(){
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
                if (gameNames.contains(gameName)) {
                    try{
                        AbstractBoardGame game = (AbstractBoardGame) Class.forName(gameName).getDeclaredConstructor().newInstance();
                        game.run();
                        break;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("No such game! Enter game name again: ");
                }
            }
            System.out.println("Do you want to play another game? (yes/no) ");
            if (!Utils.takeYes(in)) {
                System.out.println("Thank you!");
                break;
            }
        }
    }
}
