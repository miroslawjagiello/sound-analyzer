package io.jagiello;

import javafx.scene.control.Label;

class FileInfoLabels {
    private Label fileName = new Label("");
    private Label samplingRate = new Label("");
    private Label sampleSizeInBits = new Label("");

    public void setFileName(String fileName) {
        this.fileName.setText("File name: " + fileName);
    }

    public void setSamplingRate(String samplingRate) {
        this.samplingRate.setText("Sampling rate: " + samplingRate + " Hz");
    }

    public void setSampleSizeInBits(String sampleSizeInBits) {
        this.sampleSizeInBits.setText(sampleSizeInBits + " bit");
    };
}
