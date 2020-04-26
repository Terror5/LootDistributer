package sample;

import distribution.Loot;
import distribution.LootDistributer;
import enums.LootType;
import importer.WowLogsHtmlHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

/*
    public static void main(String[] args) {
        launch(args);
        WowLogsHtmlHandler w = new WowLogsHtmlHandler();
        w.parse();
    }
*/
    public static void main(String[] args) {
        WowLogsHtmlHandler w = new WowLogsHtmlHandler();
        w.parse();

        LootDistributer lootDistributer = new LootDistributer();
        //lootDistributer.addLoot(new Loot(LootType.ARCANE_CRISTALL, new BigDecimal(78.7), 1));
        lootDistributer.addLoot(new Loot(LootType.BLOODVINE, new BigDecimal(30.69), 2));
        lootDistributer.addLoot(new Loot(LootType.COIN, new BigDecimal(0.94), 98));
        lootDistributer.addLoot(new Loot(LootType.JEWEL, new BigDecimal(7.99), 54));
        lootDistributer.addLoot(new Loot(LootType.SHARD, new BigDecimal(3.3), 10));
        //lootDistributer.addLoot(new Loot(LootType.ETERNAL_ESSENCE, new BigDecimal(6.99), 5));
        lootDistributer.addLoot(new Loot(LootType.ILLUSIONDUST, new BigDecimal( 0.25), 21));

        lootDistributer.distribute();
        lootDistributer.printLootBags();

    }
}
