package io.jagiello;

import lombok.Getter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

@Getter
class WavFileData {

    private final String name;

    private final long audioFileLength;
    private final AudioFormat format;
    private final long numFrames;
    private final double waveLengthInSeconds;

    private final float[] audioSamples;
    private final byte[] audioBytes;

    WavFileData(File audioFile) throws UnsupportedAudioFileException, IOException {
        this.name = audioFile.getName();

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        this.format = audioInputStream.getFormat();
        this.audioFileLength = audioFile.length();
        this.numFrames = audioFileLength / format.getFrameSize();
        this.waveLengthInSeconds = audioInputStream.getFrameLength() / audioInputStream.getFormat().getFrameRate();

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

    float getSampleRate() {
        return format.getSampleRate();
    }

    int getSampleSizeInBits() {
        return format.getSampleSizeInBits();
    }
}
