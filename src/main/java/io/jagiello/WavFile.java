package io.jagiello;

class WavFile {
    private long audioFileLength;
    private int frameSize;
    private float frameRate;
    private long numFrames; // audioFileLength / frameSize;

    private float[] audioSamples = new float[(int)numFrames];
    private byte[] audioBytes = new byte[(int)audioFileLength];
}
