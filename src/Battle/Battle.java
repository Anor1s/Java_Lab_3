package Battle;

import Droids.Droid;

public class Battle {
    public static String oneOnOneFight(Droid droid1, Droid droid2) {
        System.out.println("\n\n\n\n\nГРА ПОЧАЛАСЬ!");
        int round = 1;

        while (droid1.isAlive() && droid2.isAlive()) {
            printRoundHeader(droid1, droid2, round);

            if (takeTurn(droid1, droid2, round)) return formatResult(droid1, droid2, round);

            if (takeTurn(droid2, droid1, round)) return formatResult(droid1, droid2, round);

            round++;
        }

        return "";
    }

    private static void printRoundHeader(Droid d1, Droid d2, int round) {
        System.out.println("\n----------------------(" + String.format("%.2f", d1.getHealth()) + ")----------------------|Round #" + round +
                "|----------------------(" + String.format("%.2f", d2.getHealth()) + ")----------------------");
    }

    private static boolean takeTurn(Droid attacker, Droid defender, int round) {
        System.out.println("Хід дроїда: " + attacker.getName() + " --> " + defender.getName());
        attacker.attack(defender);
        if (!defender.isAlive()) {
            System.out.println("------------------------------------------------------------------------------------------------------------");
            return true;
        }
        return false;
    }

    private static String formatResult(Droid d1, Droid d2, int round) {
        Droid winner = d1.isAlive() ? d1 : d2;
        Droid loser = d1.isAlive() ? d2 : d1;
        return "Гра між " + d1.getName() + " (" + d1.getType() + ") і " +
                d2.getName() + " (" + d2.getType() + ") закінчилася на " + round + " раунді!\nПереміг - " +
                winner.getName() + " (" + winner.getType() + ") " + String.format("%.2f", winner.getHealth()) + " здоров'я залишилось!";
    }
}
