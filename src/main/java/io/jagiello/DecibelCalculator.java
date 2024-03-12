package io.jagiello;

public class DecibelCalculator {
    public static double[] LdB(float[] input) {
        int len = input.length;
        double[] dB = new double[len];

        double maxLevel = Double.MIN_VALUE;
        double minLevel = Double.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            dB[i] = 20 * Math.log10(input[i]);
            if (dB[i] < minLevel) minLevel = dB[i];
            if (dB[i] > maxLevel) maxLevel = dB[i];
        }
        return dB;
    }
}
