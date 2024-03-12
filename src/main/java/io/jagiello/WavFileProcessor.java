package io.jagiello;

import javafx.scene.canvas.GraphicsContext;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import static io.jagiello.WavFileVisualizer.drawWave;

class WavFileProcessor {
    static void process(File audioFile, GraphicsContext wavCanvasGc, GraphicsContext soundLevelCanvasGc) throws UnsupportedAudioFileException, IOException {
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

        float maxAudioSample = 0f;
        float minAudioSample = 0f;
        // Assuming 16-bit samples, mono
        for (int i = 0, sampleIndex = 0; i < audioBytes.length && sampleIndex < audioSamples.length; i += 2, sampleIndex++) {
            int audioSample = (audioBytes[i + 1] << 8) | (audioBytes[i] & 0xFF);
            audioSamples[sampleIndex] = audioSample / 32768.0F; // Normalize
            if(audioSamples[sampleIndex] > maxAudioSample) {
                maxAudioSample = audioSamples[sampleIndex];
            }
            if(audioSamples[sampleIndex] < minAudioSample) {
                minAudioSample = audioSamples[sampleIndex];
            }
        }

        for (int i = 0; i < audioSamples.length - 1; i++) {
            wavCanvasGc.fillRect(i * audioSamples.length, 300 - audioSamples[i] / 128, audioSamples.length, 300);
        }

        drawWave(wavCanvasGc, audioSamples);

        //double[] soundLevel = DecibelCalculator.LdB(ExponentialAveragingCalculator.calculate(audioSamples, 125, frameRate));
        float[] soundLevel = ExponentialAveragingCalculator.calculate(audioSamples, 125, frameRate);

        for (int i = 0; i < soundLevel.length - 1; i++) {
            soundLevelCanvasGc.fillRect(i * soundLevel.length, 300 - soundLevel[i] / 128, soundLevel.length, 300);
        }

        drawWave(soundLevelCanvasGc, soundLevel);
    }
}
