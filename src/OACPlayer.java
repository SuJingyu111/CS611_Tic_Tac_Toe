public class OACPlayer extends TicTacToePlayer {

    public OACPlayer(String name, AbstractBoard board) {
        super();
        this.name = name;
        this.board = board;
        pieces.put("O", new Piece("O", this));
        pieces.put("X", new Piece("X", this));
    }

}
