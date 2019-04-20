package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

public class LeaveCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {
        if (parkingLot == null) {
            throw new IllegalStateException("Invalid parking lot state");
        }

        int leftSlot;
        try {
            leftSlot = Integer.valueOf(args[0]);
        }
        catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number");
        }

        String message = leftSlot + " is already empty";
        if (parkingLot.getFilledSlots().containsKey(leftSlot)) {
            parkingLot.getFilledSlots().remove(leftSlot);
            parkingLot.getFreeSlots().add(leftSlot);
            message = String.format("Slot number %s is free", leftSlot);
        }

        return new CommandResult(parkingLot, message);
    }
}
