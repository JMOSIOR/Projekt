package vehicledealer.vehicles.database;

import vehicledealer.vehicles.model.Car;
import vehicledealer.vehicles.model.Motorcycle;
import vehicledealer.vehicles.model.Vehicle;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CsvVehiclesDatabase implements VehiclesDatabase {
    private static final String CARS_FILE_LOCATION = "data/cars.csv";
    private static final String MOTORCYCLES_FILE_LOCATION = "data/motorcycles.csv";

    private static <T extends Vehicle> T createVehicle(String[] data, Class<T> vehicleType) {
        T vehicle = null;
        try {
            if (vehicleType == Car.class) {
                return (T) new Car(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            } else if (vehicleType == Motorcycle.class) {
                return (T) new Motorcycle(Integer.parseInt(data[0]), data[1], data[2], Integer.parseInt(data[3]), data[4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    @Override
    public List<Car> getAllCars() {
        return loadFromCsv(CARS_FILE_LOCATION, Car.class);
    }

    @Override
    public List<Motorcycle> getAllMotorcycles() {
        return loadFromCsv(MOTORCYCLES_FILE_LOCATION, Motorcycle.class);
    }

    @Override
    public void saveCars(List<Car> cars) {
        saveToCsv(CARS_FILE_LOCATION, cars);
    }

    @Override
    public void saveMotorcycles(List<Motorcycle> motorcycles) {
        saveToCsv(MOTORCYCLES_FILE_LOCATION, motorcycles);
    }

    private <T extends Vehicle> void saveToCsv(String filePath, List<T> vehicleList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehicle vehicle : vehicleList) {
                writer.write(vehicle.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania danych do pliku: " + filePath);
        }
    }

    private <T extends Vehicle> List<T> loadFromCsv(String filePath, Class<T> vehicleType) {
        List<T> results = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                T vehicle = createVehicle(data, vehicleType);
                results.add(vehicle);
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania danych z pliku: " + filePath);
        }
        return results;
    }
}
