package com.example.shop.model;

public class Food {
    private  String name;
    private  int price=0;
    private  boolean  hot=false;
    private  boolean fish=false;
    private  boolean sour=false;

    public Food(String name, int price, boolean hot, boolean fish, boolean sour) {
        this.name = name;
        this.price = price;
        this.hot = hot;
        this.fish = fish;
        this.sour = sour;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHot() {
        return hot;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isSour() {
        return sour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public void setSour(boolean sour) {
        this.sour = sour;
    }
}
