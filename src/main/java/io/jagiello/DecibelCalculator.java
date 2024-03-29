package io.jagiello;

class DecibelCalculator {
    static double[] LdB(float[] input) {
        int len = input.length;
        double[] dB = new double[len];

        double maxLevel = -300;
        double minLevel = Double.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            dB[i] = 20 * Math.log10(input[i]);
            if (dB[i] < minLevel && Double.NEGATIVE_INFINITY != dB[i]) minLevel = dB[i];
            if (dB[i] > maxLevel) {
                maxLevel = dB[i];
            }
        }
        for (int i = 0; i < len; i++) {
            if (Double.NEGATIVE_INFINITY == dB[i]) dB[i] = minLevel;
        }

        return dB;
    }
}
