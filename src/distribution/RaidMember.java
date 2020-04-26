package distribution;

import java.util.ArrayList;
import java.util.Collections;

public class RaidMember {

    private static ArrayList<String> players =  new ArrayList<>(20);

    public static void addPlayer(String player) {
        players.add(player);
    }

    public static void removePlayer(String player) {
        players.remove(player);
    }

    public static ArrayList<String> getPlayers() {
        return players;
    }

    public static int size(){
        return players.size();
    }

    public static void shuffle() {
        Collections.shuffle(players);
    }

}
