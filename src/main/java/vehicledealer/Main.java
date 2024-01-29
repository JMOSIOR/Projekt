package vehicledealer;

import vehicledealer.menu.Menu;
import vehicledealer.menu.MenuOption;
import vehicledealer.sales.VehicleSalesSystem;

import java.util.List;

public class Main {
    private static final VehicleSalesSystem salesSystem = new VehicleSalesSystem();
    private static final Menu menu = new Menu(List.of(
            new MenuOption("Dodaj samochód do sprzedaży", salesSystem::addNewCar),
            new MenuOption("Dodaj motocykl do sprzedaży", salesSystem::addNewMotorcycle),
            new MenuOption("Wyświetl samochody na sprzedaż", salesSystem::displayCarsToSale),
            new MenuOption("Wyświetl motocykle do sprzedaży", salesSystem::displayMotocyclesToSale),
            new MenuOption("Wyświetl wszystkie pojazdy do sprzedaży", salesSystem::displayAllToSale),
            new MenuOption("Edytuj pojazd", salesSystem::editVehicle),
            new MenuOption("Usuń pojazd", salesSystem::deleteVehicle)
    ));

    public static void main(String[] args) {
        menu.show();
    }
}
