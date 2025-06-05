package com.bosakon.dstaturnbase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dungeon {
    private static final Map<String, String[]> NAME_POOLS = new HashMap<String, String[]>();
    static {
        NAME_POOLS.put("E", new String[]{"Buhangin Milan", "Tigatto", "Bajada SPMC", "Cabagiuo"});
        NAME_POOLS.put("D", new String[]{"Mati", "Apokon", "Panabo", "Carmen", "Mawab"});
        NAME_POOLS.put("C", new String[]{"Lasang", "Sirawan", "Bunawan", "Donia Pilar", "LandMark"});
        NAME_POOLS.put("A", new String[]{"Lanang", "Sasa", "Magsaysay", "Roxas", "Uyangureen"});
        NAME_POOLS.put("S", new String[]{"Boulevard", "Matina Aplaya", "San pedro", "Ubos Bangkerohan"});
    }

    private String name;
    private String rank;
    private String description;

    public static String randomNameForRank(String rank) {
        String[] pool = NAME_POOLS.getOrDefault(rank, new String[]{"Unknown"});
        return pool[new Random().nextInt(pool.length)];
    }

    public Dungeon(String name, String rank, String description) {
        this.name = name;
        this.rank = rank;
        this.description = description;
    }

    public static Dungeon createWithRandomName(String rank, String description) {
        return new Dungeon(randomNameForRank(rank), rank, description);
    }

    public String getName() { return name; }
    public String getRank() { return rank; }
    public String getDescription() { return description; }

    public void displayInfo() {
        System.out.println(AnsiColors.CYAN + name + AnsiColors.RESET +
                " [" + AnsiColors.PURPLE + "Rank " + rank + AnsiColors.RESET + "]");
        System.out.println(AnsiColors.BRIGHT_BLACK + description + AnsiColors.RESET);
    }
}
