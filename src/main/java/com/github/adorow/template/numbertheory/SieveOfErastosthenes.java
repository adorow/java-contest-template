package com.github.adorow.template.numbertheory;

/**
 * An implementation of the Sieve of Erastosthenes.<br/>
 * This class is instantiated once, and a method is called to calculate up to a certain number of prime primeBitSet. And then lookup methods can be used to check whether given primeBitSet are prime or not.
 *
 * Note: This class does not take care of special conditions, the user has to take care of that.
 * @link https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 */
public final class SieveOfErastosthenes {

    public SieveOfErastosthenes() {}

    private int[] primeBitSet;
    private int primeBitSetLength;

    private int[] primes;
    private int primesLen;

    /**
     * Calculates primes, up until the number {@code maxNumber}.
     * @param maxNumber the largest number (approximately) to check for as prime. It should always be a large number, like 10000000 for example.
     */
    public void calculateUpTo(int maxNumber) {
        primeBitSetLength = (maxNumber >> 5) + 1;
        primeBitSet = new int[primeBitSetLength];

        maxNumber = primeBitSetLength << 5;// increases the upper boundary

        // there are 168 prime numbers from 2 to 1000, so a fourth is always enough.
        primes = new int[maxNumber >> 2];
        primesLen = 0;

        int prime = 2;
        int end = maxNumber;
        // mark all the even numbers besides 2
        for (int num = prime + 2; num < end; num += 2) {
            setNonPrime(num);
        }
        primes[primesLen++] = 2;
        int sqrt = (int) Math.sqrt(end);
        prime = 3;
        for (; prime <= sqrt; prime++) {
            if (isPrime(prime)) {
                primes[primesLen++] = prime;
                for (int num = (prime << 1); num < end; num += prime) {
                    setNonPrime(num);
                }
            }
        }

        // adds the rest of the primes
        for (prime = sqrt + 1; prime < end; prime++) {
            if (isPrime(prime)) {
                primes[primesLen++] = prime;
            }
        }
    }

    /**
     * Gets the amount of prime numbers found.
     *
     * @return the amount of prime numbers found.
     */
    public int getNumberOfPrimes() {
        return primesLen;
    }

    /**
     * Gets a prime number from the generated list, at the given index.
     *
     * @param index the index, starting at {@code 0} and going until {@code #getNumberOfPrimes() - 1}.
     * @return the prime number at the given index.
     */
    public int getPrimeAtIndex(int index) {
        return primes[index];
    }

    /**
     * TElls whether a given nuber is prime, based on the Sieve's calculations.
     * @param num the number to be checked.
     * @return {@code true} if the given number is prime, {@code false} otherwise.
     */
    public boolean isPrime(int num) {
        return (primeBitSet[intindex(num)] & (1 << bitindex(num))) == 0;
    }

    private void setNonPrime(int num) {
        primeBitSet[intindex(num)] |= (1 << bitindex(num));
    }


    private static int intindex(int number) {
        return (number >> 5);
    }

    private static int bitindex(int number) {
        return (number % 32);
    }

}
