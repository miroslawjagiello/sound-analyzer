package io.jagiello;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static io.jagiello.WavFileVisualizer.drawSoundLevel;

class WavFileProcessor {
    static WavFileData process(File audioFile, Canvas wavCanvas,
                        AtomicReference<Double> tau, Canvas soundLevelCanvas)
            throws UnsupportedAudioFileException, IOException {

        WavFileData wavFileData = new WavFileData(audioFile);

        GraphicsContext wavCanvasGc = wavCanvas.getGraphicsContext2D();
        wavCanvasGc.clearRect(0, 0, wavCanvas.getWidth(), wavCanvas.getHeight());
        for (int i = 0; i < wavFileData.getAudioSamples().length - 1; i++) {
            wavCanvasGc.fillRect(i * wavFileData.getAudioSamples().length,
                    300 - wavFileData.getAudioSamples()[i] / 128, wavFileData.getAudioSamples().length, 300);
        }

        drawSoundLevel(wavCanvasGc, wavFileData.getAudioSamples());

        double[] soundLevel = DecibelCalculator.LdB(
                ExponentialAveragingCalculator.calculate(
                        wavFileData.getAudioSamples(), tau.get().floatValue(), wavFileData.getSampleRate()));
        GraphicsContext soundLevelCanvasGc = soundLevelCanvas.getGraphicsContext2D();
        soundLevelCanvasGc.clearRect(0, 0, soundLevelCanvas.getWidth(), soundLevelCanvas.getHeight());
        WavFileVisualizer.drawSoundLevel(soundLevelCanvasGc, soundLevel);

        return wavFileData;
    }
}
