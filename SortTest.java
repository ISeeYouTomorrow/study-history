package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
	
	
	public static void main(String[] args) {
		
		SortTest st = new SortTest();
//		System.out.println(st.getRandom());
		st.testQueue();
	}

}
