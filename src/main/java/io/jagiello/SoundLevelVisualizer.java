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
            //double y = height / 2 - audioSamples[i] * 2;
            double y = height / 4 - audioSamples[i] * 2;
            gc.strokeLine(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        }
        VisualizerUtils.drawLines(gc, CENTER_Y, WIDTH, HEIGHT);
    }

}
