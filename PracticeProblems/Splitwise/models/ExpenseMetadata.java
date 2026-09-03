package Splitwise.models;

public class ExpenseMetadata {
    private final String name;
    private final String imageUrl;
    private final String notes;

    public ExpenseMetadata(String name, String imageUrl, String notes) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNotes() {
        return notes;
    }
}
