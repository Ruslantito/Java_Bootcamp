package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = { 2, 5, 7, 11 })
    public void isPrimeForPrimes(int number) {
//        NumberWorker nw = new NumberWorker();
        assertTrue(NumberWorker.IsPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 12321, 222, 3333, 4444, 555 })
    public void isPrimeForNotPrimes(int number) {
//        NumberWorker nw = new NumberWorker();
        assertFalse(NumberWorker.IsPrime(number));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, -100})
    public void isPrimeForIncorrectNumbers(int number) {
//        NumberWorker nw = new NumberWorker();
//        IllegalNumberException exception =
        assertThrows(
            IllegalNumberException.class,()-> {
                    NumberWorker.IsPrime(number);
            }    
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void CheckDigitsSum(int number, int result) {
//        NumberWorker nw = new NumberWorker();
        assertEquals(result, NumberWorker.DigitsSum(number));
    }
    
}
