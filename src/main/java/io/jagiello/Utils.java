package io.jagiello;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

class Utils {
    static void addPane(String title, Node wavCanvas, VBox root) {
        TitledPane wavPane = new TitledPane();
        wavPane.setText(title);
        wavPane.setContent(wavCanvas);
        root.getChildren().add(wavPane);
    }
}
