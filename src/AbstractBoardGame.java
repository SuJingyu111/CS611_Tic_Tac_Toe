import java.util.List;
import java.util.Scanner;

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

    protected abstract int[] takeInput(Scanner in);

    protected abstract boolean makeMove(Scanner in);

}
