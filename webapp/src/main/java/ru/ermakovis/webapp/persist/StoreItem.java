package ru.ermakovis.webapp.persist;

public class StoreItem {
    private int id;
    private String name;
    private String image;
    private int amount;

    public StoreItem(int id, String name, String image, int amount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", amount=" + amount +
                '}';
    }
}
