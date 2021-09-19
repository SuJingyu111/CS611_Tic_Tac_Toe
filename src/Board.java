class Board {

    private String[][] view;

    private int winningCriterion;

    private int r, c;

    private int remainingPos;

    public Board() {
        view = new String[3][3];
        r = 3;
        c = 3;
        winningCriterion = 3;
        remainingPos = 9;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < r; i++) {
            res.append("+--".repeat(Math.max(0, c)));
            res.append("+\n");
            for (int j = 0; j < c; j++) {
                res.append('|');
                res.append(view[i][j] == null ? "  " : view[i][j] + " ");
            }
            res.append("|\n");
        }
        res.append("+--".repeat(Math.max(0, c)));
        res.append("+\n");
        return res.toString();
    }

    public void put(int row, int col, String playerName) {
        remainingPos--;
        view[row][col] = playerName;
    }

    public void clear() {
        view = new String[r][c];
        remainingPos = r * c;
    }

    public boolean isValidPos(int row, int col) {
        return view[row][col] == null;
    }

    public void display() {
        System.out.println(this);
    }

    public int[] getParameters() {
        return new int[]{r, c};
    }

    public String getPositionString(int row, int col) {
        return view[row][col] == null ? " " : view[row][col];
    }

    public int getWinningCriterion() {
        return winningCriterion;
    }

    public boolean isDraw() {
        return remainingPos == 0;
    }

    public boolean inBound(int row, int col) {
        return row < r || row >= 0 || col < c || col >= 0;
    }
}
