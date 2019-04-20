package com.maikrantetasik.parkinglot.entities;

public class CommandResult {
    private ParkingLot Current;
    private String message;

    public CommandResult(ParkingLot current, String message) {
        Current = current;
        this.message = message;
    }

    public ParkingLot getCurrent() {
        return Current;
    }

    public void setCurrent(ParkingLot current) {
        Current = current;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
