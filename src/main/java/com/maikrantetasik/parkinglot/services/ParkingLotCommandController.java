package com.maikrantetasik.parkinglot.services;

import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;
import com.maikrantetasik.parkinglot.services.commands.ParkingLotCommands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotCommandController implements CommandController {
    private Map<String, ParkingLotCommands> commandMap;
    private ParkingLot parkingLot;

    public ParkingLotCommandController(ParkingLotCommands createCommand,
                                       ParkingLotCommands parkCommand,
                                       ParkingLotCommands leaveCommand,
                                       ParkingLotCommands statusCommand,
                                       ParkingLotCommands searchRegNumberByColorCommand,
                                       ParkingLotCommands searchSlotNumberByColorCommand,
                                       ParkingLotCommands searchSlotNumberByRegNumberCommand) {
        this.commandMap = new HashMap<>();
        this.commandMap.put("create_parking_lot", createCommand);
        this.commandMap.put("park", parkCommand);
        this.commandMap.put("leave", leaveCommand);
        this.commandMap.put("status", statusCommand);
        this.commandMap.put("registration_numbers_for_cars_with_colour", searchRegNumberByColorCommand);
        this.commandMap.put("slot_numbers_for_cars_with_colour", searchSlotNumberByColorCommand);
        this.commandMap.put("slot_number_for_registration_number", searchSlotNumberByRegNumberCommand);
    }

    public String execute(String[] args) {
        if (args == null || args.length == 0 || args[0] == null) {
            throw new IllegalArgumentException("Commands required");
        }

        String command = args[0].toLowerCase();
        if (!commandMap.containsKey(command)) {
            throw new IllegalArgumentException("Unrecognized command " + command);
        }

        try {
            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
            CommandResult res = commandMap.get(command).execute(parkingLot, commandArgs);
            this.parkingLot = res.getCurrent();

            return res.getMessage();
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            return e.getMessage();
        }
    }
}

