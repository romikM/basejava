package ru.basejava.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainListStreams {
    private static final int[] testArr1 = {1, 2, 3};
    private static final int[] testArr2 = {7, 5, 1, 4, 2, 3, 3};
    private static final int[] testArr3 = {1, 2, 3, 7, 5, 4, 6, 6, 8, 1, 4, 5,};
    private static final int[] testArr4 = {9, 8, 5, 1, 3, 7, 2};
    private static final int[] testArr5 = {7, 7, 1};

    private static final List<Integer> testList1 = makeList(testArr1);
    private static final List<Integer> testList2 = makeList(testArr2);
    private static final List<Integer> testList3 = makeList(testArr3);
    private static final List<Integer> testList4 = makeList(testArr4);
    private static final List<Integer> testList5 = makeList(testArr5);

    public static void main(String[] args) {

        System.out.println(minValue(testArr1));
        System.out.println(minValue(testArr2));
        System.out.println(minValue(testArr3));
        System.out.println(minValue(testArr4));
        System.out.println(minValue(testArr5));

        System.out.println(oddOrEven(testList1));
        System.out.println(oddOrEven(testList2));
        System.out.println(oddOrEven(testList3));
        System.out.println(oddOrEven(testList4));
        System.out.println(oddOrEven(testList5));

    }

    private static int minValue(int[] values) {
        return IntStream.of(values).distinct().sorted().reduce(0, (a, b) -> a * 10 + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> result = integers.stream().collect(Collectors.partitioningBy(p -> p % 2 == 0));
        return result.get(result.get(false).size() % 2 == 0);
    }

    private static List<Integer> makeList(int[] ints) {
        List<Integer> intList = new ArrayList<>(ints.length);
        for (int i : ints) {
            intList.add(i);
        }
        return intList;
    }
}
