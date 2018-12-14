package com.phsxy.sawpuzzle;

import java.util.Random;

/**
 * author: CC_0625
 * date: 2018/12/13
 * dec:
 **/
public class NRandom {
    /**
     * 8      * 对给定数目的自0开始步长为1的数字序列进行乱序
     * 9      * @param no 给定数目
     * 10      * @return 乱序后的数组
     * 11
     */
    public static int[] getSequence(int no) {
        int[] sequence = new int[no];
        for (int i = 0; i < no; i++) {
            sequence[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < no; i++) {
            int p = random.nextInt(no);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }
        random = null;
        return sequence;
    }
}
