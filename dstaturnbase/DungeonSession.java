package com.bosakon.dstaturnbase;

import java.util.Random;
import java.util.Scanner;

public class DungeonSession {
    private Dungeon dungeon;
    private Hunter hunter;
    private Scanner scanner;

    public DungeonSession(Dungeon dungeon, Hunter hunter, Scanner scanner) {
        this.dungeon = dungeon;
        this.hunter = hunter;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println(AnsiColors.BRIGHT_BLACK + "===================================================================" + AnsiColors.RESET);
        System.out.println(AnsiColors.BLUE + "You enter the " + dungeon.getName() + "..." + AnsiColors.RESET);
        System.out.println(AnsiColors.BRIGHT_BLACK + dungeon.getDescription() + AnsiColors.RESET);

        boolean exit = false;
        Random rand = new Random();

        while (!exit) {
            if (hunter.getHp() <= 0) {
                System.out.println(AnsiColors.RED + "You cannot continue with 0 HP. Returning to town..." + AnsiColors.RESET);
                hunter.setHp(hunter.getMaxHp());
                break;
            }
            System.out.println();
            System.out.println(AnsiColors.YELLOW + "What do you want to do?" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "1. Explore" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "2. Check Inventory" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "3. Check Soldiers" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "4. Recruit Soldier" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "5. Exit Dungeon" + AnsiColors.RESET);
            System.out.print(AnsiColors.GREEN + "Enter your choice: " + AnsiColors.RESET);

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    int event = rand.nextInt(3);
                    if (event == 0) {
                        Monster monster = Monster.getRandomMonster();
                        boolean survived = CombatSystem.start(hunter, monster, scanner);
                        if (!survived) {
                            System.out.println(AnsiColors.PURPLE + "You are rescued and returned to town." + AnsiColors.RESET);
                            exit = true;
                        }
                    } else if (event == 1) {
                        System.out.println(AnsiColors.GREEN + "You find a treasure chest! You gain 10 gold and a Potion." + AnsiColors.RESET);
                        hunter.addItem("Gold", 10);
                        hunter.addItem("Potion", 1);
                    } else {
                        System.out.println(AnsiColors.BRIGHT_BLACK + "You wander empty halls..." + AnsiColors.RESET);
                    }
                    break;
                case "2":
                    hunter.showInventory();
                    break;
                case "3":
                    hunter.showSoldiers();
                    break;
                case "4":
                    Soldier recruit = Soldier.randomRecruit();
                    System.out.println(AnsiColors.YELLOW + "You encounter a potential ally:" + AnsiColors.RESET);
                    recruit.showStatus();
                    System.out.print(AnsiColors.GREEN + "Recruit this soldier? (yes/no): " + AnsiColors.RESET);
                    String res = scanner.nextLine().trim().toLowerCase();
                    if (res.equals("yes")) {
                        hunter.addSoldier(recruit);
                        System.out.println(AnsiColors.GREEN + recruit.getName() + " has joined your team!" + AnsiColors.RESET);
                    } else {
                        System.out.println(AnsiColors.BRIGHT_BLACK + recruit.getName() + " departs on their own path." + AnsiColors.RESET);
                    }
                    break;
                case "5":
                    System.out.println(AnsiColors.PURPLE + "You leave the dungeon safely." + AnsiColors.RESET);
                    exit = true;
                    break;
                default:
                    System.out.println(AnsiColors.RED + "Invalid choice." + AnsiColors.RESET);
            }
        }
    }
}