package io.jagiello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SoundAnalyzer extends Application {

    private static final String TITLE = "Sound Analyzer";

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label(TITLE);
        StackPane root = new StackPane();
        root.getChildren().add(label);

        OpenButtonFactory openButtonFactory = new OpenButtonFactory();
        root.getChildren().add(openButtonFactory.createOpenFileBrowser(primaryStage));

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
