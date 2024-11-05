package com.example.timeservice.impl;

import java.util.Random;

/**
 * Description about RandomDelay
 *
 * @author Serge Belolipetski
 * @since 0.0.1, 11/4/24 6:14 PM
 */
public class RandomDelay {

    /**
     * Executes a random delay between minMillis and maxMillis milliseconds.
     * @param minMillis the minimum delay in milliseconds
     * @param maxMillis the maximum delay in milliseconds
     */
    public static long executeRandomDelay(int minMillis, int maxMillis) {
        if (minMillis < 0 || maxMillis <= 0 || minMillis > maxMillis) {
            throw new IllegalArgumentException("Invalid delay range specified.");
        }

        Random random = new Random();
        long delay = minMillis + (long)(random.nextDouble() * (maxMillis - minMillis));

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // Handle the InterruptedException if it occurs
            Thread.currentThread().interrupt();
            throw new RuntimeException("Delay execution interrupted.", e);
        }

        return delay;
    }
}