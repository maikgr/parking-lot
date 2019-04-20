package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCommandsTest {
    private ParkingLotCommands commands;

    @BeforeEach
    void setUp() {
        commands = new CreateCommands();
    }

    @Test
    void shouldIgnoreExtraArguments() {
        String[] args = new String[] { "5", "10", "extra" };

        assertDoesNotThrow(() -> commands.execute(null, args));

        CommandResult res = commands.execute(null, args);
        assertNotNull(res);
        assertEquals(5, res.getCurrent().getSize());
    }

    @Test
    void shouldCreateEmptyParkingLot() {
        int size = 6;
        String[] args = new String[] { String.valueOf(size) };

        CommandResult res = commands.execute(null, args);

        assertNotNull(res);
        assertEquals(size, res.getCurrent().getSize());
        assertEquals(size, res.getCurrent().getFreeSlots().size());
        assertEquals(0, res.getCurrent().getFilledSlots().size());
    }

    @Test
    void shouldReturnMessageWithParkingLotSize() {
        String size = "6";
        String[] args = new String[] { size };

        CommandResult res = commands.execute(null, args);

        assertNotNull(res);
        assertTrue(res.getMessage().contains(size));
    }

    @Test
    void shouldRecreateExistingParkingLot() {
        Map<Integer, Car> oldSlots = new HashMap<>();
        oldSlots.put(1, new Car("none", "test"));
        oldSlots.put(2, new Car("none", "test2"));

        Queue<Integer> oldFree = new LinkedList<>();
        oldFree.add(3);

        ParkingLot oldLot = new ParkingLot();
        oldLot.setSize(3);
        oldLot.setFilledSlots(oldSlots);
        oldLot.setFreeSlots(oldFree);

        int newSize = 6;
        String[] args = new String[] { String.valueOf(newSize) };

        CommandResult res = commands.execute(oldLot, args);

        assertNotNull(res);
        assertEquals(newSize, res.getCurrent().getSize());
        assertEquals(newSize, res.getCurrent().getFreeSlots().size());
        assertEquals(0, res.getCurrent().getFilledSlots().size());
    }

    @Test
    void shouldCreateHugeParkingLot() {
        int size = 10000000;
        String[] args = new String[] { String.valueOf(size) };

        CommandResult res = commands.execute(null, args);

        assertNotNull(res);
        assertEquals(size, res.getCurrent().getSize());
        assertEquals(size, res.getCurrent().getFreeSlots().size());
        assertEquals(0, res.getCurrent().getFilledSlots().size());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1, Integer.MIN_VALUE })
    void shouldThrowOnSizeLessThanOne(int size) {
        String[] args = new String[] { String.valueOf(size) };

        assertThrows(IllegalArgumentException.class, () -> commands.execute(null, args));
    }
}
