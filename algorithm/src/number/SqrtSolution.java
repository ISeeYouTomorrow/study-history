package number;

/**
 * @ClassName SqrtSolution
 * @Author @lvxile
 * @Date 2019/7/31 20:06
 * @Description 求解平方根
 */
public class SqrtSolution {

    public static void main(String[] args) {
        SqrtSolution solution = new SqrtSolution();
        float i = solution.mySqrt(2);
        System.out.println("-------- "+i);

        int t = solution.mySqrt_NewTon(6);
        System.out.println("-------- "+t);
    }

    /**
     * 牛顿法 x = (x+a/x)/2
     * @param a
     * @return
     */
    public int mySqrt_NewTon(int a) {
        long x = a;
        while(x*x>a) {
            x = (x +a/x)/2;
        }
        return (int)x;
    }


    /**
     * 二分法 线性递增逼近
     * @param x
     * @return
     */
    public long mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        long left = 1;
        long right = x / 2;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;
    }

}
