public class OACBoard extends TicTacToeBoard{

    public OACBoard() {
        super(6, 6, 5);
        pieceNameMaxLen = 1;
    }

    public OACBoard(int r, int c, int winningCriterion) {
        super(r, c, winningCriterion);
        pieceNameMaxLen = 1;
    }

}
