package Main;

import Droids.Droid;
import Droids.DroidCurser;
import Droids.DroidHealer;
import Droids.DroidHider;
import Droids.DroidArmoured;

import Battle.Battle;
import Battle.TeamBattle;
import Battle.BattleRecorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static List<Droid> droidList = new ArrayList<>();
    static String resultBattle = "";

    public static void main(String[] args) {
        Menu();
    }

    private static void Menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Командний бій");
            System.out.println("5. Зберегти результати битви у файл");
            System.out.println("6. Відтворити бій з файлу");
            System.out.println("7. Вийти");
            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createDroid(scanner);
                    break;
                case 2:
                    showDroidList();
                    break;
                case 3:
                    resultBattle = oneOnOneBattle(scanner);
                    break;
                case 4:
                    resultBattle = teamBattle(scanner);
                    break;
                case 5:
                    saveBattleResults(scanner);
                    break;
                case 6:
                    replayBattleFromFile(scanner);
                    break;
                case 7:
                    System.out.println("Вихід...");
                    System.exit(0);
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void createDroid(Scanner scanner) {
        System.out.println("\nВиберіть тип дроїда: 1 - Curser, 2 - Healer, 3 - Hider, 4 - Armoured");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();

        switch (type) {
            case 1:
                droidList.add(new DroidCurser("CURSER", name, 1000, 40, 0.15, 0.1, 0.2, 0.15));
                break;
            case 2:
                droidList.add(new DroidHealer("HEALER", name, 600, 10, 0.1, 0.1, 300, 0.025));
                break;
            case 3:
                droidList.add(new DroidHider("HIDER", name, 300, 80, 0.15, 0.1, 0.15, 0.35, 0.7));
                break;
            case 4:
                droidList.add(new DroidArmoured("ARMOURED", name, 500, 50, 0.15, 0.15, 0.5, 0.75));
                break;
            default:
                System.out.println("Невірний вибір типу.");
        }
    }

    private static void showDroidList() {
        if (droidList.isEmpty()) {
            System.out.println("\nСписок дроїдів порожній.");
        } else {
            System.out.println("\nСписок дроїдів: ");
            for (int i = 0; i < droidList.size(); i++) {
                System.out.println((i + 1) + ". " + droidList.get(i));
            }
        }
    }

    private static String oneOnOneBattle(Scanner scanner) {
        showDroidList();
        if (droidList.size() < 2) {
            System.out.println("\nНедостатньо дроїдів для бою.");
            return "";
        }

        System.out.print("\nВиберіть дроїда 1: ");
        int droid1Index = scanner.nextInt() - 1;
        System.out.print("Виберіть дроїда 2: ");
        int droid2Index = scanner.nextInt() - 1;

        if (droid1Index < 0 || droid1Index >= droidList.size() || droid2Index < 0 || droid2Index >= droidList.size()) {
            System.out.println("Невірний вибір дроїда.");
            return "";
        }

        Droid droid1 = droidList.get(droid1Index);
        Droid droid2 = droidList.get(droid2Index);

        String result = Battle.oneOnOneFight(droid1, droid2);
        System.out.println("\n\t" + result);
        return result;
    }

    private static String teamBattle(Scanner scanner) {
        showDroidList();
        if (droidList.size() < 4) {
            System.out.println("Недостатньо дроїдів для командного бою.");
            return "";
        }

        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        // Формуємо команду 1
        System.out.println("\nФормуємо команду 1:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Виберіть дроїда для команди 1: ");
            int droidIndex = scanner.nextInt() - 1;

            if (droidIndex < 0 || droidIndex >= droidList.size()) {
                System.out.println("Невірний вибір дроїда.");
                return "";
            }

            team1.add(droidList.get(droidIndex));
        }

        // Формуємо команду 2
        System.out.println("\nФормуємо команду 2:");
        for (int i = 0; i < 2; i++) {
            System.out.print("Виберіть дроїда для команди 2: ");
            int droidIndex = scanner.nextInt() - 1;

            if (droidIndex < 0 || droidIndex >= droidList.size()) {
                System.out.println("Невірний вибір дроїда.");
                return "";
            }

            team2.add(droidList.get(droidIndex));
        }

        // Викликаємо метод teamFight для проведення командного бою
        String result = TeamBattle.teamFight(team1, team2);
        System.out.println(result);
        return result;
    }

    private static void saveBattleResults(Scanner scanner) {
        System.out.print("\nВведіть ім'я файлу для збереження результатів бою: ");
        scanner.nextLine();
        String filename = scanner.nextLine();

        if (!resultBattle.isEmpty()) {
            BattleRecorder.recordBattle(filename, resultBattle);
        } else {
            System.out.println("Немає результатів для збереження.");
        }
    }

    private static void replayBattleFromFile(Scanner scanner) {
        System.out.print("\nВведіть ім'я файлу для відтворення бою: ");
        scanner.nextLine();
        String filename = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("\nРезультат бою з файлу:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Не вдалося прочитати файл: " + filename);
        }
    }
}
