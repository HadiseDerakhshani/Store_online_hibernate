package models;

public enum BuyStatus {
    WAITING(0, "waiting"),
    END(1, "end");
    private final int index;
    private final String title;

    BuyStatus(int index, String title) {
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
