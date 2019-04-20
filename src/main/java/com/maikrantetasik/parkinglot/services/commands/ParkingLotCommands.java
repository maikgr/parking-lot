package com.maikrantetasik.parkinglot.services.commands;

import com.maikrantetasik.parkinglot.entities.ParkingLot;

public interface ParkingLotCommands {
    ParkingLot execute(ParkingLot parkingLot, String[] args);
}
