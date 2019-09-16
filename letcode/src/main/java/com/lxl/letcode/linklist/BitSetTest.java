package com.lxl.letcode.linklist;

import org.testng.annotations.Test;

import java.util.BitSet;
import java.util.Random;

/**
 * @author Lukas
 * @since 2019/6/17 15:22
 **/
public class BitSetTest {
    Random random = new Random();

    @Test
    public void testBitSet() {
        int temp ;
        BitSet bitSet = new BitSet(100);
        for (int i=0;i<100;i++) {
            temp = random.nextInt(100);
            bitSet.set(temp);
            System.out.print(temp+" ");
        }

        System.out.println();

        for (int i=0;i<100;i++) {
            if (!bitSet.get(i)) {
                System.out.print(i+" ");
            }
        }

    }



}
