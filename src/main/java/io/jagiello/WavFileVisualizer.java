package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WavFileVisualizer {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 200;
    private static final double CENTER_X = WIDTH / 2;
    private static final double MAX_AMPLITUDE = 1.0;

    static void drawWave(GraphicsContext gc, float[] audioSamples) {
        // Maksymalna wartość amplitudy

        for (int i = 0; i < audioSamples.length; i++) {
            double x = i * (WIDTH / audioSamples.length);
            double y = HEIGHT / 2 + (audioSamples[i] / MAX_AMPLITUDE) * (HEIGHT / 2);
            double rectHeight = 10; // Wysokość prostokąta reprezentującego próbkę
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 0.1, rectHeight); // Rysowanie prostokąta
        }
    }

    static void drawWave(GraphicsContext gc, double[] audioSamples) {
        // Maksymalna wartość amplitudy

        for (int i = 0; i < audioSamples.length; i++) {
            double x = i * (WIDTH / audioSamples.length);
            double y = HEIGHT / 2 + (audioSamples[i] / MAX_AMPLITUDE) * (HEIGHT / 2);
            double rectHeight = 10; // Wysokość prostokąta reprezentującego próbkę
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 0.1, rectHeight); // Rysowanie prostokąta
        }
    }

}
