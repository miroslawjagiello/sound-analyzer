package io.jagiello;

class ExponentialAveragingCalculator {
    static float[] calculate(float[] input, float tau, float samplingFrequency) {
        int N = (int)Math.ceil(tau / 1000 * samplingFrequency);
        int lx = input.length;
        float[] y = new float[lx];
        float yp = 0;

        for (int i = 0; i < lx; i++) {
            input[i] = input[i] * input[i];
        }

        for (int i = 0; i < lx; i++) {
            y[i] = (yp * (N - 1) + input[i]) / N;
            yp = y[i];
        }

        for (int i = 0; i < lx; i++) {
            y[i] = (float)Math.sqrt(y[i]);
        }

        return y;
    }
}
