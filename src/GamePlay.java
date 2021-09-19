//This is the class where the games run

public class GamePlay {

    public static void main (String[] args) {
        AbstractBoardGame game = new TicTacToe();
        game.run();
    }
}
