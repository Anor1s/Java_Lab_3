package Battle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BattleRecorder {

    public static void recordBattle(String filename, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(result);
            System.out.println("Бій успішно записано у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Не вдалося записати бій до файлу: " + filename);
        }
    }
}