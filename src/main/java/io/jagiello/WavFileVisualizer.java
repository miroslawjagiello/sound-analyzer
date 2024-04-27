package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class WavFileVisualizer {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 200;
    private static final double CENTER_X = WIDTH / 2;
    private static final double CENTER_Y = HEIGHT / 2;
    private static final double MAX_AMPLITUDE = 1.0;

    static void drawSoundLevel(GraphicsContext gc, float[] audioSamples) {
        gc.setFill(Color.BLUE);
        for (int i = 0; i < audioSamples.length; i++) {
            double x = i * (WIDTH / audioSamples.length);
            double y = CENTER_Y + (audioSamples[i] / MAX_AMPLITUDE) * (CENTER_Y);
            gc.fillRect(x, y, 0.1, 1);
        }
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, CENTER_Y, WIDTH, CENTER_Y);

        gc.setFill(Color.BLACK);
        gc.fillText("0", 0, CENTER_Y + 12);
    }

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
        gc.setFill(Color.BLACK);
        gc.fillText("0", 0, CENTER_Y + 12);
    }

}
