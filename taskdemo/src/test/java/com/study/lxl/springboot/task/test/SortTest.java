package com.study.lxl.springboot.task.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.junit.Test;
public class SortTest {
	DescComparator comparator = new DescComparator();

	@Test
	public void testRandomfile() throws IOException {
		RandomAccessFile file = new RandomAccessFile("F:\\tomcat\\study\\apache-tomcat-8.5.31-dongnao-mall\\conf\\web - 副本.xml", "rw");
		System.out.println("file length "+file.length());
		FileChannel channel = file.getChannel();
		MappedByteBuffer mbb = channel.map(MapMode.READ_WRITE, 0, channel.size());
		int i=0;
		for(i=0;i<mbb.capacity();i++) {
			System.out.print(mbb.getChar());
		}
		mbb.clear();
		file.close();
	}

	/**
	 * 比较器 倒排
	 * @author 18515
	 *
	 */
	class DescComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 > o2) {
				return -1;
			}else {
				return 1;
			}
		}
	}



	/**
	 * set 排序
	 */
	@Test
	public void testTreeSet() {
		int size = 2000000;
		int top = 10;
		TreeSet<Integer> set = new TreeSet<>(comparator);
		for(int i=0;i<size;i++) {
			set.add((int)(Math.random()*size));
			if(set.size() > top) {
				set.pollLast();
			}
		}
		System.out.println("set size = "+set.size());

		for(Integer val : set) {
			System.out.print(""+val+",");
		}

	}

	@Test
	public void testTreeSet2() {
		int size = 100;
		int top = 3;
		TreeSet<Integer> set = new TreeSet<>();
//		for (int i = 0; i < 100; i++) {
//			set.add(i%10);
//            if (set.size() > top) {
//                set.pollLast();
//            }
//		}
        int[] nums = new int[]{1,1,2,2,3,3,3};
        for (int num : nums) {
            if (set.size()<top) {
                set.add(num);
            }else{
                if (set.first() < num) {
                    set.pollFirst();
                    set.add(num);
                }
            }

        }

        for (Integer integer : set) {
            System.out.println(integer);
        }

	}


	/**
	 * treemap 排序
	 */
	@Test
	public void testTreeMap() {
		int size = 100;
		int top = 10;
		TreeMap<Integer, Integer> tree = new TreeMap<>();
		for(int i=0;i<size;i++) {
			tree.put((int)(Math.random()*size),i);
			if(tree.size() > top) {
				tree.remove(tree.firstKey());
			}
		}

		System.out.println("tree size :"+tree.size());

		Set<Map.Entry<Integer,Integer>> set = tree.entrySet();
		Iterator<Map.Entry<Integer,Integer>> it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<Integer,Integer> map = it.next();
			System.out.println(""+map.getKey()+" => "+map.getValue());
		}
	}

	private BlockingQueue<Integer> container = new SynchronousQueue();
	private volatile boolean terminate = false;

	private List<Integer> list = new ArrayList<>();

	private Integer getRandom() {
		return (int)(Math.random()*10);
	}

	/**
	 * 测试生产者不停的写入一个整数，消费者读取该整数，如果连续三个数之和=9，则停止
	 */
	public void testQueue() {
		Thread provider = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!terminate) {
					try {
						Thread.sleep(3000);
						Integer temp = getRandom();
						System.out.println("put "+temp);
						if(terminate)
							break;
						container.put(temp);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});


		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!terminate) {
					try {
						Thread.sleep(3000);
						if (list.size()>2) {
							list.remove(0);
						}
						Integer temp = container.take();
						System.out.println("take "+temp);
						list.add(temp);
						terminate = trace(list);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		consumer.start();

		provider.start();

		try {
			consumer.join();
			provider.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean trace(List<Integer> list) {
		Integer t = 0;
		for (Integer temp : list) {
			System.out.print(temp+" ");
			t += temp;
		}
		System.out.println();
		if(list.size() <3)
			return false;

		return t==9;
	}

    /**
     * 快速排序
     * @param a
     * @param l
     * @param r
     */
    public static void quickSort(int a[],int l,int r){
        if(l>=r)
            return;

        int i = l; int j = r; int key = a[l];//选择第一个数为key

        while(i<j){

            while(i<j && a[j]>=key)//从右向左找第一个小于key的值
                j--;
            if(i<j){
                a[i] = a[j];
                i++;
            }

            while(i<j && a[i]<key)//从左向右找第一个大于key的值
                i++;

            if(i<j){
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i-1);//递归调用
        quickSort(a, i+1, r);//递归调用
    }

    /**
     * 快速排序
     * @param array
     * @param left
     * @param right
     */
    public void quicksort(int [] array,int left, int right) {
        int i, j, t, temp;
        if(left >=right)
            return;
        temp = array[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while(i != j) { //顺序很重要，要先从右边开始找
            while(array[j] >= temp && i < j)
                j--;
            while(array[i] <= temp && i < j)//再找右边的 必须是>=,因为一开始temp==array[i]，第一个数与自己比较，
                i++;
            if(i < j)//交换两个数在数组中的位置
            {
                t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        //最终将基准数归位
        array[left] = array[i];
        array[i] = temp;
        quicksort(array,left, i-1);//继续处理左边的，这里是一个递归的过程
        quicksort(array,i+1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    /**
     * 桶排序
     * @param nums
     * @return
     */
    public int[] buketSortArray(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int[] buckets = new int[max - min + 1];
        for (int num : nums) {
            buckets[num - min]++;
        }

        int index = 0;
        int n = buckets.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i]; j++) {
                nums[index++] = min + i;
            }
        }

        return nums;
    }

    /**
     * 归并排序
     * @param array
     * @param startIndex
     * @param endIndex
     */
    public void mergeSort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        // 获取最中间的元素索引
        int middleIndex = (startIndex + endIndex) / 2;

        // 左半边排序
        mergeSort(array, startIndex, middleIndex);

        // 右半边排序
        mergeSort(array, middleIndex + 1, endIndex);

        merge(array, startIndex, endIndex);
    }

    public void merge(int[] array, int startIndex, int endIndex) {
        int[] tmp = new int[endIndex - startIndex + 1];
        int middleIndex = (startIndex + endIndex) / 2;
        int i = startIndex;
        int j = middleIndex + 1;
        int k = 0;
        while (i <= middleIndex && j <= endIndex) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }

        int start = i;
        int end = middleIndex;
        if (i > middleIndex) {
            start = j;
            end = endIndex;
        }
        while (start <= end) {
            tmp[k++] = array[start++];
        }
        for (int m = 0; m < tmp.length; m++, startIndex++) {
            array[startIndex] = tmp[m];
        }
    }


	public static void main(String[] args) {

		SortTest st = new SortTest();
//		System.out.println(st.getRandom());
//		st.testQueue();

        int [] array = new int[]{1,2,7,6,4};
		st.quickSort(array,0,4);
        for (int i : array) {
            System.out.println(i);
        }

	}

}
