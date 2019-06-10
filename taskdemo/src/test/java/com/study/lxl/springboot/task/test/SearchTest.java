package com.study.lxl.springboot.task.test;

import org.junit.Test;

/**
 * @author Lukas
 * @since 2019/6/5 17:34
 **/
public class SearchTest {

    public int searchBinaryResolute(int[] array, int target,int start,int end) {
        int mid;
        mid = start + (end-start)/2;//设置中间点
        if (array[mid] == target) {
            return mid;
        }else if (array[mid]>target){//在左半边
            end = mid-1;
            return searchBinaryResolute(array,target,start,end);
        }else {//在右半边
            start = mid +1;
            return searchBinaryResolute(array,target,start,end);
        }
    }

    //非递归方法
    public int searchBinary(int[] array, int target) {
        int start = 0;//左边界
        int end = array.length-1;//右边界
        int mid;
        while (start <= end) {
            mid = start + (end-start)/2;//设置中间点
            if (array[mid] == target) {
                return mid;
            }else if (array[mid]>target){//在左半边
                end = mid-1;
            }else {//在右半边
                start = mid +1;
            }
        }

        return -1;
    }

    @Test
    public void testSearch() {
        int[] a = {1,3,5,7,9,10,20,30,50};
        int index = searchBinary(a,50);
        System.out.println("index = "+index);


        int index2 = searchBinaryResolute(a,50,0,a.length-1);
        System.out.println("index2 = "+index2);
    }


}
