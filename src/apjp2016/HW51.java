package apjp2016;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HW51 {

	private int x, y;
	private boolean available = false; // condition var
	private Lock hwlock = new ReentrantLock();
	private Condition hwc = hwlock.newCondition();
	
	public int get() {
		
		hwlock.lock();
		
		try{
			while(!available){
				hwc.await();
			}
			available = false;
			hwc.signal();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hwlock.unlock();
		}
		return x + y;
	}
	
	public void put(int a, int b){
		hwlock.lock();
		try{
			while(available){
				hwc.await();
			}
			x = a;
			y = b;
			available = true;
			hwc.signal();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			hwlock.unlock();
		}
	}

	/*public synchronized int get() {
		while (available == false) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		available = false; // enforce next consumer to wait again.
		notifyAll(); // notify all producer/consumer to compete for execution!
		// use notify() if just wanting to wakeup one thread!
		return x + y;
	}

	public synchronized void put(int a, int b) {
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		x = a;
		y = b;
		available = true; // wake up waiting consumer/producer to continue
		notifyAll(); // or notify(); }}
	}*/

	
	public static class Producer extends Thread {
		private HW51 cubbyhole;
		private int id;

		public Producer(HW51 c, int id) {
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
						sleep((int) (Math.random() * 100));
					} catch (InterruptedException e) {
					}
				}
			;
		}
	}

	public static class Consumer extends Thread {
		private HW51 cubbyhole;
		private int id;

		public Consumer(HW51 c, int id) {
			cubbyhole = c;
			this.id = id;
		}

		public void run() {
			int value = 0;
			for (int i = 0; i < 25; i++) {
				value = cubbyhole.get();
				System.out.println("Consumer #" + this.id + " got: " + value);
			}
		}
	}
	
	public static void main(String[] args) {
		HW51 c = new HW51();
		Producer p1 = new Producer(c, 1);
		Consumer c1 = new Consumer(c, 1);
		p1.start();
		c1.start();
		}

	

}
