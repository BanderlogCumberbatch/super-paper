package generator;

import lombok.Getter;

import java.util.Random;

public class TestDataGenerator {
    @Getter
    static private String postCode = generatePostCode();

    // Метод для генерации 10-значного Post Code
    public static String generatePostCode() {
        Random random = new Random();
        StringBuilder postCodeBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            postCodeBuilder.append(random.nextInt(10));
        }

        postCode = postCodeBuilder.toString();
        return postCode;
    }

    // Метод для генерации First Name на основе Post Code
    public static String generateFirstNameFromPostCode() {
        StringBuilder firstName = new StringBuilder();

        // Разбиваем Post Code на 5 двузначных чисел
        for (int i = 0; i < 5; i++) {
            int start = i * 2;
            int end = start + 2;
            String twoDigits = postCode.substring(start, end);
            int number = Integer.parseInt(twoDigits);

            // Преобразуем число в букву (0-25: a-z, 26-51: a-z и т.д.)
            char letter = (char) ('a' + (number % 26));
            firstName.append(letter);
        }

        return firstName.toString();
    }
}
