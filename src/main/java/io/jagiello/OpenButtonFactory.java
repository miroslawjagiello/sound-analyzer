package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

class OpenButtonFactory {

    Button createOpenFileBrowser(Stage primaryStage, GraphicsContext gc) {
        Button btn = new Button();
        btn.setText("Open File Browser");

        btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");

            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(primaryStage);
            try {
                WavFileProcessor.process(file, gc);
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            if (file != null) {
                System.out.println("File selected: " + file.getAbsolutePath());
            }
        });
        return btn;
    }
}
