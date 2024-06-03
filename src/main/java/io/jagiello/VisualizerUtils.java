package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VisualizerUtils {

    static void drawLines(GraphicsContext gc, double centerY, double width, double height) {

        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, centerY, width, centerY);
        gc.strokeLine(0, 0, 0, height);
        gc.setLineDashes(10, 5, 10, 5);
        for (int i = 0; i <= 8 ; i ++) {
            gc.strokeLine(0, height * i / 8, width, height * i / 8);
            gc.strokeLine(width * i / 8, 0, width * i / 8, height);
        }
        gc.setFill(Color.BLACK);
        gc.fillText("0", 0, centerY + 12);
    }
}
