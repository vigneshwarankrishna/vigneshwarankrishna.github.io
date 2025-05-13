package com.interview.questions.practise;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Stack;

@SpringBootApplication
public class PractiseApplication {

    public static void main(String[] args) {
        String input1 = "({[]})";
        String input2 = "({([])})";
        System.out.println("Input: " + input1 + " - Valid: " + isValid(input1));
        System.out.println("Input: " + input2 + " - Valid: " + isValid(input2));
    }

    public static boolean isValid(String s) {
        Stack<Character> brackets = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                brackets.push(c);
            } else if (c == ')' && brackets.peek() == '(') {
                brackets.pop();
            } else if (c == ']' && brackets.peek() == '[') {
                brackets.pop();
            } else if (c == '}' && brackets.peek() == '{') {
                brackets.pop();
            } else {
                return false;
            }
        }
        return brackets.isEmpty();
    }

}


