package com.maikrantetasik.parkinglot.entities;

public class Car {
    private String color;
    private String regNumber;

    public Car(String color, String regNumber) {
        this.color = color;
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public String getRegNumber() {
        return regNumber;
    }
}