package distribution;

import java.util.Comparator;

public class SortByPrice implements Comparator<Loot> {


    @Override
    public int compare(Loot o1, Loot o2) {

        return o1.getValue().compareTo(o2.getValue());
    }
}
