package com.interview.questions.practise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int[] response = new int[nums.length];
        int[] leftProducts = new int[nums.length];
        int[] rightProducts = new int[nums.length];
        Arrays.fill(leftProducts, 1);
        Arrays.fill(rightProducts, 1);
        for (int i = 1; i < nums.length; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            response[i] = rightProducts[i] * leftProducts[i];
        }
        return response;
    }

    public static void main(String[] args) {
        List<String> listOf = Arrays.asList("Banana", "Apple", "Mango",
                "Guava", "Apple", "Banana", "Apple", "Mango");
        Map<String,Long> map = listOf.stream().collect
                (Collectors.groupingBy(f->f,Collectors.counting()));
        System.out.println(map .entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Find max occurrence
                .get()
                .getKey());
    }

}
