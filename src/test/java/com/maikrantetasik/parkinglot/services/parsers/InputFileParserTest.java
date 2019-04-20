package com.maikrantetasik.parkinglot.services.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class InputFileParserTest {
    private FileParser parser;

    @BeforeEach
    void setUp() {
        parser = new InputFileParser();
    }

    @Test
    void shouldParseValidFile() throws IOException {
        File resource = new File("src/test/resources/input_test.txt");
        String validPath = resource.getPath();
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
    void shouldParseEmptyFile() throws IOException {
        File resource = new File("src/test/resources/empty_test.txt");
        String validPath = resource.getPath();

        String[] res = parser.parse(validPath);

        assertNotNull(res);
        assertEquals(0, res.length);
    }

    @Test
    void shouldParseBigFile() throws IOException {
        File resource = new File("src/test/resources/5000lines.txt");
        String bigFilePath = resource.getPath();
        int expectLength = 5000;

        assertDoesNotThrow(() -> parser.parse(bigFilePath));

        String[] res = parser.parse(bigFilePath);
        assertNotNull(res);
        assertEquals(expectLength, res.length);
    }

    @Test
    void shouldThrowErrorOnInvalidPath() {
        String invalidPath = "test/invalid";

        assertThrows(IOException.class, () -> parser.parse(invalidPath));
    }
}
