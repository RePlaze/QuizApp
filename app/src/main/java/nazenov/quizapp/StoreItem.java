package nazenov.quizapp;

public class StoreItem {
    private String title;
    private int price;
    private boolean isPurchased;

    public StoreItem(String title, int price, boolean isPurchased) {
        this.title = title;
        this.price = price;
        this.isPurchased = isPurchased;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
