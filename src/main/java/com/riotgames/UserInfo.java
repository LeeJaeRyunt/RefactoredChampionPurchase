package com.riotgames;

import java.util.HashMap;

public class UserInfo {
    String accountId;
    int ownedRp;
    HashMap<String, Champion> ownedChampions = new HashMap<>();

    public boolean hasChampion(String championName){
        return ownedChampions.containsKey(championName);
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setRp(int rp) {
        this.ownedRp = rp;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getRp() {
        return ownedRp;
    }

    public void addChampion(String champName, Champion champion){
        ownedChampions.put(champName, champion);
    }

    public void setOwnedRp(int ownedRp) {
        this.ownedRp = ownedRp;
    }
}
