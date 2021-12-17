package models;

public enum Grouping {
    ELECTRONIC(0, "Electronic"),
    SHOES(1, "Shoes"),
    READING(2, "Reading");
    private int index;
    private String title;

    Grouping(int index, String title) {
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
