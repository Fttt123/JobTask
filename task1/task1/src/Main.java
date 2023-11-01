import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива\n");
        int n = readInteger(scanner);
        System.out.print("Введите длину пути\n");
        int m = readInteger(scanner);

        int[] circularArray = new int[m];
        int startValue = 1;
        int arrayValue = 0;

        StringBuilder path = new StringBuilder();

        for(int i = 0; i < m; i++, arrayValue++) {
            circularArray[i] = startValue + arrayValue;

            if(startValue + i == n) {
                startValue = 1;
                arrayValue = -1;
            }

            if(i == m - 1 && circularArray[i] != 1) {
                startValue = circularArray[i];
                arrayValue = -1;
                i = -1;
            }

            if(i == 0) {
                path.append(circularArray[i]);
            }
        }

        System.out.print("Путь: " + path);
    }

    public static int readInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Ошибка! Введено некорректное значение. Попробуйте снова.");
            System.out.print("Введите целое число: ");
        }
        return scanner.nextInt();
    }
}