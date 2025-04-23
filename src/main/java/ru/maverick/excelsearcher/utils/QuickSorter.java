package ru.maverick.excelsearcher.utils;

import java.util.List;

public class QuickSorter {

    public static List<Integer> quickSort(List<Integer> list) {
        if (list == null || list.isEmpty()) return list;
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private static void quickSort(List<Integer> list, int low, int high) {
        if (low >= high) return;

        int middle = low + (high - low) / 2;
        int opora = list.get(middle);

        int i = low, j = high;
        while (i <= j) {
            while (list.get(i) < opora) i++;
            while (list.get(j) > opora) j--;

            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        if (low < j) quickSort(list, low, j);
        if (high > i) quickSort(list, i, high);
    }

    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}