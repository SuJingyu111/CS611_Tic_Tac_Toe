public class OACPlayer extends TicTacToePlayer {

    public OACPlayer(String name, AbstractBoard board) {
        super();
        this.name = name;
        this.board = board;
        pieces.put("O", new OACPiece("O" + name, this));
        pieces.put("X", new OACPiece("X" + name, this));
    }

    protected int getLength(int[] direction, int r, int c) {
        int step = 0;
        String pieceName = board.getPieceName(lastMoveRow, lastMoveCol);
        while (lastMoveRow + (step + 1) * direction[0] < r && lastMoveRow + (step + 1) * direction[0] >= 0
                && lastMoveCol + (step + 1) * direction[1] < c && lastMoveCol + (step + 1) * direction[1] >= 0) {
            if (board.getPieceName(lastMoveRow + (step + 1) * direction[0], lastMoveCol + (step + 1) * direction[1]).equals(pieceName)
                    && board.getPieceOwner(lastMoveRow + (step + 1) * direction[0], lastMoveCol + (step + 1) * direction[1]) == this) {
                step++;
            }
            else break;
        }
        return step;
    }
}
