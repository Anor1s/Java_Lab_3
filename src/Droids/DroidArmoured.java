package Droids;

public class DroidArmoured extends Droid {
    private double armourePercentage;
    private double spikedArmoure;
    private boolean flag = false;  // Прапорець для контролю збільшення шкоди
    private double originalEnemyDamage;    // Збереження початкової шкоди ворога
    private double maxHealth;
    private double minHealth;

    public DroidArmoured(String type, String name, double health, double damage, double criticalChance, double missChance, double armourePercentage, double spikedArmoure) {
        super(type, name, health, damage, criticalChance, missChance);
        this.armourePercentage = armourePercentage;
        this.spikedArmoure = spikedArmoure;
        this.maxHealth = getHealth();
        this.minHealth = getHealth() * 0.3;
    }

    @Override
    public void specificAttack(Droid enemy) {
        if (isArmour()) {
            System.out.println("\t" + type + " відражає " + (enemy.getDamage() * spikedArmoure) + " шкоди по " + enemy.getType() + "!");
            enemy.takeDamage(damage + enemy.getDamage() * spikedArmoure);
            return;
        }

        if (isArmour() && !flag) {
            System.out.println("(SKILL) \t" + type + " активує броню, і отримує на " + armourePercentage * 100 +
                    "% менше шкоди, а також відражає " + spikedArmoure * 100 + "% шкоди! #########");
            originalEnemyDamage = enemy.getDamage(); // Збереження початкової шкоди
            enemy.setDamage(originalEnemyDamage * (1 - armourePercentage)); // Зменшуємо шкоду
            flag = true;
        } else if (!isArmour() && flag) {
            System.out.println("(SKILL) \tБроня " + type + " зламана!");
            enemy.setDamage(originalEnemyDamage); // Відновлюємо початкову шкоду
            flag = false;
        }

        enemy.takeDamage(damage);
    }

    boolean isArmour() {
        return health <= maxHealth && health >= minHealth;
    }

    // Інформація про дроїда
    @Override
    public String toString() {
        return super.toString() + " | Armour percentage: " + String.format("%.2f", armourePercentage * 100) + "%"
                + ", Spiked armoure percentage: " + String.format("%.2f", spikedArmoure * 100) + "%";
    }
}

