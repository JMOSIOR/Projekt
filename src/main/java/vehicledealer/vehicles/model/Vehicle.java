package vehicledealer.vehicles.model;

import vehicledealer.vehicles.database.utils.CsvSerializable;

public abstract class Vehicle implements CsvSerializable {

    int id;
    String brand;
    String model;
    int year;

    public Vehicle(int id, String brand, String model, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID:" + id + " -> " + brand + " " + model + " (" + year + ")";
    }

    public int getId() {
        return id;
    }
}