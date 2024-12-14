package slotmachine;

import java.util.Scanner;
import java.util.Random;

public class SlotMachine {

    static void displayAsciiTown() {
        System.out.println("                                        .");
        System.out.println("              . .                     -:-             .  .  .");
        System.out.println("            .'.:,'.        .  .  .     ' .           . \\ | / .");
        System.out.println("            .'.;.`.       ._. ! ._.       \\          .__\\:/__.");
        System.out.println("             `,:.'         ._\\!/.                     .';`.      . ' .");
        System.out.println("             ,'             . ! .        ,.,      ..======..       .:.");
        System.out.println("            ,                 .         ._!_.     ||::: : | .        ',");
        System.out.println("     .====.,                  .           ;  .~.===: : : :|   ..===.");
        System.out.println("     |.::'||      .=====.,    ..=======.~,   |\"|: :|::::::|   ||:::|=====|");
        System.out.println("  ___| :::|!__.,  |:::::|!_,   |: :: ::|\"|l_l|\"|:: |:;;:::|___!| ::|: : :|");
        System.out.println(" |: :|::: |:: |!__|; :: |: |===::: :: :|\"||_||\"| : |: :: :|: : |:: |:::::|");
        System.out.println(" |:::| _::|: :|:::|:===:|::|:::|:===F=:|\"!/|\\!\"|::F|:====:|::_:|: :|::__:|");
        System.out.println(" !_[]![_]_!_[]![]_!_[__]![]![_]![_][I_]!//_:_\\\\![]I![_][_]!_[_]![]_!_[__]!");
        System.out.println(" -----------------------------------\"---''''```---\"-----------------------");
        System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |= _ _:_ _ =| _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.println("                                     |=    :    =|                        ");
        System.out.println("_____________________________________L___________J________________________");
    }

    static double betHandling(Scanner sc) {
        double bet;
        while (true) {
            System.out.print("Bet: $");
            if (!sc.hasNextDouble()) {
                sc.nextLine();
                System.out.println("Invalid input. Please enter a valid number.");
            } else {
                bet = sc.nextDouble();
                if (bet <= 0) {
                    System.out.println("Bet must be greater than 0. Please try again.");
                } else {
                    return bet;
                }
            }
        }
    }

    static boolean getUserConfirmation(Scanner sc) {
        while (true) {
            System.out.print("\nDo you want to continue in Java Slot Machine (y/n): ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                System.out.println("Terminating...");
                System.exit(0);
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    static int betCondition(double balance, double bet) {
        if (bet == 0) {
            System.out.println("Withdrawing $" + balance);
            System.out.println("Thank you for playing!");
            return -1;
        } else if (bet < 0) {
            System.out.println("Bet must be above 0!");
            return 0;
        } else if (bet > balance) {
            System.out.println("Insufficient funds. Please enter a smaller amount.");
            return 0;
        }
        return 1;
    }

    static double runSpinAndCheck(String[] symbols, double balance, double bet) {
        Random rand = new Random();
        String[] results = new String[3];
        for (int i = 0; i < results.length; i++) {
            results[i] = symbols[rand.nextInt(symbols.length)];
        }

        System.out.println("\n   |============================|");
        System.out.println("  ||==||||||||||||||||||||||||==|");

        if (results[0].equals(results[1]) && results[0].equals(results[2])) {
            System.out.println(" |||==****   YOU WIN!    ****==|");
            balance += (bet * 5);
        } else {
            System.out.println(" |||==****   YOU LOSE!   ****==|");
        }

        System.out.println("||||==*********************==||");
        System.out.print("||||==*  ");

        for (int i = 0; i < results.length; i++) {
            System.out.print(results[i]);
            if (i < results.length - 1) {
                System.out.print("  |  ");
            }
        }

        System.out.println("  *||");
        System.out.println("||||============================|");
        System.out.println("||||============================|");

        return balance;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] symbols = {"🍒", "🍀", "🏆"};

        double balance = 100;

        displayAsciiTown();
        getUserConfirmation(sc);

        while (true) {
            System.out.println("\nCurrent Balance: $" + balance + " (enter 0 to payout/exit)");

            double bet = betHandling(sc);
            int condition = betCondition(balance, bet);

            if (condition == -1) {
                break;
            } else if (condition == 0) {
                continue;
            }

            balance -= bet;
            balance = runSpinAndCheck(symbols, balance, bet);

            if (balance <= 0) {
                System.out.println("You are out of money! Game over.");
                break;
            }
        }
        sc.close();
    }
}