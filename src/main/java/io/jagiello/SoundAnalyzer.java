package io.jagiello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicReference;

import static io.jagiello.LayoutUtils.addPane;
import static io.jagiello.LayoutUtils.createFileInfoBox;

public class SoundAnalyzer extends Application {

    private static final String TITLE = "Sound Analyzer";
    private static final double WINDOW_WIDTH = 1024;
    private static final double WINDOW_HEIGHT = 700;

    private static final double CANVAS_WIDTH = 400;
    private static final double WAV_CANVAS_HEIGHT = 200;
    private static final double SOUND_LEVEL_CANVAS_HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label titleLabel = new Label(TITLE);

        VBox root = new VBox();
        root.setSpacing(5);

        OpenButtonFactory openButtonFactory = new OpenButtonFactory();
        ExpTimeButtonsFactory expTimeButtonsFactory = new ExpTimeButtonsFactory();

        Canvas wavCanvas = new Canvas(CANVAS_WIDTH, WAV_CANVAS_HEIGHT);
        GraphicsContext gcWavCanvas = wavCanvas.getGraphicsContext2D();

        Canvas soundLevelCanvas = new Canvas(CANVAS_WIDTH, SOUND_LEVEL_CANVAS_HEIGHT);
        GraphicsContext gcSoundLevelCanvas = soundLevelCanvas.getGraphicsContext2D();

        AtomicReference<Double> xPosition = new AtomicReference<>(0d);

        wavCanvas.setOnMouseClicked(event -> {
            double x = event.getX();
            xPosition.set(x);

            gcWavCanvas.setStroke(Color.RED);
            gcWavCanvas.strokeLine(x, 0, x, WAV_CANVAS_HEIGHT);

            gcSoundLevelCanvas.setStroke(Color.RED);
            gcSoundLevelCanvas.strokeLine(x, 0, x, SOUND_LEVEL_CANVAS_HEIGHT);
        });

        root.getChildren().add(titleLabel);

        AtomicReference<Double> tau = new AtomicReference<>(125d);
        root.getChildren().add(expTimeButtonsFactory.create(tau));

        AtomicReference<FileInfoLabels> fileInfoLabels = new AtomicReference<>(new FileInfoLabels());
        root.getChildren().add(openButtonFactory.createOpenFileBrowser(primaryStage,
                fileInfoLabels, wavCanvas, tau, soundLevelCanvas));

        VBox fileInfoBox = createFileInfoBox(fileInfoLabels);

        addPane("File info", fileInfoBox, root);
        addPane("Sound wave", wavCanvas, root);
        addPane("Sound Level", soundLevelCanvas, root);

        createScene(primaryStage, root);
    }

    private static void createScene(Stage primaryStage, VBox root) {
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(WINDOW_WIDTH);
        primaryStage.setMinHeight(WINDOW_HEIGHT);
        primaryStage.show();
    }
}
