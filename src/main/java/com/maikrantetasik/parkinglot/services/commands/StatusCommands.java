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
        builder.append("Slot No. \t Registration No \t Colour");
        builder.append(System.lineSeparator());

        for (Map.Entry<Integer, Car> park : parkingLot.getFilledSlots().entrySet()) {
            int slot = park.getKey();
            Car car = park.getValue();
            builder.append(String.format("%s \t\t %s \t\t %s", slot, car.getRegNumber(), car.getColor()));
            builder.append(System.lineSeparator());
        }

        return new CommandResult(parkingLot, builder.toString());
    }
}
