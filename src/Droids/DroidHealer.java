package Droids;

public class DroidHealer extends Droid {
    private double healAmount;
    private double lifestealPercentage;
    private double healing;
    private boolean isHeal;

    public DroidHealer(String type, String name, double health, double damage, double criticalChance, double missChance, double healAmount, double lifestealPercentage) {
        super(type, name, health, damage, criticalChance, missChance);
        this.healAmount = healAmount;
        this.lifestealPercentage = lifestealPercentage;
        this.healing = getHealth() * 0.2;;
        this.isHeal = false;
    }

    @Override
    public void specificAttack(Droid enemy) {

        enemy.setHealth(enemy.getHealth() - lifestealPercentage * enemy.getHealth());

        if (isCriticalHit()) {
            System.out.println("\t" + type + " краде +" + String.format("%.2f", lifestealPercentage * enemy.getHealth()) + " здоворв'я у " + enemy.getType());
            enemy.setHealth(enemy.getHealth() - (enemy.getHealth() * lifestealPercentage));
            enemy.takeDamage(damage + lifestealPercentage * enemy.getHealth());
        } else {
            System.out.println("\t" + type + " краде +" + String.format("%.2f", lifestealPercentage * enemy.getHealth()) + " здоворв'я у " + enemy.getType());
            enemy.setHealth(enemy.getHealth() - (enemy.getHealth() * lifestealPercentage));
            enemy.takeDamage(damage + lifestealPercentage * enemy.getHealth());
        }

        heal();
    }

    // Метод для зцілення іншого дроїда
    public void heal() {
        if (health <= healing && health > 0 && !isHeal) {
            System.out.println("(SKILL) \t" + type + " вилікуває себе на " + healAmount + " здоров'я!");
            health += healAmount;
            isHeal = true;
        }
    }

    // Інформація про дроїда
    @Override
    public String toString() {
        return super.toString() + " | Heal Amount: " + healAmount;
    }
}
