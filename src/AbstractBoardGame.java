import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBoardGame {

    protected Board board;

    protected List<List<Player>> teamList;

    public AbstractBoardGame(Board board) {
        this.board = board;
    }

    public abstract void run();

    public abstract void display();

    public abstract void clear();

    public abstract void endDisplay();

}
