package com.bosakon.dstaturnbase;


public class SaveSystem {
    // Placeholder for actual save/load logic
    private String savedHunterName = null;
    private String savedWeapon = null;

    public boolean loadGame() {
        // TODO: Implement actual file reading and deserialization
        return savedHunterName != null;
    }

    public void saveGame(String name, String weapon) {
        // TODO: Implement actual file writing and serialization
        this.savedHunterName = name;
        this.savedWeapon = weapon;
    }

    public String getSavedHunterName() {
        return savedHunterName;
    }

    public String getSavedWeapon() {
        return savedWeapon;
    }
}