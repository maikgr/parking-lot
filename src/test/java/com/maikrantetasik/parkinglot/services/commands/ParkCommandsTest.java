package com.maikrantetasik.parkinglot.services.commands;
import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ParkCommandsTest {
    private ParkingLotCommands commands;
    private ParkingLot mockLot;

    @BeforeEach
    void setUp() {
        commands = new ParkCommands();

        Map<Integer, Car> filledSlots = new HashMap<>();
        filledSlots.put(1, new Car("none", "test"));
        filledSlots.put(3, new Car("none", "test2"));

        Queue<Integer> freeSlots = new LinkedList<>();
        freeSlots.add(2);
        freeSlots.add(4);

        mockLot = new ParkingLot();
        mockLot.setSize(4);
        mockLot.setFreeSlots(freeSlots);
        mockLot.setFilledSlots(filledSlots);
    }

    @Test
    void shouldIgnoreExtraArguments() {
        String[] args = new String[] { "TEST REG NUMBER", "COLOR", "extra", "arguments" };

        Assertions.assertDoesNotThrow(() -> commands.execute(mockLot, args));

        CommandResult res = commands.execute(mockLot, args);
        Assertions.assertNotNull(res);
    }

    @Test
    void shouldAddCarToFilledSlot() {
        String regNumber = "TEST REG NUMBER";
        String color = "COLOR";
        String[] args = new String[] { regNumber, color };

        CommandResult res = commands.execute(mockLot, args);

        Assertions.assertTrue(res.getCurrent().getFilledSlots().containsValue(new Car(color, regNumber)));
    }

    @Test
    void shouldTakeFreeSlotOnPark() {
        String regNumber = "TEST REG NUMBER";
        String color = "COLOR";
        String[] args = new String[] { regNumber, color };

        CommandResult res = commands.execute(mockLot, args);

        Assertions.assertEquals(1, res.getCurrent().getFreeSlots().size());
        Assertions.assertEquals(3, res.getCurrent().getFilledSlots().size());
    }

    @Test
    void shouldReturnAllocatedNumberOnMessage() {
        String regNumber = "TEST REG NUMBER";
        String color = "COLOR";
        String[] args = new String[] { regNumber, color };

        CommandResult res = commands.execute(mockLot, args);

        Assertions.assertNotNull(res.getMessage());
        Assertions.assertTrue(res.getMessage().contains("2") ||
                res.getMessage().contains("4"));
    }

    @Test
    void shouldDoNothingOnFullLot() {
        String regNumber = "TEST REG NUMBER";
        String color = "COLOR";
        String[] args = new String[] { regNumber, color };
        mockLot.setFreeSlots(new LinkedList<>());

        Assertions.assertDoesNotThrow(() -> commands.execute(mockLot, args));

        CommandResult res = commands.execute(mockLot, args);
        Assertions.assertEquals(mockLot.getFilledSlots().size(), res.getCurrent().getFilledSlots().size());
        Assertions.assertEquals(0, res.getCurrent().getFreeSlots().size());
    }

    @Test
    void shouldReturnMessageOnFullLot() {
        String regNumber = "TEST REG NUMBER";
        String color = "COLOR";
        String[] args = new String[] { regNumber, color };
        mockLot.setFreeSlots(new LinkedList<>());

        CommandResult res = commands.execute(mockLot, args);

        Assertions.assertNotNull(res.getMessage());
    }

    @Test
    void shouldAbleToTakeAllFreeSlot() {
        String[] firstArgs = new String[] { "2" };
        String[] secondArgs = new String[] { "4" };

        CommandResult firstRes = commands.execute(mockLot, firstArgs);
        CommandResult secondRes = commands.execute(firstRes.getCurrent(), secondArgs);

        Assertions.assertTrue(secondRes.getCurrent().getFilledSlots().containsKey(2));
        Assertions.assertTrue(secondRes.getCurrent().getFilledSlots().containsKey(4));
        Assertions.assertEquals(0, secondRes.getCurrent().getFreeSlots().size());
    }

    @Test
    void shouldThrowOnNullLot() {
        String[] args = new String[] { "1" };

        Assertions.assertThrows(IllegalStateException.class, () -> commands.execute(null, args));
    }
}

