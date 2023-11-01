package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите путь к файлу tests.json:");
            String filePath = scanner.nextLine();
            JSONObject testsJson = new JSONObject(new JSONTokener(new FileReader(filePath)));
            System.out.print("Введите путь к файлу values.json:");
            filePath = scanner.nextLine();
            JSONObject valuesJson = new JSONObject(new JSONTokener(new FileReader(filePath)));
            System.out.print("Введите путь к файлу report.json:");
            filePath = scanner.nextLine();

            if (valuesJson.has("values")) {
                JSONArray valuesArray = valuesJson.getJSONArray("values");

                for (int i = 0; i < valuesArray.length(); i++) {
                    JSONObject valueObj = valuesArray.getJSONObject(i);
                    int id = valueObj.getInt("id");
                    JSONArray testsArray = testsJson.getJSONArray("tests");
                    for (int j = 0; j < testsArray.length(); j++) {
                        JSONObject testObj = testsArray.getJSONObject(j);
                        if (testObj.getInt("id") == id) {
                            testObj.put("value", valueObj.getString("value"));
                            break;
                        }
                    }
                }

                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(testsJson.toString());
                fileWriter.close();

                System.out.println("Готово.");
            } else {
                System.out.println("Ключ не найден.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}