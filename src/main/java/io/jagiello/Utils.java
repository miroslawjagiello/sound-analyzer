package io.jagiello;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import java.util.concurrent.atomic.AtomicReference;

class Utils {
    static VBox createFileInfoBox(AtomicReference<FileInfoLabels> fileInfoLabels) {
        VBox fileInfoBox = new VBox();
        fileInfoBox.setSpacing(5);
        fileInfoBox.getChildren().add(fileInfoLabels.get().getFileName());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getSamplingRate());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getSampleSizeInBits());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getLength());
        return fileInfoBox;
    }

    static void addPane(String title, Node wavCanvas, VBox root) {
        TitledPane wavPane = new TitledPane();
        wavPane.setText(title);
        wavPane.setContent(wavCanvas);
        root.getChildren().add(wavPane);
    }
}
