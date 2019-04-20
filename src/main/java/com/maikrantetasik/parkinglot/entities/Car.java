package com.maikrantetasik.parkinglot.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return color.equals(car.color) &&
                regNumber.equals(car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, regNumber);
    }
}