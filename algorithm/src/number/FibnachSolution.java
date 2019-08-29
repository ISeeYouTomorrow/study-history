package number;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FibnachSolution
 * @Author @lvxile
 * @Date 2019/8/3 9:15
 * @Descriptio 斐波那契数列
 */
public class FibnachSolution {
    private Map<Integer, Integer> map = new HashMap<>();


    public static void main(String[] args) {
        FibnachSolution fib = new FibnachSolution();
        int x = fib.fib(10);
        System.out.println("x --- "+x);

        int x2 = fib.fib2(10);
        System.out.println("x2 --- "+x2);

        int x3 = fib.fib3(10);
        System.out.println("x3 --- "+x3);
    }

    /**
     * 循环法
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int one = 0;
        int two = 1;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum = one+two; //第一个数+第二个数 f(N)
            two = one;//第一个数变成第二个数 f(n-2)
            one = sum;//前次求和变成第一个数 f(n-1)
        }
        return sum;
    }


    public int fib3(int n) {
        if (n <= 2) {
            return n;
        }
        if (n==3) {
            return 4;
        }
        int one = 0;
        int two = 1;
        int three = 2;
        int sum = 0;
        for(int i=0;i<n;i++){
            sum = one+two+three; //第一个数+第二个数 f(N)
            three = two;
            two = one;//第一个数变成第二个数 f(n-2)
            one = sum;//前次求和变成第一个数 f(n-1)
        }
        return sum;
    }


    /**
     * 递归法
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            if (map.containsKey(n)) {
                return map.get(n);
            }else {
                int x = fib(n-1)+fib(n-2);
                map.put(n, x);
                return x;
            }
        }
    }

}
