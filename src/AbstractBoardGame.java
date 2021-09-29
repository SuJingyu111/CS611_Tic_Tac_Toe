/** Abstract class for board games. */

import java.util.List;

public abstract class AbstractBoardGame {

    protected AbstractBoard board;

    protected List<Team> teamList;

    public AbstractBoardGame(AbstractBoard board) {
        this.board = board;
    }

    /** Game runs in this method */
    public abstract void run();

    /** Displays current game */
    public abstract void display();

    /** Clear current game status for a new round */
    public abstract void clear();

    /** Messages to be displayed at the end of the game */
    public abstract void endDisplay();

    /** Checks if a player is winner */
    protected abstract boolean isWinner(AbstractPlayer thisPlayer);

    /** Checks if the game is draw */
    protected abstract boolean isDraw();
}
