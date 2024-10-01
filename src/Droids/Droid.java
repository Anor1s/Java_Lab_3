package Droids;

public abstract class Droid {
    protected String type;
    protected String name;
    protected double health;
    protected double damage;
    protected double criticalChance;
    protected double missChance;

    public Droid(String type, String name, double health, double damage, double criticalChance, double missChance) {
        this.type = type;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.criticalChance = criticalChance;
        this.missChance = missChance;
    }

    // Абстракний метод для атаки
    public abstract void specificAttack(Droid enemy);

    // Атака
    public void attack(Droid enemy) {

        if (isMissedHit() && isCriticalHit()) {
            System.out.println(type + " промахується КРИТИЧНИМ ударом по " + enemy.getType() + "!");
            return;
        } else if (isMissedHit()) {
            System.out.println(type + " промахнувся ударом по " + enemy.getType() + "!");
            return;
        }

        if (isCriticalHit()) {
            System.out.println(type + " наносить КРИТИЧНИЙ удар по " + enemy.getType() + " на " + String.format("%.2f", damage * 2) + " шкоди!");
        } else {
            System.out.println(type + " атакує " + enemy.getType() + " і завдає " + String.format("%.2f", damage) + " шкоди");
        }

        specificAttack(enemy);
    }

    public double setCriticalChance(double chance) {
        return this.criticalChance = chance;
    }

    public double setMissChance(double chance) {
        return this.missChance = chance;
    }

    protected boolean isCriticalHit() {
        return Math.random() < criticalChance;
    }

    protected boolean isMissedHit() {
        return Math.random() < missChance;
    }

    public void takeDamage(double damage) {
        health -= damage;
        if (health <= 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double setHealth(double health) {
        return this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public double setDamage(double damage) {
        return this.damage = damage;
    }

    public double getMissChance() {
        return missChance;
    }

    public String getType() {
        return type;
    }

    // Інформація про дроїда
    @Override
    public String toString() {
        return "Type: (" + type + "), Droid: " + name + ", Health: " +  String.format("%.2f", health) + ", Damage: "
                + String.format("%.2f", damage) + ", Critical Chance: " + String.format("%.2f", criticalChance * 100) + "%" + ", Miss Chance: "
                +  String.format("%.2f", missChance * 100) + "%";
    }
}