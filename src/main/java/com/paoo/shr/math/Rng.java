package com.paoo.shr.math;

public class Rng {

    private static java.util.Random rand = new java.util.Random(99999999);

    public static int randInt() { return rand.nextInt(); }
    public static int randInt(int min, int max) { return rand.nextInt((max - min) + 1) + min; }

    public static long randLong() { return rand.nextLong(); }

    public static float randFloat() { return rand.nextFloat(); }
    public static float randFloat(float min, float max) { return (rand.nextFloat() * (max - min)) + min; }

    public static double randDouble() { return rand.nextDouble(); }
    public static double randDouble(double min, double max) { return rand.nextDouble() * (max - min) + min;  }
}
