package ru.db.touristAgency.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CollectionsUtils {
    public static <T> Collection<T> getTopElements(
            Collection<T> collection, Comparator<T> comparator, int amount) {
        int amountToSkip = Integer.max(0, collection.size() - amount);

        return collection
                .stream()
                .sorted(comparator)
                .skip(amountToSkip)
                .collect(Collectors.toSet());
    }
}
