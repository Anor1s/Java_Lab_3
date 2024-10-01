package Battle;

import Droids.Droid;
import java.util.List;

public class TeamBattle {
    public static String teamFight(List<Droid> team1, List<Droid> team2) {
        StringBuilder result = new StringBuilder();

        // Показати команди
        showTeams(result, "Команда 1", team1);
        showTeams(result, "Команда 2", team2);

        // Перший і другий бій
        Droid remainingDroid1 = fightAndAppendResult(result, "Перша битва", team1.get(0), team2.get(0));
        Droid remainingDroid2 = fightAndAppendResult(result, "Друга битва", team1.get(1), team2.get(1));

        // Фінальний бій, якщо необхідно
        if (remainingDroid1 != null && remainingDroid2 != null) {
            remainingDroid1.setHealth(500);
            remainingDroid2.setHealth(500);
            fightAndAppendResult(result, "Фінальний бій", remainingDroid1, remainingDroid2);
        }

        // Оголошення переможців
        result.append("\nПеремогла команда ").append(remainingDroid1 != null ? "1" : "2").append(":\n");
        List<Droid> winningTeam = remainingDroid1 != null ? team1 : team2;
        showTeams(result, null, winningTeam);

        return result.toString();
    }

    private static void showTeams(StringBuilder result, String teamName, List<Droid> team) {
        if (teamName != null) result.append("\n").append(teamName).append(":\n");
        for (Droid droid : team) {
            result.append(droid.getName()).append(" (").append(droid.getType()).append(")\n");
        }
    }

    private static Droid fightAndAppendResult(StringBuilder result, String fightName, Droid droid1, Droid droid2) {
        result.append("\n").append(fightName).append(": ").append(droid1.getName()).append(" vs ").append(droid2.getName()).append("\n");
        String fightResult = Battle.oneOnOneFight(droid1, droid2);
        result.append(fightResult).append("\n");
        return droid1.isAlive() ? droid1 : (droid2.isAlive() ? droid2 : null);
    }
}
