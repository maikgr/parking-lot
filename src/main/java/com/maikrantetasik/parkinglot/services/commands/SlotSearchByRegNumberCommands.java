package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.Car;
import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlotSearchByRegNumberCommands implements ParkingLotCommands {

    public CommandResult execute(ParkingLot parkingLot, String[] args) {

        if (parkingLot == null) {
            throw new IllegalStateException("Invalid parking lot state");
        }

        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid search query");
        }

        String query = args[0].toLowerCase();
        List<String> foundSlots = new ArrayList<>();
        for (Map.Entry<Integer, Car> parked : parkingLot.getFilledSlots().entrySet()) {
            String regNumber = parked.getValue().getRegNumber().toLowerCase();
            if (regNumber.equals(query)) {
                foundSlots.add(String.valueOf(parked.getKey()));
            }
        }

        if (foundSlots.size() > 0) {
            return new CommandResult(parkingLot, String.join(", ", foundSlots));
        }

        return new CommandResult(parkingLot, "Not found");
    }
}
