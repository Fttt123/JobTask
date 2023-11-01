import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float centerX = 0, centerY = 0, radius = 0;
        List<Float> points = new ArrayList<>();;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберете файл с координатами центра и радиусом!\n");
        System.out.print("Введите путь к файлу:");
        String filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String[] centerCoordinates = line.split(" ");
            centerX = Float.parseFloat(centerCoordinates[0]);
            centerY = Float.parseFloat(centerCoordinates[1]);
            line = reader.readLine();
            radius = Float.parseFloat(line);
            System.out.println("Центр окружности: (" + centerX + ", " + centerY + ")");
            System.out.println("Радиус окружности: " + radius);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка преобразования строки в число: " + e.getMessage());
        }
        System.out.print("Выберете файл с координатами точек\n");
        System.out.print("Введите путь к файлу:");
        filePath = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int i = 0;
            for (String line = reader.readLine(); line != null || i<100; line = reader.readLine(), i++) {
                String[] pointsLine = line.split(" ");
                points.add(Float.parseFloat(pointsLine[0]));
                points.add(Float.parseFloat(pointsLine[1]));
                System.out.println("Точки: (" + Float.parseFloat(pointsLine[0]) + ", " + Float.parseFloat(pointsLine[1]) + ")");
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования строки в число: " + e.getMessage());
        }
        float pointX = 0, pointY = 0;
        boolean isPointReady = false;
        for(float value : points)
        {
            if(isPointReady)
            {
                pointY = value;
                float distance = (float)Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));
                if (distance < radius) {
                    System.out.println("Точка "+"(" + pointX + ", " + pointY + ")"+"  внутри.\n Ответ:1");
                } else if (distance == radius) {
                    System.out.println("Точка "+"(" + pointX + ", " + pointY + ")"+" лежит на окружности.\n Ответ:0");
                } else {
                    System.out.println("Точка "+"(" + pointX + ", " + pointY + ")"+" снаружи.\n Ответ:2");
                }
                isPointReady = false;
            }
            else
            {
                pointX = value;
                isPointReady = true;
            }
        }
    }
}