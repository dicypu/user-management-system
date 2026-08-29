package util;

import java.util.List;

public class CollectionUtils {

    /**
     * Generic Metot: Herhangi bir tipteki Listeden ilk elemanı döner.
     * @param <T> Metodun çalışacağı genel veri tipi
     * @param list İncelenecek liste
     * @return Listenin ilk elemanı veya liste boşsa null
     */
    public static <T> T getFirst(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}