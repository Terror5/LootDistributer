package distribution;

import java.math.BigDecimal;
import enums.LootType;

public class Loot {

    private LootType type;
    private BigDecimal value;
    private int amount;

    public Loot(LootType type){
        this.type = type;
        this.value = new BigDecimal(0);
        this.amount = 0;
    }

    public Loot(LootType type, BigDecimal value, int amount){
        this.type = type;
        this.value =  value;
        this.amount = amount;
    }

    public LootType getType() {
        return type;
    }

    public void setType(LootType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void increaseAmount() {
        this.amount += 1;
    }

    public BigDecimal getTotalValue() {
        return this.value.multiply(new BigDecimal(this.amount));
    }
}
