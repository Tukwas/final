package com.bosakon.dstaturnbase;

import java.util.Random;

public class Soldier {
    private String name;
    private String type;
    private String rank;
    private int maxHp;
    private int hp;
    private int attack;
    private int defense;
    private String specialSkill;

    private static final Soldier[] RECRUIT_POOL = {
        new Soldier("Gareth", "Warrior", "C", 22, 22, 8, 4, "Shield Bash"),
        new Soldier("Lira", "Mage", "B", 16, 16, 12, 2, "Fireball"),
        new Soldier("Fenrir", "Monster", "S", 30, 30, 14, 6, "Savage Howl"),
        new Soldier("Elara", "Healer", "A", 18, 18, 5, 3, "Blessing Light"),
        new Soldier("Whisper", "Rogue", "D", 15, 15, 7, 3, "Poison Strike"),
        new Soldier("Boulder", "Tank", "C", 28, 28, 5, 8, "Stonewall"),
        new Soldier("Aqua", "Spirit", "B", 17, 17, 6, 4, "Healing Rain")
    };

    public Soldier(String name, String type, String rank, int maxHp, int hp, int attack, int defense, String specialSkill) {
        this.name = name;
        this.type = type;
        this.rank = rank;
        this.maxHp = maxHp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialSkill = specialSkill;
    }

    public static Soldier randomRecruit() {
        Random rand = new Random();
        Soldier s = RECRUIT_POOL[rand.nextInt(RECRUIT_POOL.length)];
        return new Soldier(s.name, s.type, s.rank, s.maxHp, s.maxHp, s.attack, s.defense, s.specialSkill);
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getRank() { return rank; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = Math.max(0, Math.min(maxHp, hp)); }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public String getSpecialSkill() { return specialSkill; }
    public int getMaxHp() { return maxHp; }

    public void showStatus() {
        System.out.printf("%s(%s, %s) HP: %d/%d ATK: %d DEF: %d Skill: %s\n",
            name, type, rank, hp, maxHp, attack, defense, specialSkill);
    }
}
