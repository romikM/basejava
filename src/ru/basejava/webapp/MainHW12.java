package ru.basejava.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MainHW12 {
    private static final int[] testArr1 = {1, 2, 3};
    private static final int[] testArr2 = {7, 5, 1, 4, 2, 3, 3};
    private static final int[] testArr3 = {1, 2, 3, 7, 5, 4, 6, 6, 8, 1, 4, 5,};
    private static final int[] testArr4 = {9, 8, 5, 1, 3, 7, 2};
    private static final int[] testArr5 = {7, 7, 1};

    private static List<Integer> testList1 = makeList(testArr1);
    private static List<Integer> testList2 = makeList(testArr2);
    private static List<Integer> testList3 = makeList(testArr3);
    private static List<Integer> testList4 = makeList(testArr4);
    private static List<Integer> testList5 = makeList(testArr5);

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
        int[] result = IntStream.of(values).distinct().toArray();
        Arrays.sort(result);
        int res = 0;
        for (int i = 0; i < result.length; i++) {
            res = res * 10 + result[i];
        }
        return res;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        int summ = 0;
        for (int i: integers) {
            if (i%2==0) {
                odds.add(i);
            } else {
                evens.add(i);
            }
            summ+=i;
        };
        if (summ%2==0) {
            return odds;
        }
        return evens;
    }

    private static List<Integer> makeList(int[] ints) {
        List<Integer> intList = new ArrayList<>(ints.length);
        for (int i : ints) {
            intList.add(i);
        }
        return intList;
    }
}
