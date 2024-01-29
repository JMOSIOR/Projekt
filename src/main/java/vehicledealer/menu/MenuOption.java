package vehicledealer.menu;

public class MenuOption {
    private final String description;
    private final Runnable action;

    public MenuOption(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public void execute() {
        action.run();
    }

    public String getDescription() {
        return this.description;
    }
}
