package com.bosakon.dstaturnbase;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DungeonSelector {
    private static final String[] RANKS = {"E", "D", "C", "A", "S"};
    private static final String[] DESCRIPTIONS = {
        "A musty cave crawling with beginner-level monsters.",
        "An old mine, rumored to be haunted by restless spirits.",
        "A crumbling temple filled with traps and ancient secrets.",
        "A dungeon within a dungeon. Only the brave survive here.",
        "The most dangerous dungeon, home to legendary bosses."
    };

    private static int rankToValue(String rank) {
        switch(rank) {
            case "E": return 0;
            case "D": return 1;
            case "C": return 2;
            case "A": return 3;
            case "S": return 4;
            default: return -1;
        }
    }

    public static List<Dungeon> generateDungeons() {
        List<Dungeon> dungeons = new ArrayList<Dungeon>();
        for (int i = 0; i < RANKS.length; i++) {
            dungeons.add(Dungeon.createWithRandomName(RANKS[i], DESCRIPTIONS[i]));
        }
        return dungeons;
    }

    public static Dungeon chooseDungeon(Scanner scanner, Hunter hunter) {
        while(true) {
            List<Dungeon> dungeons = generateDungeons();
            System.out.println(AnsiColors.BRIGHT_BLACK + "===================================================================" + AnsiColors.RESET);
            System.out.println(AnsiColors.BLUE + "                      >>Choose Dungeon<<" + AnsiColors.RESET);
            int shown = 0;
            List<Dungeon> available = new ArrayList<Dungeon>();
            for (int i = 0; i < dungeons.size(); i++) {
                Dungeon d = dungeons.get(i);
                if (rankToValue(d.getRank()) <= rankToValue(hunter.getRankLetter())) {
                    shown++;
                    System.out.print(AnsiColors.YELLOW + (shown) + ". " + AnsiColors.RESET);
                    d.displayInfo();
                    available.add(d);
                } else {
                    System.out.print(AnsiColors.BRIGHT_BLACK + "-. " + AnsiColors.RESET);
                    System.out.print(AnsiColors.RED + d.getName() + " [LOCKED: Requires Rank " + d.getRank() + " or higher]" + AnsiColors.RESET + "\n");
                }
            }
            System.out.println(AnsiColors.RED + "0. Exit program" + AnsiColors.RESET);

            System.out.print(AnsiColors.GREEN + "Enter dungeon number: " + AnsiColors.RESET);
            String input = scanner.nextLine().trim();
            int idx;
            try {
                idx = Integer.parseInt(input);
                if (idx == 0) {
                    System.out.println(AnsiColors.PURPLE + "Exiting program. Goodbye adventurer!" + AnsiColors.RESET);
                    System.exit(0);
                }
                if (idx > 0 && idx <= available.size()) {
                    Dungeon chosen = available.get(idx - 1);
                    System.out.println(AnsiColors.GREEN + "You've chosen: " + chosen.getName() + AnsiColors.RESET);
                    return chosen;
                }
            } catch (NumberFormatException e) {
                // fall through
            }
            System.out.println(AnsiColors.RED + "Invalid choice or locked dungeon. Please try again." + AnsiColors.RESET);
        }
    }
}
