package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

import java.util.Map;

public class StatusCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {
        if (parkingLot == null) {
            throw new IllegalStateException("Invalid parking lot state");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Slot No.    Registration No    Colour");

        for (Map.Entry<Integer, Car> park : parkingLot.getFilledSlots().entrySet()) {
            int slot = park.getKey();
            Car car = park.getValue();
            builder.append(System.lineSeparator());
            builder.append(String.format("%s           %s      %s", slot, car.getRegNumber(), car.getColor()));
        }

        return new CommandResult(parkingLot, builder.toString());
    }
}
