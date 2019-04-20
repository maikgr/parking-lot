package com.maikrantetasik.parkinglot.services.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputFileParserTest {
    private FileParser parser;

    @BeforeEach
    void setUp() {
        parser = new InputFileParser();
    }

    @Test
    void shouldParseValidFile() {
        String validPath = "/input_test.txt";
        String[] expect = new String[] {
                "create_parking_lot 6",
                "park KA-01-HH-1234 White",
                "park KA-01-HH-9999 White",
                "park KA-01-BB-0001 Black",
                "park KA-01-HH-7777 Red",
                "park KA-01-HH-2701 Blue",
                "park KA-01-HH-3141 Black",
                "leave 4",
                "status",
                "park KA-01-P-333 White",
                "park DL-12-AA-9999 White",
                "registration_numbers_for_cars_with_colour White",
                "slot_numbers_for_cars_with_colour White",
                "slot_number_for_registration_number KA-01-HH-3141",
                "slot_number_for_registration_number MH-04-AY-1111"
        };

        String[] res = parser.parse(validPath);

        assertNotNull(res);
        assertTrue(res.length > 0);
        assertArrayEquals(expect, res);
    }

    @Test
    void shouldParseEmptyFile() {
        String validPath = "/empty_test.txt";

        String[] res = parser.parse(validPath);

        assertNotNull(res);
        assertEquals(0, res.length);
    }

    @Test
    void shouldParseBigFile() {
        String bigFilePath = "/5000lines.txt";
        int expectLength = 5000;

        assertDoesNotThrow(() -> parser.parse(bigFilePath));

        String[] res = parser.parse(bigFilePath);
        assertNotNull(res);
        assertEquals(expectLength, res.length);
    }

    @Test
    void shouldThrowErrorOnInvalidPath() {
        String invalidPath = "test/invalid";

        assertThrows(IllegalArgumentException.class, () -> parser.parse(invalidPath));
    }
}
