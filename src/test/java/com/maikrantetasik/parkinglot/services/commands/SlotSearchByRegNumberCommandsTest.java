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

public class SlotSearchByRegNumberCommandsTest {
    private ParkingLotCommands commands;
    private ParkingLot mockLot;

    @BeforeEach
    void setUp() {
        commands = new ParkCommands();

        Map<Integer, Car> filledSlots = new HashMap<>();
        filledSlots.put(1, new Car("white", "5N1AA0N"));
        filledSlots.put(2, new Car("red", "FN6597"));
        filledSlots.put(3, new Car("red", "1FTYY9"));
        filledSlots.put(6, new Car("white", "5R9MV4X"));
        filledSlots.put(9, new Car("white", "Z27482"));

        Queue<Integer> freeSlots = new LinkedList<>();
        freeSlots.add(4);
        freeSlots.add(5);
        freeSlots.add(7);
        freeSlots.add(8);

        mockLot = new ParkingLot();
        mockLot.setSize(9);
        mockLot.setFreeSlots(freeSlots);
        mockLot.setFilledSlots(filledSlots);
    }

    @Test
    void shouldIgnoreExtraArguments() {
        String[] args = new String[] { "5N1AA0N", "extra", "args" };

        assertDoesNotThrow(() -> commands.execute(mockLot, args));

        CommandResult res = commands.execute(mockLot, args);
        assertNotNull(res);
    }

    @Test
    void shouldReturnFoundSlotNumberOnMessage() {
        String[] args = new String[] { "Z27482" };

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
        assertTrue(res.getMessage().contains("9"));
    }

    @Test
    void shouldReturnMessageOnNotFound() {
        String[] args = new String[] { "none" };

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
    }

    @Test
    void shouldThrowOnNullLot() {
        String[] args = new String[] { "5R9MV4X" };

        assertThrows(IllegalStateException.class, () -> commands.execute(null, args));
    }
}
