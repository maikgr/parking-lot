package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

public class ParkCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {
        if (parkingLot == null) {
            throw new IllegalStateException("Invalid parking lot state");
        }

        if (args == null || args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments specified");
        }

        if (parkingLot.getFreeSlots().size() == 0) {
            return new CommandResult(parkingLot, "Sorry, parking lot is full");
        }

        Car parkedCar = new Car(args[1], args[0]);
        int slot = parkingLot.getFreeSlots().remove();
        parkingLot.getFilledSlots().put(slot, parkedCar);

        return new CommandResult(parkingLot, "Allocated slot number: " + slot);
    }
}
