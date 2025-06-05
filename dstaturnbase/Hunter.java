package com.bosakon.dstaturnbase;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Hunter {
    private String name;
    private int rank; // for future use, not used in comparison
    private String rankLetter; // "E", "D", "C", "A", "S"
    private int[] stats;
    private HashMap<String, Integer> inventory;
    private String weapon;
    private int maxHp;
    private int hp;
    private List<Soldier> soldiers;

    public Hunter(String name) {
        this.name = name;
        this.rank = 0;
        this.rankLetter = "E"; // Start at rank E
        this.stats = new int[]{5, 5, 5, 5, 5};
        this.inventory = new HashMap<>();
        this.weapon = "";
        this.maxHp = 30;
        this.hp = maxHp;
        this.soldiers = new ArrayList<Soldier>();
    }

    public String getName() { return name; }
    public int getRank() { return rank; }
    public String getRankLetter() { return rankLetter; }
    public void setRankLetter(String rl) { this.rankLetter = rl; }
    public int[] getStats() { return stats; }
    public HashMap<String, Integer> getInventory() { return inventory; }
    public String getWeapon() { return weapon; }
    public void setWeapon(String weapon) { this.weapon = weapon; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public void setHp(int hp) { this.hp = Math.max(0, Math.min(maxHp, hp)); }
    public void heal(int amount) { setHp(this.hp + amount); }
    public List<Soldier> getSoldiers() { return soldiers; }

    public void addSoldier(Soldier s) { this.soldiers.add(s); }
    public void addItem(String item, int amount) {
        inventory.put(item, inventory.getOrDefault(item, 0) + amount);
    }

    public void showInventory() {
        System.out.println(AnsiColors.BLUE + "                      >>Inventory<<" + AnsiColors.RESET);
        int i = 1;
        for (String item : inventory.keySet()) {
            System.out.printf(AnsiColors.WHITE + "%d. %s x%d\n" + AnsiColors.RESET, i++, item, inventory.get(item));
        }
        System.out.println(AnsiColors.WHITE + "HP: " + hp + "/" + maxHp + AnsiColors.RESET);
    }

    public void showSoldiers() {
        System.out.println(AnsiColors.CYAN + ">> Your Soldiers <<" + AnsiColors.RESET);
        if (soldiers.isEmpty()) {
            System.out.println("  (None recruited yet)");
        } else {
            int i = 1;
            for (Soldier s : soldiers) {
                System.out.print(i++ + ". ");
                s.showStatus();
            }
        }
    }
}