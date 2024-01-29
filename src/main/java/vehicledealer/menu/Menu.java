package vehicledealer.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final List<MenuOption> menuOptions = new ArrayList<>(List.of(
            new MenuOption("Wyjdź", () -> System.out.println("Zapraszamy ponownie!"))
    ));

    public Menu(List<MenuOption> menuOptions) {
        this.menuOptions.addAll(menuOptions);
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.println("Wybierz liczbę: ");
            choice = scanner.nextInt();
            System.out.println("\n");
            executeOption(choice);
            System.out.println("\n");
        } while (choice != 0);
    }

    private void displayMenu() {
        for (int i = 0; i < menuOptions.size(); i++) {
            System.out.println(i + ". " + menuOptions.get(i).getDescription());
        }
    }

    private void executeOption(int number) {
        if (number < 0 || number >= menuOptions.size()) {
            System.out.println("Numer niepoprawny, spróbuj jeszcze raz");
        } else {
            menuOptions.get(number).execute();
        }
    }
}
