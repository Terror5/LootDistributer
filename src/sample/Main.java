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
        lootDistributer.addLoot(new Loot(LootType.ARCANE_CRISTALL, new BigDecimal(90.0), 1));
        lootDistributer.addLoot(new Loot(LootType.BLOODVINE, new BigDecimal(25.0), 5));
        lootDistributer.addLoot(new Loot(LootType.COIN, new BigDecimal(1.0), 45));
        lootDistributer.addLoot(new Loot(LootType.JEWEL, new BigDecimal(9.0), 33));
        lootDistributer.addLoot(new Loot(LootType.SHARD, new BigDecimal(4.0), 5));

        lootDistributer.distribute();
        lootDistributer.printLootBags();

    }
}
