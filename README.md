## Parking Lot Project
To run this project, you need to install dependencies by executing
```bin/setup```

After that you can run this project in two ways
1. Execute `bin/parking_lot` to run the application as interactive mode where you can input the command manually.
2. Execute `bin/parking_lo <input_file_path>` to make the application read list of commands from a file instead.

### Command List
- `create_parking_lot <size>` to create parking lot with defined size.
- `park <registration_number> <color>` to get an allocated parking slot for a car.
- `leave <slot_number>` to free up a parking slot number/when a car leave its allocated slot.
- `status` to get the details of parking lot and parked cars.
- `registration_numbers_for_cars_with_colour <color>` to search for cars registration numbers by color
- `slot_numbers_for_cars_with_colour <color>` to search for allocated parking slot for cars by color
- `slot_number_for_registration_number <registration_number>` to search for an allocation parking slot by car's registration number
- `exit` to terminate the interactive mode