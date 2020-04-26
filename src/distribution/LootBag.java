package distribution;

import enums.LootType;

import java.math.BigDecimal;
import java.util.ArrayList;

public class LootBag {

    private ArrayList<Loot> loots = new ArrayList<Loot>();

    public void addLoot (Loot loot){
        loots.add(loot);
    }

    public ArrayList<Loot> getLoots() {
        return loots;
    }

    public BigDecimal getBagValue() {
        BigDecimal value = new BigDecimal(0);
        for (Loot loot: loots) {
            value = value.add(loot.getTotalValue());
        }
        return value;
    }

    public boolean hasItemType(LootType lootType) {
        boolean hasItem = false;
        for(Loot loot : loots) {
            if(loot.getType() == lootType) {
                hasItem = true;
            }
        }
        return hasItem;
    }

    public Loot getItemByType(LootType lootType) {
        Loot lootByType = null;
        for(Loot loot : loots) {
            if(loot.getType() == lootType) {
                lootByType = loot;
            }
        }
        return lootByType;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer("");
        for(Loot loot : loots) {
            s.append("| ");
            s.append(loot.getType());
            s.append(" N=");
            s.append(loot.getAmount());
            s.append(" | ");
        }
        s.append(" TOTAL VALUE= ");
        s.append(this.getBagValue());

        return s.toString();
    }
}
