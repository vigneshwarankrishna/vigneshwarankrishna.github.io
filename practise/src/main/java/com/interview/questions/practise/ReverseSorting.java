package com.interview.questions.practise;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReverseSorting {

    public static void main(String... args) {
        // reversing a string
        String str = "vigneshwaran";
        String reversed = IntStream.range(0,str.length())
                .mapToObj(i->String.valueOf(str.charAt((str.length()-1-i)))).
                collect(Collectors.joining());
        System.out.println("Reversed String: " + reversed);
        // character occurences
        Map<String,Long> characterOccurences = Stream.of(str.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(characterOccurences);
        // number sorting
        List<Integer> numbers = new ArrayList<>(){{
            add(15);
            add(13);
            add(10);
            add(16);
            add(12);
            add(14);
        }};
        System.out.println("Before sorting....." + numbers);
        numbers = numbers.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        System.out.println("Sorted in descending order....." + numbers);
    }

}
