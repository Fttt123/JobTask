package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        try {
            JSONObject testsJson = new JSONObject(new JSONTokener(new FileReader("tests.json")));

            JSONObject valuesJson = new JSONObject(new JSONTokener(new FileReader("values.json")));

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

                FileWriter fileWriter = new FileWriter("report.json");
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