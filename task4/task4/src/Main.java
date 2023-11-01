import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу с масивом:");
        String filePath = scanner.nextLine();
        String arrayValue = "";
        int[] intArray;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                arrayValue += line + " ";
            }
            String[] stringArray = arrayValue.split(" ");
            intArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                intArray[i] = Integer.parseInt(stringArray[i]);
            }

            int calculationResponse = 0, answer = 0;
            boolean firstAnswer = true;
            for(int i = 0;i<intArray.length;i++)
            {
                for(int j = 0;j<intArray.length;j++)
                {
                    if(intArray[j]<intArray[i])
                    {
                        intArray[j]++;
                        calculationResponse++;
                        j--;
                    }
                    else if(intArray[j]>intArray[i])
                    {
                        intArray[j]--;
                        calculationResponse++;
                        j--;
                    }
                    if(j>=intArray.length-1)
                    {
                        if(answer>calculationResponse)
                        {
                            answer = calculationResponse;
                        }
                        else if(firstAnswer)
                        {
                            answer = calculationResponse;
                            firstAnswer = false;
                        }
                    }
                }
                for (int x = 0; x < stringArray.length; x++) {
                    intArray[x] = Integer.parseInt(stringArray[x]);
                }
                calculationResponse = 0;
            }
            System.out.println("Ответ: " + answer);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования строки в число: " + e.getMessage());
        }
    }
}