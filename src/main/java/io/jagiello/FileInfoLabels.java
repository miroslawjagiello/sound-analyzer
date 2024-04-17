package io.jagiello;

import javafx.scene.control.Label;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
class FileInfoLabels {
    private final Label fileName = new Label("");
    private final Label samplingRate = new Label("");
    private final Label sampleSizeInBits = new Label("");
    private final Label length = new Label("");

    public void setFileName(String fileName) {
        this.fileName.setText("File name: " + fileName);
    }

    public void setSamplingRate(float samplingRate) {
        this.samplingRate.setText("Sampling rate: " + samplingRate + " Hz");
    }

    public void setSampleSizeInBits(int sampleSizeInBits) {
        this.sampleSizeInBits.setText(sampleSizeInBits + " bit");
    };


    public void setLength(double length) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.length.setText("Length: " + decimalFormat.format(length) + "s");
    }

}
