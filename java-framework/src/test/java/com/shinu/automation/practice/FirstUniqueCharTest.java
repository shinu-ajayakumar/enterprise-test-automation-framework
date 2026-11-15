package com.shinu.automation.practice;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharTest {

    static int firstUniqueCharIndex(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.merge(c, 1, Integer::sum);
        }
        for (int i = 0; i < s.length(); i++) {
            if (counts.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    @DataProvider
    public Object[][] cases() {
        return new Object[][] {
                {"leetcode", 0}, {"loveleetcode", 2}, {"aabb", -1}, {"", -1}, {"D", 0},
                {null, -1}, {"YTFHhgvdsfvJHGKJ", 0}, {"765675ghvjhgv", 9}, {"&^$%*&^", 2}
        };
    }

    @Test(dataProvider = "cases", groups = "practice")
    public void returnsIndexOfFirstUniqueChar(String input, int expected) {
        Assert.assertEquals(firstUniqueCharIndex(input), expected);
    }
}