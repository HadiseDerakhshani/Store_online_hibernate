package models;

public enum ShoesType {
    SPORT(0, "Sport"),
    OFFICIAL(1, "Official");
    private int index;
    private String title;

    ShoesType(int index, String title) {
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
