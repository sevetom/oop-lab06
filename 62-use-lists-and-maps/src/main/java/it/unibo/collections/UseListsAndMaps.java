package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int N1 = 1000;
    private static final int N2 = 2000;
    private static final int N3 = 100_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> list1 = new ArrayList<>();
        for (int i = UseListsAndMaps.N1; i < UseListsAndMaps.N2; i++) {
            list1.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> list2 = new LinkedList<>(list1);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int tmp = list1.get(list1.size()-1);
        list1.set(list1.size()-1,list1.get(0));
        list1.set(0,tmp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int elem : list1) {
            System.out.println(elem);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        TimePerformance(list1, "add", UseListsAndMaps.N3);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        TimePerformance(list2, "read", UseListsAndMaps.N1);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        /*
         * 8) Compute the population of the world
         */
        final Map<String, Long> map = new HashMap<>();
        map.put("Africa", 1_110_635_000L);
        map.put("Americas", 972_005_000L);
        map.put("Antarctica", 0L);
        map.put("Asia", 4_298_723_000L);
        map.put("Europe", 742_452_000L);
        map.put("Oceania", 38_304_000L);

        Long worldPop = 0L;
        for (final Long person : map.values()) {
            worldPop += person;
        }
        System.out.println("The world Population is " + worldPop);
    }

    static void TimePerformance(List<Integer> list, String type, int repetitions) {
        long time = System.nanoTime();
        if (type == "add") {
            for (int i = 0; i < repetitions; i++) {
                list.add(i);
            }
        } else {
            int mid = list.size() / 2;
            for (int i = 0; i < repetitions; i++) {
                list.get(mid);
            }
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(
            "In a list long "
                + list.size() + " "
                + type + "ing " + repetitions + " took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }
}
