package com.github.adorow.template.numbertheory;

/**
 * Simple test to benchmark how fast the Sieve is.
 */
public class SieveOfErastosthenesBenchmark {

    public static void main(String[] args) {
        runBenchmarkFor(1000);
        runBenchmarkFor(10000);
        runBenchmarkFor(100000);
        runBenchmarkFor(1000000);
        runBenchmarkFor(10000000);
        runBenchmarkFor(100000000);
        runBenchmarkFor(1000000000);
    }

    private static void runBenchmarkFor(int maxNumber) {
        SieveOfErastosthenes sieve = new SieveOfErastosthenes();
        final long startAt = System.currentTimeMillis();
        sieve.calculateUpTo(maxNumber);
        final long endAt = System.currentTimeMillis();

        System.out.printf("SieveOfErastosthenes.calculateUpTo(%d): %dms%n", maxNumber, endAt - startAt);
    }

}
