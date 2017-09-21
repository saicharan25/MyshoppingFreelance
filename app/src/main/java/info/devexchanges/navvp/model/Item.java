package info.devexchanges.navvp.model;

public class Item {
    private final int id;
    private final String name;

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
