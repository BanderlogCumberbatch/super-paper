package org.utils;

import java.util.Comparator;
import java.util.List;

public class StringFinder {
    /**
     * Находит ту строку, у которой длина будет ближе
     * к среднему арифметическому.
     * @return String
     */
    public static String getTheMostAverage(List<String> s) {
        // Вычисляем среднее арифметическое длин строк
        double averageLength = s.stream().mapToInt(String::length).average().orElse(0);
        // Находим имя с длиной, ближайшей к среднему значению
        return s.stream()
                .min(Comparator.comparingDouble(a -> Math.abs(a.length() - averageLength)))
                .orElse(null);
    }
}
