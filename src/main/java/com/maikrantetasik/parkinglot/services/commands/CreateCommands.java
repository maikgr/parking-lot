package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CreateCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {
        int size;
        try {
            size = Integer.valueOf(args[0]);
            if (size <= 0) {
                throw new NumberFormatException();
            }
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid size, please input a positive number");
        }

        String message = String.format("Created a parking lot with %s slots", size);

        return new CommandResult(initializeLot(size), message);
    }

    private ParkingLot initializeLot(int size) {
        Map<Integer, Car> filledSlots = new HashMap<>();
        Queue<Integer> freeSlots = new LinkedList<>();

        for (int i = 1; i <= size; ++i) {
            freeSlots.add(i);
        }

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setSize(size);
        parkingLot.setFreeSlots(freeSlots);
        parkingLot.setFilledSlots(filledSlots);

        return parkingLot;
    }
}