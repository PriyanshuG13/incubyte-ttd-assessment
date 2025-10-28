package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator calc;

    @BeforeEach
    void setup() {
        calc = new StringCalculator();
    }

    @Test
    void returnsZeroForEmptyString() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void returnsNumberForSingleValue() {
        assertEquals(5, calc.add("5"));
    }

    @Test
    void addsTwoNumbersSeparatedByComma() {
        assertEquals(8, calc.add("3,5"));
    }

    @Test
    void handlesNewlinesBetweenNumbers() {
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    void supportsCustomDelimiter() {
        assertEquals(3, calc.add("//;\n1;2"));
    }

    @Test
    void supportsMultiCharDelimiter() {
        assertEquals(6, calc.add("//[***]\n1***2***3"));
    }

    @Test
    void supportsMultipleDelimiters() {
        assertEquals(6, calc.add("//[*][%]\n1*2%3"));
    }

    @Test
    void throwsExceptionForNegatives() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            calc.add("1,-2,3,-5");
        });
        assertTrue(ex.getMessage().contains("-2"));
        assertTrue(ex.getMessage().contains("-5"));
    }

    @Test
    void throwsExceptionForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> calc.add("1,a,3"));
    }
}
