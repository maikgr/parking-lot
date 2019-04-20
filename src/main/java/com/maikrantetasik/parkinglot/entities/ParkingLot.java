package com.maikrantetasik.parkinglot.entities;

import java.util.Map;
import java.util.Queue;

public class ParkingLot {
    private int size;
    private Queue<Integer> freeSlots;
    private Map<Integer, Car> filledSlots;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Queue<Integer> getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(Queue<Integer> freeSlots) {
        this.freeSlots = freeSlots;
    }

    public Map<Integer, Car> getFilledSlots() {
        return filledSlots;
    }

    public void setFilledSlots(Map<Integer, Car> filledSlots) {
        this.filledSlots = filledSlots;
    }
}

