package com.bosakon.dstaturnbase;

import java.util.Scanner;

public class CombatSystem {
    public static boolean start(Hunter hunter, Monster monster, Scanner scanner) {
        System.out.println(AnsiColors.RED + "A wild " + monster.getName() + " appears!" + AnsiColors.RESET);

        boolean soldierUsed = false;

        while (monster.getHp() > 0 && hunter.getHp() > 0) {
            System.out.println(AnsiColors.WHITE + "Your HP: " + hunter.getHp() + "/" + hunter.getMaxHp() + " | " +
                    monster.getName() + " HP: " + monster.getHp() + AnsiColors.RESET);
            System.out.println(AnsiColors.YELLOW + "Choose your action:" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "1. Attack" + AnsiColors.RESET);
            System.out.println(AnsiColors.CYAN + "2. Use Potion" + AnsiColors.RESET);
            if (!soldierUsed && !hunter.getSoldiers().isEmpty()) {
                System.out.println(AnsiColors.CYAN + "3. Summon Soldier" + AnsiColors.RESET);
            }
            System.out.println(AnsiColors.CYAN + "0. Run" + AnsiColors.RESET);
            System.out.print(AnsiColors.GREEN + "Enter your choice: " + AnsiColors.RESET);
            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println(AnsiColors.PURPLE + "You ran away!" + AnsiColors.RESET);
                return false;
            } else if (choice.equals("2")) {
                if (hunter.getInventory().getOrDefault("Potion", 0) > 0 && hunter.getHp() < hunter.getMaxHp()) {
                    hunter.addItem("Potion", -1);
                    hunter.heal(15);
                    System.out.println(AnsiColors.GREEN + "You used a Potion and healed 15 HP!" + AnsiColors.RESET);
                    continue;
                } else {
                    System.out.println(AnsiColors.RED + "No potions available or already at full HP!" + AnsiColors.RESET);
                    continue;
                }
            } else if (choice.equals("3") && !soldierUsed && !hunter.getSoldiers().isEmpty()) {
                hunter.showSoldiers();
                System.out.print(AnsiColors.GREEN + "Choose a soldier by number (or 0 to cancel): " + AnsiColors.RESET);
                try {
                    int idx = Integer.parseInt(scanner.nextLine().trim());
                    if (idx > 0 && idx <= hunter.getSoldiers().size()) {
                        Soldier s = hunter.getSoldiers().get(idx - 1);
                        System.out.println(AnsiColors.YELLOW + "You summon " + s.getName() + "!" + AnsiColors.RESET);
                        int sDmg = Math.max(0, s.getAttack() - monster.getDefense());
                        monster.setHp(monster.getHp() - sDmg);
                        System.out.println(AnsiColors.GREEN + s.getName() + " hits the " + monster.getName() + " for " + sDmg + " damage!" + AnsiColors.RESET);
                        System.out.println(AnsiColors.PURPLE + s.getName() + " uses " + s.getSpecialSkill() + "!" + AnsiColors.RESET);
                        soldierUsed = true;
                        if (monster.getHp() <= 0) {
                            System.out.println(AnsiColors.GREEN + "You and your soldier defeated the " + monster.getName() + "!" + AnsiColors.RESET);
                            return true;
                        }
                        int monsterDamage = Math.max(0, monster.getAttack() - s.getDefense());
                        System.out.println(AnsiColors.RED + "The " + monster.getName() + " attacks your soldier for " + monsterDamage + " damage!" + AnsiColors.RESET);
                        s.setHp(s.getHp() - monsterDamage);
                        if (s.getHp() <= 0) {
                            System.out.println(AnsiColors.RED + s.getName() + " has fallen for this battle!" + AnsiColors.RESET);
                        }
                        continue;
                    } else if (idx == 0) {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(AnsiColors.RED + "Invalid input." + AnsiColors.RESET);
                    continue;
                }
            }

            int playerAttack = 6;
            int damage = Math.max(0, playerAttack - monster.getDefense());
            monster.setHp(monster.getHp() - damage);
            System.out.println(AnsiColors.GREEN + "You hit the " + monster.getName() + " for " + damage + " damage!" + AnsiColors.RESET);

            if (monster.getHp() <= 0) {
                System.out.println(AnsiColors.GREEN + "You defeated the " + monster.getName() + "!" + AnsiColors.RESET);
                return true;
            }

            int monsterDamage = Math.max(0, monster.getAttack() - 2);
            hunter.setHp(hunter.getHp() - monsterDamage);
            System.out.println(AnsiColors.RED + "The " + monster.getName() + " hits you for " + monsterDamage + " damage!" + AnsiColors.RESET);

            if (hunter.getHp() <= 0) {
                System.out.println(AnsiColors.RED + "You have been defeated..." + AnsiColors.RESET);
                return false;
            }
            System.out.println();
        }
        return hunter.getHp() > 0;
    }
}