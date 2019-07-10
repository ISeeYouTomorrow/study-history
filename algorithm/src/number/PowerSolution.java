package number;

/**
 * @ClassName Power
 * @Author @lvxile
 * @Date 2019/7/10 10:37
 * @Description 分治法+递归求取n次方
 */
public class PowerSolution {

    /**
     * 考虑n为负数的情况
     * @param x
     * @param n
     * @return
     */
    public Double power(double x, int n) {
        if (n == 0) {
            return 1.0D;
        }
        int k = n;
        if (n<0) {
            k = Math.abs(n);
            return 1/powerHelper(x, k);
        }else {
            return powerHelper(x, k);
        }
    }

    private Double powerHelper(double x, int n) {
        if (n ==0) {
            return 1.0;
        }
        double half = powerHelper(x,n/2); //分治法
        if (n%2 ==0) {
            return half *half;
        }else {
            return half*half*x;
        }
    }

    public static void main(String[] args) {
        PowerSolution ps = new PowerSolution();
        double x = ps.power(99, -2);
        System.out.println("99^-29999 == "+x);
    }
}
