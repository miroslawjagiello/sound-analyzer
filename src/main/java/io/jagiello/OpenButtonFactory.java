package io.jagiello;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

class OpenButtonFactory {

    Button createOpenFileBrowser(Stage primaryStage,
                                 AtomicReference<Label> fileName,
                                 AtomicReference<Label> samplingRate,
                                 AtomicReference<Label> sampleSizeInBits,
                                 Canvas wavCanvas,
                                 AtomicReference<Double> tau,
                                 Canvas soundLevelCanvas) {
        Button btn = new Button();
        btn.setText("Open File Browser");

        btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");

            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(primaryStage);
            WavFileData wavFileData;
            try {
                wavFileData = WavFileProcessor.process(file, wavCanvas, tau, soundLevelCanvas);
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            if (file != null) {
                fileName.get().setText("File name: " + wavFileData.getName());
                samplingRate.get().setText("Sampling rate: " + wavFileData.getSampleRate() + " Hz");
                sampleSizeInBits.get().setText(wavFileData.getSampleSizeInBits() + " bit");
            }
        });
        return btn;
    }
}
