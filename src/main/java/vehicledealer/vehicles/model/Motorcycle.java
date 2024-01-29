package vehicledealer.vehicles.model;

public class Motorcycle extends Vehicle {
    String type;

    public Motorcycle(int id, String brand, String model, int year, String type) {
        super(id, brand, model, year);
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + ", typ: " + type;
    }

    @Override
    public String toCsv() {
        return id + "," + brand + "," + model + "," + year + "," + type + "\n";
    }
}