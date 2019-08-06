package number;

/**
 * @ClassName BitSolution
 * @Author @lvxile
 * @Date 2019/8/2 9:33
 * @Description TODO
 */
public class BitSolution {
    /**
     * x&1==1 //判断奇偶数
     * x=x&(x-1) // 清零最低位的1
     * x&-x // 得到最低为1的位置
     * 交换x/y
     * x = x ^ y; // (1)
     * y = x ^ y; // (2)
     * x = x ^ y; // (3)
     */
    /**
     * 二进制 1的个数
      * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        while(n != 0)
        {
            n = n&n-1;
            i++;
        }
        return i;
    }

    /**
     * 是否2的幂次方
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
//        if (((int)Math.sqrt(n) & 1) ==0) {
//            return true;
//        }
        if ((n&n-1) == 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        BitSolution bs = new BitSolution();
        System.out.println(bs.isPowerOfTwo(4));
    }

}
