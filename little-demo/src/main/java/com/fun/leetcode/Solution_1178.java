package com.fun.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_1178 {

    public static void main(String[] args) {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        List<Integer> result = findNumOfValidWords(words, puzzles);
        System.out.println(result);
    }

    /**
     * 官方解答 bitmask
     */
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        Map<Integer, Integer> frequency = new HashMap<>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            int total = 0;

            // 枚举子集方法一
            // for (int choose = 0; choose < (1 << 6); ++choose) {
            //     int mask = 0;
            //     for (int i = 0; i < 6; ++i) {
            //         if ((choose & (1 << i)) != 0) {
            //             mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
            //         }
            //     }
            //     mask |= (1 << (puzzle.charAt(0) - 'a'));
            //     if (frequency.containsKey(mask)) {
            //         total += frequency.get(mask);
            //     }
            // }

            // 枚举子集方法二
            int mask = 0;
            for (int i = 1; i < 7; ++i) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subset = mask;
            do {
                int s = subset | (1 << (puzzle.charAt(0) - 'a')); // 总是包含首字母
                if (frequency.containsKey(s)) {
                    total += frequency.get(s);
                }
                subset = (subset - 1) & mask; // puzzle子集掩码
            } while (subset != mask);

            ans.add(total);
        }
        return ans;
    }


    /**
     * 暴力算法，超时
     */
    public static List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {

        List<Integer> result = new ArrayList<>(puzzles.length);

        for (int i = 0; i < puzzles.length; i++) {
            int pi = 0;
            String piStr = puzzles[i];
            // 用 puzzles[i] 和words[]一次表计算满足条件单的情况，满足pi++
            for (int j = 0; j < words.length; j++) {

                // 判断是否包含首字符
                boolean containFirst = words[j].contains(String.valueOf(piStr.charAt(0)));
                if (!containFirst){
                    continue;
                }

                // 判断是否都在谜面之中
                boolean allInRound = true;
                for (int k = 0; k < words[j].length(); k++) {
                    if (!piStr.contains(String.valueOf(words[j].charAt(k)))) {
                        allInRound = false;
                        break;
                    }
                }

                // 满足条件，统计++
                if (containFirst && allInRound) {
                    pi++;
                }
            }
            result.add(pi);
        }

        return result;
    }
}
