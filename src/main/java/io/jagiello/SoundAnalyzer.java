package io.jagiello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class SoundAnalyzer extends Application {

    private static final String TITLE = "Sound Analyzer";
    private static final double WINDOW_WIDTH = 1024;
    private static final double WINDOW_HEIGHT = 700;

    private static final double CANVAS_WIDTH = 400;
    private static final double WAV_CANVAS_HEIGHT = 200;
    private static final double SOUND_LEVEL_CANVAS_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label(TITLE);

        VBox root = new VBox();
        root.setSpacing(5);

        AtomicReference<Double> tau = new AtomicReference<>(125d);

        OpenButtonFactory openButtonFactory = new OpenButtonFactory();
        ExpTimeButtonsFactory expTimeButtonsFactory = new ExpTimeButtonsFactory();

        Canvas wavCanvas = new Canvas(CANVAS_WIDTH, WAV_CANVAS_HEIGHT);
        GraphicsContext gc = wavCanvas.getGraphicsContext2D();
        wavCanvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            // Draw a text at the click location
            gc.fillText("Click detected at (" + x + ", " + y + ")", x, y);
        });

        Canvas soundLevelCanvas = new Canvas(CANVAS_WIDTH, SOUND_LEVEL_CANVAS_HEIGHT);

        root.getChildren().add(label);
        root.getChildren().add(expTimeButtonsFactory.create(tau));
        AtomicReference<FileInfoLabels> fileInfoLabels = new AtomicReference<>(new FileInfoLabels());
        root.getChildren().add(openButtonFactory.createOpenFileBrowser(primaryStage,
                fileInfoLabels, wavCanvas, tau, soundLevelCanvas));
        VBox fileInfoBox = new VBox();
        fileInfoBox.setSpacing(5);
        fileInfoBox.getChildren().add(fileInfoLabels.get().getFileName());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getSamplingRate());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getSampleSizeInBits());
        fileInfoBox.getChildren().add(fileInfoLabels.get().getLength());

        TitledPane fileInfoPane = new TitledPane();
        fileInfoPane.setText("File info");
        fileInfoPane.setContent(fileInfoBox);
        root.getChildren().add(fileInfoPane);

        TitledPane wavPane = new TitledPane();
        wavPane.setText("Sound wave");
        wavPane.setContent(wavCanvas);
        root.getChildren().add(wavPane);

        TitledPane soundLevelPane = new TitledPane();
        soundLevelPane.setText("Sound Level");
        soundLevelPane.setContent(soundLevelCanvas);
        root.getChildren().add(soundLevelPane);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
