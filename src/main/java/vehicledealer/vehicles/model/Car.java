package vehicledealer.vehicles.model;

public class Car extends Vehicle {
    int numberOfDoors;

    public Car(int id, String brand, String model, int year, int numberOfDoors) {
        super(id, brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + numberOfDoors + " drzwi";
    }

    @Override
    public String toCsv() {
        return id + "," + brand + "," + model + "," + year + "," + numberOfDoors + "\n";
    }
}