package com.bosakon.dstaturnbase;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class GameStart {
    static Scanner scanner = new Scanner(System.in);
    static SaveSystem saveSystem = new SaveSystem();

    public static void main(String[] args) {
        System.out.println(AnsiColors.CYAN +
            "                =================================");
        System.out.println("                              404                ");
        System.out.println("                =================================" + AnsiColors.RESET);

        System.out.println();
        System.out.println(AnsiColors.WHITE +
            "In this world, every choice leads you closer to your destiny…" + AnsiColors.RESET);
        System.out.println();

        boolean loaded = false;
        Hunter hunter = null;
        String hunterName = "";
        String chosenWeapon = "";
        while (true) {
            System.out.print(AnsiColors.YELLOW + "Load previous game? (yes/no): " + AnsiColors.RESET);
            String loadChoice = scanner.nextLine().trim().toLowerCase();
            if (loadChoice.equals("yes")) {
                loaded = saveSystem.loadGame();
                if (loaded) {
                    hunterName = saveSystem.getSavedHunterName();
                    chosenWeapon = saveSystem.getSavedWeapon();
                    hunter = new Hunter(hunterName);
                    hunter.setWeapon(chosenWeapon);
                    System.out.println(AnsiColors.GREEN + "Game loaded successfully!" + AnsiColors.RESET);
                    break;
                } else {
                    System.out.println(AnsiColors.RED + "No previous save found. Starting a new game." + AnsiColors.RESET);
                }
            }
            if (loadChoice.equals("no")) {
                break;
            }
            System.out.println(AnsiColors.RED + "Please enter 'yes' or 'no'." + AnsiColors.RESET);
        }

        if (!loaded) {
            System.out.println();
            System.out.print(AnsiColors.PURPLE + "Enter your name: " + AnsiColors.RESET);
            hunterName = scanner.nextLine().trim();
            hunter = new Hunter(hunterName);
            System.out.println();
        }

        hunter.addItem("Tirungan", 1);
        hunter.addItem("Lapis", 1);
        hunter.addItem("Flat Screw", 1);

        while (hunter.getWeapon().isEmpty()) {
            System.out.println(AnsiColors.BRIGHT_BLACK + "===================================================================" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "Choose your weapon:" + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "1. Check Inventory" + AnsiColors.RESET);
            System.out.println(AnsiColors.RED + "0. Exit program" + AnsiColors.RESET);
            System.out.print(AnsiColors.GREEN + "Enter 1 or 0: " + AnsiColors.RESET);
            String weaponChoice = scanner.nextLine().trim();

            if (weaponChoice.equals("0")) {
                System.out.println(AnsiColors.PURPLE + "Exiting program. See you next time, " + hunter.getName() + "!" + AnsiColors.RESET);
                return;
            } else if (weaponChoice.equals("1")) {
                hunter.showInventory();
                System.out.println();
                System.out.print(AnsiColors.GREEN + "Select a weapon by number (or 0 to go back): " + AnsiColors.RESET);
                String itemChoice = scanner.nextLine().trim();
                try {
                    int idx = Integer.parseInt(itemChoice);
                    if (idx == 0) continue;
                    List<String> keys = new ArrayList<String>(hunter.getInventory().keySet());
                    if (idx > 0 && idx <= keys.size()) {
                        chosenWeapon = keys.get(idx - 1);
                        hunter.setWeapon(chosenWeapon);
                        System.out.println(AnsiColors.GREEN + "You have chosen: " + chosenWeapon + AnsiColors.RESET);
                        saveSystem.saveGame(hunter.getName(), chosenWeapon);
                    } else {
                        System.out.println(AnsiColors.RED + "Invalid selection." + AnsiColors.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(AnsiColors.RED + "Please enter a valid number." + AnsiColors.RESET);
                }
            } else {
                System.out.println(AnsiColors.RED + "Invalid choice." + AnsiColors.RESET);
            }
        }

        while (true) {
            Dungeon chosenDungeon = DungeonSelector.chooseDungeon(scanner, hunter); // pass hunter
            DungeonSession session = new DungeonSession(chosenDungeon, hunter, scanner);
            session.start();
            System.out.println(AnsiColors.YELLOW + "Returning to town..." + AnsiColors.RESET);
        }
    }
}