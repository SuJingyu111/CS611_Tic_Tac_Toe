class TicTacToePlayer extends AbstractPlayer{

    private int lastMoveRow, lastMoveCol;

    public TicTacToePlayer() {
        super();
        lastMoveRow = -1;
        lastMoveCol = -1;
        winCnt = 0;
    }

    public TicTacToePlayer(String name, AbstractBoard board) {
        super(name, board);
        lastMoveRow = -1;
        lastMoveCol = -1;
        winCnt = 0;
        String pieceName = name.equals("O") ? "O" : "X";
        pieces.put(pieceName, new Piece(pieceName, this));
    }

    @Override
    public void put(int row, int col, String pieceName) {
        lastMoveRow = row;
        lastMoveCol = col;
        board.put(row, col, pieces.get(pieceName));
    }

    public int[] getLastPos() {
        return new int[]{lastMoveRow, lastMoveCol};
    }
}
