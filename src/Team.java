/** Teams in a game */

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {

    private String name;

    private List<AbstractPlayer> members;

    private int representingPlayerIdx;

    /** Constructs and fills team with a given number of member players */
    public Team(int playerNum, Class<?> playerClass, String[] name, AbstractBoard board) {
        members = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            try {
                Constructor<?> con = playerClass.getConstructor(String.class, AbstractBoard.class);
                members.add((AbstractPlayer) con.newInstance(name[i], board));
            }
            catch (Exception e) {
                System.out.println("Reflection gone wrong");
                e.printStackTrace();
            }
        }
        representingPlayerIdx = 0;
    }

    /** Returns the player that represents this team */
    public AbstractPlayer getRepresentingPlayer() {
        return members.get(representingPlayerIdx);
    }

    /** Sets representing player */
    public void setRepresentingPlayer(int idx) {
        representingPlayerIdx = idx;
    }

    /** Sets representing player randomly */
    public void setRandomRepresentingPlayer() {
        Random rand = new Random();
        representingPlayerIdx = rand.nextInt(members.size());
    }

    /** Gets name of the team */
    public String getTeamName() { return name; }

    /** Sets name of the team */
    public void setTeamName(String name) { this.name = name; }

    /** Gets number of times the team has won */
    public int getWinCnt() {
        int cnt = 0;
        for (AbstractPlayer p : members) {
            cnt += p.getWinCnt();
        }
        return cnt;
    }
}
