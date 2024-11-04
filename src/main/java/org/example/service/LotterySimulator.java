package org.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LotterySimulator {
    boolean win;
    Random random = new Random();
    int loopCount;

    public int startSimulator(Set<Integer> chooseNumbers, int numbersOfHits) {
        while (win == false) {
            loopCount++;
            Set<Integer> lotteryNum = new HashSet<>();
            while (lotteryNum.size() < 6) {
                lotteryNum.add(random.nextInt(49) + 1);
            }

            int count = 0;
            for (Integer num : chooseNumbers) {
                if (lotteryNum.contains(num)) count++;
                if (count == numbersOfHits) win = true;
            }
        } return loopCount;
    }
}