package com.bosakon.dstaturnbase;

import java.util.Random;

public class Monster {
    private String name;
    private int hp;
    private int attack;
    private int defense;

    public Monster(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public static Monster getRandomMonster() {
        Monster[] monsters = {
            new Monster("Goblin", 15, 4, 1),
            new Monster("Cave Bat", 10, 3, 0),
            new Monster("Slime", 20, 2, 2),
            new Monster("Skeleton", 18, 5, 2)
        };
        Random rand = new Random();
        return monsters[rand.nextInt(monsters.length)];
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
}
