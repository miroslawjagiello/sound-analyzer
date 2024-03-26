package io.jagiello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class SoundAnalyzer extends Application {

    private static final String TITLE = "Sound Analyzer";
    private static final double WINDOW_WIDTH = 1024;
    private static final double WINDOW_HEIGHT = 700;

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label(TITLE);

        VBox root = new VBox();
        root.setSpacing(5);

        AtomicReference<Double> tau = new AtomicReference<>(125d);

        OpenButtonFactory openButtonFactory = new OpenButtonFactory();
        ExpTimeButtonsFactory expTimeButtonsFactory = new ExpTimeButtonsFactory();

        Canvas wavCanvas = new Canvas(400, 200);
        Canvas soundLevelCanvas = new Canvas(400, 400);

        root.getChildren().add(label);
        root.getChildren().add(expTimeButtonsFactory.create(tau));
        AtomicReference<Label> fileName = new AtomicReference<>(new Label(""));
        AtomicReference<Label> samplingRate = new AtomicReference<>(new Label(""));
        root.getChildren().add(openButtonFactory.createOpenFileBrowser(primaryStage, fileName, samplingRate, wavCanvas, tau, soundLevelCanvas));
        root.getChildren().add(fileName.get());
        root.getChildren().add(samplingRate.get());
        root.getChildren().add(wavCanvas);
        root.getChildren().add(soundLevelCanvas);

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
