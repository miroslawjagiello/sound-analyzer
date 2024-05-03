package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class WavFileVisualizer {

    private static final double WIDTH = 400;
    private static final double HEIGHT = 200;
    private static final double CENTER_Y = HEIGHT / 2;
    private static final double MAX_AMPLITUDE = 1.0;

    static void drawWav(GraphicsContext gc, float[] audioSamples) {
        gc.setFill(Color.BLUE);
        for (int i = 0; i < audioSamples.length; i++) {
            double x = i * (WIDTH / audioSamples.length);
            double y = CENTER_Y + (audioSamples[i] / MAX_AMPLITUDE) * (CENTER_Y);
            gc.fillRect(x, y, 0.1, 1);
        }
        VisualizerUtils.drawLines(gc, CENTER_Y, WIDTH, HEIGHT);
    }
}
