package apjp2016;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HW52 {
	

	/**
	 * CubbyHole2 can hold at most 2 pairs of numbers
	 * Implement this class to satisfy the requirement 
	 * 
	 * @author chencc
	 * 
	 */
	public static class CubbyHole2 {
		
		private int x, y;
		private ArrayList<pair> n = new ArrayList<pair>(); 
		private Lock hwlock = new ReentrantLock();
		private Condition hwc = hwlock.newCondition();
		
		//The following two methods are dummy methods used simply to make
		//the program no syntax error. 
		
		public class pair{
			int x,y;
			pair(int a, int b){
				this.x = a;
				this.y = b;
			}
			int getsum(){
				return x+y;
			}
		}
		public int get() {
			
			hwlock.lock();
			int c = -1;
			
			try{
				while((n.size() == 0)){
					hwc.await();
				}
				c = n.remove(0).getsum();

				hwc.signal();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				hwlock.unlock();
			}
			return c;
		}
		
		public void put(int a, int b){
			hwlock.lock();
			try{
				while( (n.size() == 2)){
					hwc.await();
				}
				n.add(new pair(a,b));
				hwc.signal();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				hwlock.unlock();
			}
		}

	}

	public static class Producer implements Runnable {
		private CubbyHole2 cubbyhole;
		private int id;

		public Producer(CubbyHole2 c, int id) {
			cubbyhole = c;
			this.id = id;
		}

		public void run() {
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++) {
					cubbyhole.put(i, j);
					System.out.println("Producer #" + this.id + " put: (" + i
							+ "," + j + ").");
					try {
						Thread.sleep((int) (Math.random() * 100));
					} catch (InterruptedException e) {
					}
				}
			;
		}
	}

	public static class Consumer implements Runnable {
		private CubbyHole2 cubbyhole;
		private int id;

		public Consumer(CubbyHole2 c, int id) {
			cubbyhole = c;
			this.id = id;
		}

		public void run() {
			int value = 0;
			for (int i = 0; i < 25; i++) {
				value = cubbyhole.get();
				System.out.println("Consumer #" + this.id + " got: " + value);
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException e) {
				}
			}
			
		}
	}

	public static void main(String[] args) {

		CubbyHole2 c = new CubbyHole2();
		

		Producer[] ps = new Producer[4];
		for (int k = 0; k < 4; k++) {
			ps[k] = new Producer(c, k);
		}

		Consumer[] cs = new Consumer[4];
		for (int k = 0; k < 4; k++) {
			cs[k] = new Consumer(c, k);
		}

		// Using fixed-sized ThreadPool with size(3) < number of producers (5)
		// will result in deadlock. The available threads(3) will be hold by producers which
		// are blocked in the blockingQueue and cannot terminate.
		
		// ExecutorService es = Executors.newFixedThreadPool(3);
		ExecutorService es = Executors.newCachedThreadPool();

		long startTime = System.currentTimeMillis();

		for (Producer p : ps) {
			es.execute(p);
		}

		for (Consumer csumer : cs) {
			es.execute(csumer);
		}

		es.shutdown();

		while (!es.isTerminated()) {
			try {
				es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			} catch (InterruptedException e) {
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("The total execution time is "
				+ (endTime - startTime) + " milliseconds");

	}

}
