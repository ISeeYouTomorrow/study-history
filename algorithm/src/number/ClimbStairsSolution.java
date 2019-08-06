package number;

/**
 * @ClassName ClimbStairsSolution
 * @Author @lvxile
 * @Date 2019/8/3 16:08
 * @Description 爬楼梯
 */
public class ClimbStairsSolution {

    public static void main(String[] args) {
        ClimbStairsSolution cs = new ClimbStairsSolution();
        int x1 = cs.climbStairs(10);
        System.out.println("x1 ==== "+x1);
        int n = 10;
        int[] mark = new int[n+1];
        int x2 = cs.climb_Stairs_fast(0, n, mark);
        System.out.println("x2 ==== "+x2);

        int x3 = cs.climbStairsNew(n);
        System.out.println("动态规划结果: "+x3);

    }

    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }

    /**
     * 递归法
     * @param i 当前层数
     * @param n 目标层数
     * @return
     */
    private int climb_Stairs(int i, int n) {
        if (i == n) {
            return 1;
        }
        if (i>n) {
            return 0;
        }
        //从第1层爬到第n层 + 第2层爬到第n层
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }

    /**
     * 归纳记忆法-剪枝
     * @param i
     * @param n
     * @param mark
     * @return
     */
    private int climb_Stairs_fast(int i, int n, int[] mark) {
        if (i == n) {
            return 1;
        }
        if (i>n) {
            return 0;
        }
        if (mark[i] > 0) {//第i层的方法数
            return mark[i];
        }
        mark[i] = climb_Stairs_fast(i+1,n,mark)+climb_Stairs_fast(i+2,n,mark);
        //从第1层爬到第n层 + 第2层爬到第n层
        return mark[i];
    }

    /**
     * 动态规划
     * @param n 总层数
     * @return
     */
    public int climbStairsNew(int n) {
        if(n == 1) {
            return 1;
        }
        int[] mark = new int[n+1];
        mark[0] = 0;
        mark[1] = 1;
        mark[2] = 2;
        for (int i = 3; i <= n; i++) {
            mark[i] = mark[i - 1] + mark[i - 2];
        }
        return mark[n];
    }
}
