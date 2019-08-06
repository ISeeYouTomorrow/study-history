package number;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NKuoHao
 * @Author @lvxile
 * @Date 2019/7/31 16:54
 * @Description TODO
 * n 生成有效括号组合
 * 1 ()
 * 2 ()() (())
 */
public class NKuoHao {


    public static void main(String[] args) {
        NKuoHao nKuoHao = new NKuoHao();
        nKuoHao.solution(3);

        nKuoHao.list.forEach(s -> System.out.println(s));

    }

    private List<String> list = new ArrayList<>();

    public List<String> solution(int n) {
        //递归实现n括号
        gen(0, 0, n, "");
        return list;
    }

    /**
     * @param left   左括号使用个数
     * @param right  右括号使用个数
     * @param n      总括号数
     * @param result
     */
    private void gen(int left, int right, int n, String result) {
        if (left == n && right == n) {
            System.out.println("result ====== "+result);
            list.add(result);
        }
        if (left < n) {//判断左括号的个数小于n
            gen(left + 1, right, n, result + "(");
        }
        if (left > right && right < n) {//判断左括号的个数大于右括号的个数且右括号的个数小于n
            gen(left, right + 1, n, result + ")");
        }
    }
}
