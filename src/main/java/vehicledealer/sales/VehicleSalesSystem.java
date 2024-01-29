package vehicledealer.sales;

import vehicledealer.vehicles.database.CsvVehiclesDatabase;
import vehicledealer.vehicles.database.VehiclesDatabase;
import vehicledealer.vehicles.model.Car;
import vehicledealer.vehicles.model.Motorcycle;
import vehicledealer.vehicles.model.Vehicle;
import vehicledealer.vehicles.model.VehicleType;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VehicleSalesSystem {

    private static final VehiclesDatabase db = new CsvVehiclesDatabase();
    private static List<Car> carList;
    private static List<Motorcycle> motorcycleList;
    private final Random idGenerator = new Random();

    public VehicleSalesSystem() {
        carList = db.getAllCars();
        motorcycleList = db.getAllMotorcycles();
    }

    public void addNewCar() {
        addNewVehicle(VehicleType.CAR);
    }

    public void addNewMotorcycle() {
        addNewVehicle(VehicleType.MOTOCYCLE);
    }

    public void displayAllToSale() {
        System.out.println("Lista pojazdów na sprzedaż:");
        Stream.concat(carList.stream(), motorcycleList.stream())
                .forEach(System.out::println);
    }

    public void displayCarsToSale() {
        System.out.println("Lista samochodów na sprzedaż:");
        carList.forEach(System.out::println);
    }

    public void displayMotocyclesToSale() {
        System.out.println("Lista motocykli na sprzedaż:");
        motorcycleList.forEach(System.out::println);
    }

    public void editVehicle() {
        System.out.println("Podaj id pojazdu do edycji: ");
        int id = getId();
        Set<Integer> carsIds = carList.stream().map(Vehicle::getId).collect(Collectors.toSet());

        if (carsIds.contains(id)) {
            carList = carList.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
            addVehicle(VehicleType.CAR, id);
        } else {
            motorcycleList = motorcycleList.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
            addVehicle(VehicleType.MOTOCYCLE, id);
        }
    }

    public void deleteVehicle() {
        System.out.println("Podaj id pojazdu do usunięcia: ");
        int id = getId();
        Set<Integer> carsIds = carList.stream().map(Vehicle::getId).collect(Collectors.toSet());

        if (carsIds.contains(id)) {
            carList = carList.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
            db.saveCars(carList);
        } else {
            motorcycleList = motorcycleList.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
            db.saveMotorcycles(motorcycleList);
        }
    }

    private void addNewVehicle(VehicleType vehicleType) {
        addVehicle(vehicleType, idGenerator.nextInt(10000));
    }

    private void addVehicle(VehicleType vehicleType, int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Marka: ");
        String brand = scanner.next();

        System.out.print("Model: ");
        String model = scanner.next();

        System.out.print("Rok produkcji: ");
        int year = scanner.nextInt();

        if (vehicleType == VehicleType.CAR) {
            System.out.print("Liczba drzwi: ");
            int numberOfDoors = scanner.nextInt();
            Car newCar = new Car(id, brand, model, year, numberOfDoors);
            carList.add(newCar);
            db.saveCars(carList);
            System.out.println("Dodano pojazd do sprzedaży: " + newCar);
        } else if (vehicleType == VehicleType.MOTOCYCLE) {
            System.out.print("Typ: ");
            String type = scanner.next();
            Motorcycle newMotorcycle = new Motorcycle(id, brand, model, year, type);
            motorcycleList.add(newMotorcycle);
            db.saveMotorcycles(motorcycleList);
            System.out.println("Dodano pojazd do sprzedaży: " + newMotorcycle);
        }
    }

    private int getId() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}