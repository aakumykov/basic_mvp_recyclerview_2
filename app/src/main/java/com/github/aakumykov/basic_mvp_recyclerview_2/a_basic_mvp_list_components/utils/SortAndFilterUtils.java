package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SortAndFilterUtils {

    public static <T> List<T> sortList(List<T> inputList, Comparator<T>comparator) {
        return inputList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public static <T> List<T> filterList(List<T> inputList, Predicate<? super T> predicate) {
        return inputList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
