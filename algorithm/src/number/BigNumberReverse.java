package number;

import java.util.*;

public class BigNumberReverse {

    public String reverseBigNumber(long number) {
        long num = 0;
        Queue<Integer> stack = new LinkedList<>();
        Integer temp = 0;
        while (number != 0) {
            temp = Math.toIntExact(number % 10);
            stack.add(temp);
            number = number/10;
        }

        while (!stack.isEmpty()) {
            temp = stack.remove();
            System.out.print(temp);
        }
        return null;
    }


    public long reverseBigNumber2(long number) {
        long num = 0;
        Stack<Integer> stack = new Stack<>();
        Integer temp = 0;
        while (number != 0) {
            temp = Math.toIntExact(number % 10);
            stack.push(temp);
            number = number/10;
        }
        int len = stack.size();
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                num = stack.get(i);
            }else{
                num += stack.get(i)*Math.pow(10,len-i-1);
            }

        }
        System.out.println(num);
        return num;
    }


    public static void main(String[] args) {
        BigNumberReverse bigNumberReverse = new BigNumberReverse();
        System.out.println("12345");
        bigNumberReverse.reverseBigNumber(12345l);
        System.out.println("");
        bigNumberReverse.reverseBigNumber2(12345l);
//        301 = 1*10^0 +0*10^1+3*10^2;
    }

}
