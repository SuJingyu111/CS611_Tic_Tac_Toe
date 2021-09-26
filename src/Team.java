import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {

    private String name;

    private List<AbstractPlayer> members;

    private int representingPlayerIdx;

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

    public AbstractPlayer getRepresentingPlayer() {
        return members.get(representingPlayerIdx);
    }

    public void setRepresentingPlayer(int idx) {
        representingPlayerIdx = idx;
    }

    public void setRandomRepresentingPlayer() {
        Random rand = new Random();
        representingPlayerIdx = rand.nextInt(members.size());
    }

    public String getTeamName() { return name; }

    public void setTeamName(String name) { this.name = name; }

    public int getWinCnt() {
        int cnt = 0;
        for (AbstractPlayer p : members) {
            cnt += p.getWinCnt();
        }
        return cnt;
    }
}
