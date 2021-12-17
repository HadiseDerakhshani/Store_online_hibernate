package models;

public enum ElectronicType {
    TELEVISION(0, "Television"),
    RADIO(1, "Radio");
    private final int index;
    private final String title;

    ElectronicType(int index, String title) {
        this.index = index;
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }
}
