import java.util.List;

public abstract class AbstractBoardGame {

    protected AbstractBoard board;

    protected List<List<AbstractPlayer>> teamList;

    public AbstractBoardGame(AbstractBoard board) {
        this.board = board;
    }

    public abstract void run();

    public abstract void display();

    public abstract void clear();

    public abstract void endDisplay();

}
