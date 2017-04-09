package com.riotgames;

public class Champion {
    private String championName;
    private int price;

    public Champion (String championName, int price) {
        this.championName = championName;
        this.price = price;
    }

    public String getChampionName() {
        return championName;
    }

    public int getPrice() {
        return price;
    }
}
