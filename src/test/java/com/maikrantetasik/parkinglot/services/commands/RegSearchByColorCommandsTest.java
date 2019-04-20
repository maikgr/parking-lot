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

public class RegSearchByColorCommandsTest {
    private ParkingLotCommands commands;
    private ParkingLot mockLot;

    @BeforeEach
    void setUp() {
        commands = new ParkCommands();

        Map<Integer, Car> filledSlots = new HashMap<>();
        filledSlots.put(1, new Car("white", "5N1AA0N"));
        filledSlots.put(2, new Car("red", "FN6597"));
        filledSlots.put(3, new Car("red", "1FTYY9"));
        filledSlots.put(4, new Car("white", "5R9MV4X"));
        filledSlots.put(5, new Car("white", "Z27482"));

        mockLot = new ParkingLot();
        mockLot.setSize(5);
        mockLot.setFreeSlots(new LinkedList<>());
        mockLot.setFilledSlots(filledSlots);
    }

    @Test
    void shouldIgnoreExtraArguments() {
        String[] args = new String[] { "white", "red", "green", "blue" };

        assertDoesNotThrow(() -> commands.execute(mockLot, args));

        CommandResult res = commands.execute(mockLot, args);
        assertNotNull(res);
    }

    @Test
    void shouldReturnRegistrationNumbersOfFoundCars() {
        String[] args = new String[] { "red" };

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
        assertTrue(res.getMessage().contains("FN6597"));
        assertTrue(res.getMessage().contains("1FTYY9"));
    }

    @Test
    void shouldReturnMessageOnNotFound() {
        String[] args = new String[] { "invisible" };

        CommandResult res = commands.execute(mockLot, args);

        assertNotNull(res.getMessage());
    }

    @Test
    void shouldThrowOnNullLot() {
        String[] args = new String[] { "red" };

        assertThrows(IllegalStateException.class, () -> commands.execute(null, args));
    }
}
