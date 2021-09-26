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

    public String getName() {
        return name;
    }

    public int getWinCnt() { return winCnt; }

    public void incrementWinCnt() { winCnt++; }

    public abstract void put(int row, int col, String pieceName);
}
