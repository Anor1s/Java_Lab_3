package Droids;

public class DroidHider extends Droid {
    private double hideChance;
    private boolean isHiding;
    private int roundsToHide;
    private double criticalBuff;
    private double missSpellChance;

    public DroidHider(String type, String name, double health, double damage, double criticalChance,
                      double missChance, double hideChance, double criticalBuff, double missSpellChance) {
        super(type, name, health, damage, criticalChance, missChance);
        this.hideChance = hideChance;
        this.criticalBuff = criticalBuff;
        this.missSpellChance = missSpellChance;
        this.isHiding = false;
        this.roundsToHide = 0;
    }

    @Override
    public void specificAttack(Droid enemy) {

        if (isHiding) {
            roundsToHide--;
            if (roundsToHide > 0) {
                System.out.println("(SKILL) \t" + type + " в невидимості, ще " + roundsToHide + " раунд(ів)!");
            } else if (roundsToHide < 1) {
                // Виходимо з невидимості
                isHiding = false;
                criticalChance -= criticalBuff; // Повертаємо критичний шанс
                enemy.setMissChance(enemy.getMissChance() - (missSpellChance * 100));
                System.out.println("(SKILL) \t" + type + " виходить з невидимості!");
            }
        }

        if (!isHiding && Math.random() < hideChance) {
            isHiding = true;
            roundsToHide = 2; // невидимість на 2 раунди
            System.out.println("(SKILL) \t" + type + " приховується на " + roundsToHide + " раунда(ів), " +
                    "шанс на промах по ньому змінився на: " + missSpellChance + "!");
            criticalChance += criticalBuff; // Збільшуємо шанс на критичний удар
            enemy.setMissChance(missSpellChance * 100);
        }

        enemy.takeDamage(damage);
    }

    // Інформація про дроїда
    @Override
    public String toString() {
        return super.toString() + " | Hide chance: " + String.format("%.2f", hideChance * 100) + "%" + ", Enemy miss chance: " + String.format("%.2f", missSpellChance * 100) + "%";
    }
}
