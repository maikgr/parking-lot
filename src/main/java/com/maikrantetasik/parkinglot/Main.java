package com.maikrantetasik.parkinglot;

import com.maikrantetasik.parkinglot.services.CommandController;
import com.maikrantetasik.parkinglot.services.ParkingLotCommandController;
import com.maikrantetasik.parkinglot.services.commands.*;
import com.maikrantetasik.parkinglot.services.parsers.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandController controller = new ParkingLotCommandController(
                new CreateCommands(),
                new ParkCommands(),
                new LeaveCommands(),
                new StatusCommands(),
                new RegSearchByColorCommands(),
                new SlotSearchByColorCommands(),
                new SlotSearchByRegNumberCommands()
        );

        if (args.length > 0) {
            try {
                FileParser parser = new InputFileParser();
                String[] commands = parser.parse(args[0]);

                for (String command : commands) {
                    String message = controller.execute(command.trim().split(" "));
                    System.out.println(message);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();

                if (command.toLowerCase().equals("exit")) {
                    break;
                }

                try {
                    String message = controller.execute(command.trim().split(" "));
                    System.out.println(message);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}