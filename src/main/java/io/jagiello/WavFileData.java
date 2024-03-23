package io.jagiello;

import lombok.Builder;
import lombok.Getter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

@Getter
class WavFileData {
    final private long audioFileLength;
    final private int frameSize;
    final private float frameRate;
    final private long numFrames;

    final private float[] audioSamples;
    final private byte[] audioBytes;

    WavFileData(File audioFile) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioInputStream.getFormat();
        this.audioFileLength = audioFile.length();
        this.frameSize = format.getFrameSize();
        this.frameRate = format.getFrameRate();
        this.numFrames = audioFileLength / frameSize;

        this.audioSamples = new float[(int)numFrames];
        this.audioBytes = new byte[(int)audioFileLength];

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
    }
}
