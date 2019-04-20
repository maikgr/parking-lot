package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class StatusCommandsTest {private ParkingLotCommands commands;
    private ParkingLot mockLot;

    @BeforeEach
    void setUp() {
        commands = new ParkCommands();

        Map<Integer, Car> filledSlots = new HashMap<>();
        filledSlots.put(1, new Car("white", "5N1AA0N"));
        filledSlots.put(2, new Car("red", "FN6597"));
        filledSlots.put(3, new Car("red", "1FTYY9"));

        Queue<Integer> freeSlots = new LinkedList<>();
        freeSlots.add(4);
        freeSlots.add(5);

        mockLot = new ParkingLot();
        mockLot.setSize(5);
        mockLot.setFreeSlots(freeSlots);
        mockLot.setFilledSlots(filledSlots);
    }

    @Test
    void shouldIgnoreArguments() {
        String[] args = new String[] { "should", "ignore", "these" };

        assertDoesNotThrow(() -> commands.execute(mockLot, args));

        CommandResult res = commands.execute(mockLot, args);
        assertNotNull(res);
    }

    @Test
    void shouldReturnFilledSlotsDetails() {
        String[] args = new String[] { "white" };

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
        assertTrue(res.getMessage().contains("1"));
        assertTrue(res.getMessage().contains("2"));
        assertTrue(res.getMessage().contains("3"));
        assertTrue(res.getMessage().contains("white"));
        assertTrue(res.getMessage().contains("red"));
        assertTrue(res.getMessage().contains("5N1AA0N"));
        assertTrue(res.getMessage().contains("FN6597"));
        assertTrue(res.getMessage().contains("1FTYY9"));
    }

    @Test
    void shouldReturnMessageOnEmptyLot() {
        String[] args = new String[] { "" };
        ParkingLot emptyLot = new ParkingLot();
        emptyLot.setSize(1);
        emptyLot.setFreeSlots(new LinkedList<>());
        emptyLot.setFilledSlots(new HashMap<>());

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
    }

    @Test
    void shouldThrowOnNullLot() {
        String[] args = new String[] { "" };

        assertThrows(IllegalStateException.class, () -> commands.execute(null, args));
    }
}
