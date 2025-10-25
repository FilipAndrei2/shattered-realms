package com.paoo.shr.math;


public class Compare {

    public static final float FLT_EPS = 1e-4f;
    public static final double DBL_EPS = 1e-8;

    public static boolean equals(float a, float b) {
        return Math.abs(a - b) < FLT_EPS;
    }

    public static boolean equals(double a, double b) {
        return Math.abs(a - b) < DBL_EPS;
    }

    public static boolean lessThan(float a, float b) {
        return (b - a) > FLT_EPS;
    }

    public static boolean lessThan(double a, double b) {
        return (b - a) > DBL_EPS;
    }

    public static boolean greaterThan(float a, float b) {
        return (a - b) > FLT_EPS;
    }

    public static boolean greaterThan(double a, double b) {
        return (a - b) > DBL_EPS;
    }

    public static boolean isZero(float a) {
        return a <= FLT_EPS;
    }

    public static boolean isZero(double a) {
        return a <= DBL_EPS;
    }

    public static boolean lessThanEq(float a, float b) {
        return lessThan(a, b) || equals(a, b);
    }

    public static boolean lessThanEq(double a, double b) {
        return lessThan(a, b) || equals(a, b);
    }

    public static boolean greaterThanEq(float a, float b) {
        return greaterThan(a, b) || equals(a, b);
    }

    public static boolean greaterThanEq(double a, double b) {
        return greaterThan(a, b) || equals(a, b);
    }

    public static int compare(float a, float b) {
        if (lessThan(a, b)) {
            return -1;
        }
        if (equals(a, b)) {
            return 0;
        }
        return 1;
    }

    public static int compare(double a, double b) {
        if (lessThan(a, b)) {
            return -1;
        }
        if (equals(a, b)) {
            return 0;
        }
        return 1;
    }
}
