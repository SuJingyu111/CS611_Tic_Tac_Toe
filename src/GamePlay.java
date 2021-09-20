//This is the class where the games run

public class GamePlay {

    public static void main (String[] args) {
        //TODO: Play Continuous Round
        //TODO: What if team take turns?
        AbstractBoardGame game = new TicTacToe();
        game.run();
        game = new OrderAndChaos();
        game.run();
    }
}
