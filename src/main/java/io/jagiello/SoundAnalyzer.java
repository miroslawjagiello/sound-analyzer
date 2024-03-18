package io.jagiello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        GraphicsContext wavCanvasGc = wavCanvas.getGraphicsContext2D();

        Canvas soundLevelCanvas = new Canvas(400, 400);
        GraphicsContext soundLevelCanvasGc = soundLevelCanvas.getGraphicsContext2D();

        root.getChildren().add(label);
        root.getChildren().add(expTimeButtonsFactory.create(tau));
        root.getChildren().add(openButtonFactory.createOpenFileBrowser(primaryStage, wavCanvasGc, soundLevelCanvasGc));
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
