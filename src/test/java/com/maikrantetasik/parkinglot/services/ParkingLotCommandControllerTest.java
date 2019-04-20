package com.maikrantetasik.parkinglot.services;

import com.maikrantetasik.parkinglot.services.commands.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotCommandControllerTest {
    private CommandController controller;

    @BeforeEach
    void setUp() {
        controller = new ParkingLotCommandController(
                new CreateCommands(),
                new ParkCommands(),
                new LeaveCommands(),
                new StatusCommands(),
                new RegSearchByColorCommands(),
                new SlotSearchByColorCommands(),
                new SlotSearchByRegNumberCommands()
        );
    }

    @Test
    void shouldHaveCreateCommand() {
        String[] args = new String[]{"create_parking_lot", "6"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveParkCommand() {
        String[] args = new String[]{"park", "KA-01-HH-1234", "White"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveLeaveCommand() {
        String[] args = new String[]{"leave", "4"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveStatusCommand() {
        String[] args = new String[]{"status"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveSearchRegNumbersByColorCommand() {
        String[] args = new String[]{"registration_numbers_for_cars_with_colour", "white"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveSearchSlotNumbersByColorCommand() {
        String[] args = new String[]{"slot_numbers_for_cars_with_colour", "white"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @Test
    void shouldHaveSearchSlotNumbersByRegNumberCommand() {
        String[] args = new String[]{"slot_number_for_registration_number", "MH-04-AY-1111"};

        assertDoesNotThrow(() -> controller.execute(args));
        String res = controller.execute(args);
        assertNotNull(res);
        assertNotEquals("", res);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"test", "-1", "((!&&@())__$$#$#"})
    void shouldThrowOnUnexpectedCommands(String command) {
        String[] args = new String[]{command};

        assertThrows(IllegalArgumentException.class, () -> controller.execute(args));
    }
}
