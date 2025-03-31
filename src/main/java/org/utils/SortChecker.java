package org.utils;

import java.util.List;

public class SortChecker {

    /**
     * Проверяет отсортирован ли список.
     * @return boolean
     */
    public static boolean isSorted(List<String> list) {
        boolean isSorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    /**
     * Проверяет отсортирован ли список в обратном порядке.
     * @return boolean
     */
    public static boolean isSortedInReverse(List<String> list) {
        boolean isSorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) < 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}
