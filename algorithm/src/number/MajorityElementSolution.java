package number;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName MajorityElement
 * @Author @lvxile
 * @Date 2019/7/10 11:49
 * @Description 求众数
 */
public class MajorityElementSolution {

    public int majorityElement_1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * 栈顶元素即众数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i:nums) {
            if (stack.isEmpty() || stack.peek() == i) {
                stack.push(i);
            }else{
                stack.pop();
            }
        }

        stack.stream().forEach(t-> System.out.println(t));

        return stack.firstElement();
    }

    public static void main(String[] args) {
        MajorityElementSolution solution = new MajorityElementSolution();
        int x = solution.majorityElement(new int[]{3,2,3,4,3,1,3});

        System.out.println(x);
    }


}
