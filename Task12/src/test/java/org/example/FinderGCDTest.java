package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("gcd finder is working when...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class FinderGCDTest {

    private FinderGCD solver;

    @BeforeEach
    public void setUp() {
        solver = new FinderGCD();
    }

    @ParameterizedTest(name = "find gcd on variable values {0},{1},{2}")
    @CsvSource(value = {
            "9, 3 , 3",
            "125, 25, 25",
            "54, 36, 18 "
    })
    public void on_variable_values_numbers(int a, int b, int expected) {
        int actual = solver.findGcd(a, b);

        assertEquals(actual, expected);
    }


    @DisplayName("find gcd is working when numbers is prime")
    @Nested
    public class ForPrime {

        @ParameterizedTest(name = "return 1 for prime numbers {0},{1}:answer {2}")
        @CsvSource(value = {
                "7, 5, 1",
                "13, 17, 1 ",
                "1, 1, 1"
        })
        public void on_prime_numbers_return_one(int a, int b, int expected) {
            int actual = solver.findGcd(a, b);

            assertEquals(actual, expected);
        }
    }

    @DisplayName("find gcd working when numbers is the same")
    @Nested
    public class ForSameNumbers {

        @ParameterizedTest(name = "return input numbers {2} if this numbers {0},{1} the same ")
        @CsvSource(value = {
                "12, 12, 12",
                "30, 30, 30",
                "0, 0, 0"
        })
        public void on_numbers_is_the_same_return_input_number(int a, int b, int expected) {
            int actual = solver.findGcd(a, b);

            assertEquals(actual, expected);
        }
    }

    @DisplayName("find gcd when first or second number equal zero and get exception")
    @Nested
    public class ForNumberEqualZero {

        @ParameterizedTest(name = "expected exception ,input numbers:{0},{1}")
        @CsvSource(value = {
                "14, 0",
                "0, 19"
        })
        public void on_numbers_if_first_or_second_numbers_is_zero(int a, int b) {
            Throwable thrown = assertThrows(IllegalArgumentException.class, () -> solver.findGcd(a, b));
        }
    }
}
