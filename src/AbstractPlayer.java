/** Abstract class for player in board games */

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPlayer {

    protected String name;

    protected AbstractBoard board;

    protected Map<String, AbstractPiece> pieces;

    protected int winCnt;

    public AbstractPlayer() {
        pieces = new HashMap<>();
    }

    public AbstractPlayer(String name, AbstractBoard board) {
        this.name = name;
        this.board = board;
        pieces = new HashMap<>();
    }

    /** Get the name for this player */
    public String getName() {
        return name;
    }

    /** Get the times this player has won */
    public int getWinCnt() { return winCnt; }

    /** Increment winning count by 1 */
    public void incrementWinCnt() { winCnt++; }

    /** Put the piece on the board */
    public abstract void put(int row, int col, String pieceName);
}
