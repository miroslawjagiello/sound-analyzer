package io.jagiello;

public class DecibelCalculator {
    public static double[] LdB(float[] input) {
        int len = input.length;
        double[] dB = new double[len];

        for (int i = 0; i < len; i++) {

            if (input[i] > 0) {
                dB[i] = 20 * Math.log10(input[i]);
            }
        }
        return dB;
    }
}
