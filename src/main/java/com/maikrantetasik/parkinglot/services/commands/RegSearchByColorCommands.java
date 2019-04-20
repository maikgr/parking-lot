package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegSearchByColorCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {
        if (parkingLot == null) {
            throw new IllegalStateException("Invalid parking lot state");
        }

        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid search query");
        }

        String query = args[0].toLowerCase();
        List<String> foundRegs = new ArrayList<>();
        for (Map.Entry<Integer, Car> parked : parkingLot.getFilledSlots().entrySet()) {
            String carColor = parked.getValue().getColor().toLowerCase();
            if (carColor.equals(query)) {
                foundRegs.add(parked.getValue().getRegNumber());
            }
        }

        if (foundRegs.size() > 0) {
            return new CommandResult(parkingLot, String.join(", ", foundRegs));
        }

        return new CommandResult(parkingLot, "Not Found");
    }
}