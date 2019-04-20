package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.CommandResult;
import com.maikrantetasik.parkinglot.entities.ParkingLot;

public interface ParkingLotCommands {
    CommandResult execute(ParkingLot parkingLot, String[] args);
}
