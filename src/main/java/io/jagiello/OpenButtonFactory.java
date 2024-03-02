package io.jagiello;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

class OpenButtonFactory {
    Button createOpenFileBrowser(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Open File Browser");

        btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                System.out.println("File selected: " + file.getAbsolutePath());
            }
        });
        return btn;
    }
}
