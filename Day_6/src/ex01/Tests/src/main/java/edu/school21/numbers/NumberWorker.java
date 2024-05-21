package edu.school21.numbers;

import java.math.RoundingMode; 

public class NumberWorker {

    public static boolean IsPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Ups, The number must be greater than 2! Please try again!");
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;   
    }

    public static int DigitsSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }

}
