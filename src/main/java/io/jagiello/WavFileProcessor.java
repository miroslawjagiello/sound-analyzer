package io.jagiello;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class WavFileProcessor {
    static void process(File audioFile, GraphicsContext gc) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioInputStream.getFormat();
        long audioFileLength = audioFile.length();
        int frameSize = format.getFrameSize();
        float frameRate = format.getFrameRate();
        long numFrames = audioFileLength / frameSize;

        float[] audioSamples = new float[(int)numFrames];
        byte[] audioBytes = new byte[(int)audioFileLength];

        // Read the audio bytes and convert them to samples
        audioInputStream.read(audioBytes);
        // Assuming 16-bit samples, mono
        for (int i = 0, sampleIndex = 0; i < audioBytes.length && sampleIndex < audioSamples.length; i += 2, sampleIndex++) {
            int audioSample = (audioBytes[i + 1] << 8) | (audioBytes[i] & 0xFF);
            audioSamples[sampleIndex] = audioSample / 32768.0F; // Normalize
        }

        List<Line> line = new ArrayList<>();
        for (int i = 0; i < audioSamples.length - 1; i++) {
            gc.fillRect(i * audioSamples.length, 300 - audioSamples[i] / 128, audioSamples.length, 300);
        }

        double width = 200;
        double height = 100;
        double centerX = width / 2;
        double maxAmplitude = 1.0; // Maksymalna wartość amplitudy

        for (int i = 0; i < audioSamples.length; i++) {
            double x = i * (width / audioSamples.length);
            double y = height / 2 + (audioSamples[i] / maxAmplitude) * (height / 2);
            double rectHeight = 10; // Wysokość prostokąta reprezentującego próbkę
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 0.1, rectHeight); // Rysowanie prostokąta
        }
    }
}
