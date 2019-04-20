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


public class LeaveCommandsTest {
    private ParkingLotCommands command;
    private ParkingLot mockLot;

    @BeforeEach
    void setUp() {
        command = new LeaveCommands();

        Map<Integer, Car> filledSlots = new HashMap<>();
        filledSlots.put(1, new Car("none", "test"));
        filledSlots.put(2, new Car("none", "test2"));

        Queue<Integer> freeSlots = new LinkedList<>();
        freeSlots.add(3);

        mockLot = new ParkingLot();
        mockLot.setSize(3);
        mockLot.setFreeSlots(freeSlots);
        mockLot.setFilledSlots(filledSlots);
    }

    @Test
    void shouldIgnoreExtraArguments() {
        String[] args = new String[] { "1", "invalid", "extra", "args" };

        assertDoesNotThrow(() -> command.execute(mockLot, args));

        CommandResult res = command.execute(mockLot, args);
        assertNotNull(res);
    }


    @ParameterizedTest
    @ValueSource(strings = { "1", "2" })
    void shouldFreeSlotOnLeave(String leaveSlot) {
        String[] args = new String[] { leaveSlot };

        CommandResult res = command.execute(mockLot, args);

        assertTrue(res.getCurrent().getFreeSlots().contains(Integer.valueOf(leaveSlot)));
        assertFalse(res.getCurrent().getFilledSlots().containsKey(Integer.valueOf(leaveSlot)));
    }

    @Test
    void shouldReturnFreedSlotNumberOnMessage() {
        String[] args = new String[] { "1" };

        CommandResult res = command.execute(mockLot, args);

        assertNotNull(res.getMessage());
        assertTrue(res.getMessage().contains("1"));
    }

    @Test
    void shouldDoNothingOnLeavingFreeSlot() {
        String[] args = new String[] { "3" };

        assertDoesNotThrow(() -> command.execute(mockLot, args));

        CommandResult res = command.execute(mockLot, args);
        assertNotNull(res.getCurrent());
        assertEquals(mockLot.getFilledSlots().size(), res.getCurrent().getFilledSlots().size());
        assertEquals(mockLot.getFreeSlots().size(), res.getCurrent().getFreeSlots().size());
    }

    @ParameterizedTest
    @ValueSource(strings = { "10", "0", "-1"})
    void shouldDoNothingOnInvalidSlot(String leaveSlot) {
        String[] args = new String[] { leaveSlot };

        assertDoesNotThrow(() -> command.execute(mockLot, args));

        CommandResult res = command.execute(mockLot, args);
        assertNotNull(res.getCurrent());
        assertEquals(mockLot.getFilledSlots().size(), res.getCurrent().getFilledSlots().size());
        assertEquals(mockLot.getFreeSlots().size(), res.getCurrent().getFreeSlots().size());
    }

    @ParameterizedTest
    @ValueSource(strings = { "10", "0", "-1"})
    void shouldReturnMessageOnInvalidSlot(String leaveSlot) {
        String[] args = new String[] { leaveSlot };

        assertDoesNotThrow(() -> command.execute(mockLot, args));

        CommandResult res = command.execute(mockLot, args);
        assertNotNull(res.getMessage());
    }

    @Test
    void shouldThrowOnNullLot() {
        String[] args = new String[] { "1" };

        assertThrows(IllegalStateException.class, () -> command.execute(null, args));
    }
}
