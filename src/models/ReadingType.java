package models;

public enum ReadingType {
    BOOK(0, "Book"),
    JOURNAL(1, "Journal");
    private int index;
    private String title;

    ReadingType(int index, String title) {
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
