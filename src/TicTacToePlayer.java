class TicTacToePlayer extends AbstractPlayer{

    protected int lastMoveRow, lastMoveCol;

    protected int winCnt;

    protected final int[][] directions;

    public TicTacToePlayer() {
        super();
        lastMoveRow = -1;
        lastMoveCol = -1;
        winCnt = 0;
        directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    }

    public TicTacToePlayer(String name, AbstractBoard board) {
        super(name, board);
        lastMoveRow = -1;
        lastMoveCol = -1;
        winCnt = 0;
        directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        String pieceName = name.equals("1") ? "O" : "X";
        pieces.put(pieceName, new TicTacToePiece(pieceName, this));
    }

    public int getWinCnt() { return winCnt; }

    @Override
    public void put(int row, int col, String pieceName) {
        lastMoveRow = row;
        lastMoveCol = col;
        board.put(row, col, pieces.get(pieceName));
    }

    @Override
    public boolean isWinner() {
        int[] parameters = board.getParameters();
        int r = parameters[0], c = parameters[1];
        int step1 = 0, step2 = 0, winningCriterion = board.getWinningCriterion();
        for (int i = 0; i < 4; i++) {
            int[] direction1 = directions[2 * i], direction2 = directions[2 * i + 1];
            step1 = getLength(direction1, r, c);
            step2 = getLength(direction2, r, c);
            if (step1 + step2 + 1 >= winningCriterion) {
                winCnt++;
                return true;
            }
        }
        return false;
    }

    protected int getLength(int[] direction, int r, int c) {
        int step = 0;
        while (lastMoveRow + (step + 1) * direction[0] < r && lastMoveRow + (step + 1) * direction[0] >= 0
                && lastMoveCol + (step + 1) * direction[1] < c && lastMoveCol + (step + 1) * direction[1] >= 0) {
            if (board.getPieceOwner(lastMoveRow + (step + 1) * direction[0], lastMoveCol + (step + 1) * direction[1]) == this) {
                step++;
            }
            else break;
        }
        return step;
    }
}
