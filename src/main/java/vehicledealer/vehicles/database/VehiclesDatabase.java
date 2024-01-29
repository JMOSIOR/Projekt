package vehicledealer.vehicles.database;

import vehicledealer.vehicles.model.Car;
import vehicledealer.vehicles.model.Motorcycle;

import java.util.List;

public interface VehiclesDatabase {
    List<Car> getAllCars();

    void saveCars(List<Car> cars);


    List<Motorcycle> getAllMotorcycles();

    void saveMotorcycles(List<Motorcycle> motorcycles);

}
