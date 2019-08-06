package number;

import java.util.Arrays;

/**
 * @ClassName BinarySearch
 * @Author @lvxile
 * @Date 2019/7/31 19:43
 * @Description 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = bs.solution(nums, 9);
        System.out.println("index == "+index);


        int[] nums2 = {1, 3, 2, 9, 4, 7, 5, 8, 6};
        bs.buddleSort(nums2);
        Arrays.stream(nums2).forEach(s -> System.out.println(s));
    }

    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left =0, right = nums.length-1;
        int mid;
        while (left<right) {
            mid = (left +right)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid]<target) {
                left = mid +1;
            }
            if (nums[mid]>target) {
                right = mid -1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public void buddleSort(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length-i-1; j++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j-1] > nums[j]) {
                    temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }
    }
}
