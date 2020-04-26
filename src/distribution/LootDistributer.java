package distribution;

import enums.LootType;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class LootDistributer {

    private ArrayList<Loot> loots = new ArrayList<>(LootType.values().length);
    private ArrayList<Pair<String, LootBag>> playerLoots = new ArrayList<>(RaidMember.size());

    //TODO QUICK TEST -> CONSOLE
    public void addLoot(Loot loot) {
        loots.add(loot);
    }

    public void printLootBags() {
        for(Pair<String, LootBag> playerLoot : playerLoots){
            System.out.println("Spieler: " + playerLoot.getKey() + " LootBag: " + playerLoot.getValue());
        }
    }

    public void distribute() {
        //TODO maybe delete playerLoots for reruns of method

        // 1. Sort loots by their value in Gold - reversed for high to low
        Collections.sort(loots, new SortByPrice().reversed());

        // 2. Shuffle players to randomize outcome
        RaidMember.shuffle();

        //3. Fill playerLoots list with all players
        for (String name: RaidMember.getPlayers()) {
            playerLoots.add(new Pair<>(name, new LootBag()));
        }

        // 4. calculate total value of items and set mean value per person
        BigDecimal treshold = new BigDecimal(0);
        for (Loot loot: loots) {
            treshold = treshold.add(loot.getTotalValue());
        }
        treshold = treshold.divide(new BigDecimal(RaidMember.size()), BigDecimal.ROUND_DOWN);
        System.out.println("THRESHOLD: " + treshold);

        // 5. Iterate through all loot and assign it to players with the lowest gold amount
        int currentPlayerIndex = 0;

        for (Loot loot: loots) {
            for(int i = 0; i < loot.getAmount(); ++i){

                // is the current players LootBag lower than threshold
                if(playerLoots.get(currentPlayerIndex).getValue().getBagValue().compareTo(treshold) < 0 ) {

                    LootBag currentLootBag = playerLoots.get(currentPlayerIndex).getValue();

                    // add item to lootbag or increase amount counter
                    if(currentLootBag.hasItemType(loot.getType())) {
                        currentLootBag.getItemByType(loot.getType()).increaseAmount();
                    } else {
                        currentLootBag.addLoot(new Loot(loot.getType(), loot.getValue(), 1));
                    }

                } else {
                    // iterate over the next 19 Players to find an eligible candidate
                    for(int y = 0; y < 19; ++y){
                        currentPlayerIndex = increaseIndex(currentPlayerIndex);
                        LootBag currentNextLootBag = playerLoots.get(currentPlayerIndex).getValue();

                        if(currentNextLootBag.getBagValue().compareTo(treshold) < 0 ) {

                            // add item to lootbag or increase amount counter
                            if (currentNextLootBag.hasItemType(loot.getType())) {
                                currentNextLootBag.getItemByType(loot.getType()).increaseAmount();
                            } else {
                                currentNextLootBag.addLoot(new Loot(loot.getType(), loot.getValue(), 1));
                            }
                            break;
                        }
                    }

                    // if none is found - find all players with lowest lootbag value and randomly assign it to one
                    // NOT NECESSARY -> should terminate because it is possible to put items exceeding threshold
                    //addLootToLowestBag(loot);
                }

                // increase playerindex so it cant exceed the array bounds set by the playerlist
                currentPlayerIndex = increaseIndex(currentPlayerIndex);
            }
        }
    }


    private int increaseIndex(int index) {
        ++index;
        if(index == RaidMember.size()) {
            index = 0;
        }
        return index;
    }
}
