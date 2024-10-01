package Droids;

public class DroidCurser extends Droid {
    private double curseChance;
    private boolean isCursed;
    private int curseRoundsLeft;
    private double curseDamage;

    public DroidCurser(String type, String name, double health, double damage, double criticalChance, double missChance, double curseChance, double missCurseChance) {
        super(type, name, health, damage, criticalChance, missChance);
        this.curseChance = curseChance;// Прокляття триває 5 раунди
        this.isCursed = false;
        this.curseRoundsLeft = 0;
        this.curseDamage = 15;
    }

    @Override
    public void specificAttack(Droid enemy) {

        if (isCursed) {
            curseRoundsLeft--;
            if (curseRoundsLeft > 0) {
                System.out.println("(SKILL) \t " + name +" (" + type + ") наносить " + String.format("%.2f", curseDamage) + " шкоди " + enemy.getName()
                        + " (" + enemy.getType() + ") від прокляття");
                System.out.println("(SKILL) \tНа " + enemy.getName() + " (" + enemy.getType() + ") діє прокляття ще " + curseRoundsLeft + " раунда(ів)");
                enemy.takeDamage(damage + curseDamage);
                return;
            } else if (curseRoundsLeft < 1) {
                isCursed = false;
                enemy.setCriticalChance(criticalChance);
                enemy.setMissChance(missChance);
                System.out.println("(SKILL) \t" + enemy.getName() + " (" + enemy.getType() + ") більше не під прокляттям.");
            }
        }

        if (!isCursed && Math.random() < curseChance) {
            isCursed = true;
            curseRoundsLeft = 3;
            System.out.println("(SKILL) \t" + name +" (" + type + ") накладає прокляття на " + enemy.getName() + " (" + enemy.getType()
                    + "), яке наносить " + String.format("%.2f", curseDamage) + " шкоди за раунд, " +
                    "\n\t\t\tа також зменшує його шанс критичного удару до нуля, "
                    + "на " + curseRoundsLeft + " раунда(ів)!");
            enemy.setCriticalChance(0);
            enemy.takeDamage(damage + curseDamage);
            return;
        }

        enemy.takeDamage(damage);
    }

    // Інформація про дроїда
    @Override
    public String toString() {
        return super.toString() + " | Curse Chance: " + String.format("%.2f",curseChance * 100) + "%";
    }
}
