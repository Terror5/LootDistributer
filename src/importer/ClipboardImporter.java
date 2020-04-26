package importer;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

public class ClipboardImporter {


    public String importData() {

        String data = "";
        try {
            data = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            System.out.print(data);

        } catch (Exception e){

        }
        return data;
    }
}
