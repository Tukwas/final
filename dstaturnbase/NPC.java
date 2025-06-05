package com.bosakon.dstaturnbase;

import java.util.HashMap;
import java.util.Random;

public class NPC {
    public String name;
    public HashMap<String, Integer> inventory;
    private static final String[] NAME_POOL = {
        "Ana", "Kai", "Lira", "Milo", "Vex", "Sora", "Ray", "Mira", "Rin", "Kade", "Eve", "Taro",
        "Jin", "Nova", "Lex", "Sage", "Yuri", "Ember"
    };

    public NPC() {
        this.name = NAME_POOL[new Random().nextInt(NAME_POOL.length)];
        this.inventory = new HashMap<String, Integer>();
    }
}