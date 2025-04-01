package org.utils;

import com.google.common.collect.Lists;
import java.util.List;

public class SortChecker {
    /**
     * Проверяет отсортирован ли список.
     * @return boolean
     */
    public static boolean isSorted(List<String> list) {
        String previous = "";
        for (final String current: list) {
            if (current.compareToIgnoreCase(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    /**
     * Проверяет отсортирован ли список в обратном порядке.
     * @return boolean
     */
    public static boolean isSortedInReverse(List<String> list) {
        String previous = "";
        for (final String current: Lists.reverse(list)) {
            if (current.compareToIgnoreCase(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }
}
