package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class SoundLevelVisualizer {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 400;
    private static final double CENTER_Y = HEIGHT / 2;

    static void drawSoundLevel(GraphicsContext gc, double[] audioSamples) {
        int width = (int) gc.getCanvas().getWidth();
        int height = (int) gc.getCanvas().getHeight();
        double lastX = 0, lastY = height / 2;

        gc.setStroke(Color.BLUE);
        for (int i = 0; i < audioSamples.length; i++) {
            double x = (double) i / audioSamples.length * width;
            double y = height / 2 - audioSamples[i] * 2;
            gc.strokeLine(lastX, lastY, x, y);

            lastX = x;
            lastY = y;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, CENTER_Y, WIDTH, CENTER_Y);

        gc.strokeLine(0, 0, 0, HEIGHT);
        gc.setLineDashes(10, 5, 10, 5);
        for (int i = 0; i <= 8 ; i ++) {
            gc.strokeLine(0, HEIGHT * i / 8, WIDTH, HEIGHT * i / 8);
            gc.strokeLine(WIDTH * i / 8, 0, WIDTH * i / 8, HEIGHT);
        }

        gc.setFill(Color.BLACK);
        gc.fillText("0", 0, CENTER_Y + 12);
    }

}
