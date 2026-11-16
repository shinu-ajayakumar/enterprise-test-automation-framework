package com.shinu.automation.practice;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParenthesesTest {

    private static final Map<Character, Character> PAIRS = Map.of(')', '(', ']', '[', '}', '{');

    static boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (PAIRS.containsValue(c)) {
                stack.push(c);
            } else if (PAIRS.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (open != PAIRS.get(c)) {
                    return false;
                }
            } else {
                return false;               // any other character is invalid
            }
        }
        return stack.isEmpty();
    }

    @DataProvider
    public Object[][] cases() {
        return new Object[][] {
                {"()", true}, {"()[]{}", true}, {"{[]}", true}, {"((({{[[]]}})))", true}, {"", true},
                {"(]", false}, {"([)]", false}, {"(", false}, {")", false}, {"((", false},
                {"())", false}, {null, false}, {"(a)", false}
        };
    }

    @Test(dataProvider = "cases", groups = "practice")
    public void detectsBalancedBrackets(String input, boolean expected) {
        Assert.assertEquals(isValid(input), expected, "Input: " + input);
    }
}