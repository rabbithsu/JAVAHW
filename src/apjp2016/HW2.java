package apjp2016;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import apjp2016.HW2Solution.PQueue;
import apjp2016.HW2Solution.Pair;
import apjp2016.HW2Solution.Table;
import apjp2016.HW2Solution.Test;*/

public class HW2 {

	public static void main(String[] args) {

		mainTest();

	}

	/**
	 * Return the minimum and maximum of all numbers in the variable argument
	 * nums as an array. Please see java.lang.Number for understanding
	 * properties of Number. Your program should pass the following tests:
	 * <ul>
	 * <li>minMax() return {}
	 * <li>minMax(3) return {3D,3D}.
	 * <li>minMax(10, 20D, 3) return {3D, 20D}
	 * <li>mainMax(new BigInteger("200"), 20D, -3L) return {-3D, 200D}.
	 * </ul>
	 * 
	 * @param nums
	 *            a array of Numbers.
	 * @return a double array with minimum value put at position 0 and maximum
	 *         value put at position 1 or an empty array if there is no input.
	 */
	@SafeVarargs
	public static <N extends Number> double[] minMax(N... nums) {
		// replace following code by yours.
		Double min = null;
		Double max = null;
		if(nums.length == 0)
			return new double[0];
		else{
			for(N s : nums){
				if(min==null){
					min = s.doubleValue();
					max = s.doubleValue();
				}
				max = Double.max(max, s.doubleValue());
				min = Double.min(min, s.doubleValue());
			}
			double [] n = {min, max};
			return n;
		}
	}

	/**
	 * This is a generic class used to store a pair (key, value) of values of
	 * two generic types <K,V>. The value-part of instances of this class can be
	 * changed via setValue(), while the key-part of this class cannot be
	 * changed once it was created. This class is provided for your for the
	 * implementation of other classes.
	 * 
	 *
	 * @param <K>
	 *            the type of first component (key-part)
	 * @param <V>
	 *            the type of second component (value-part)
	 */

	public static class Pair<K, V> {
		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Pair(K key) {
			this(key, null);
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V v) {
			value = v;
		}

		/**
		 * Return a string representation of this pair.
		 */
		@Override
		public String toString() {
			return "(" + key + "," + value + ")";
		}

	}

	/**
	 * Implement a class Table<K, V> that manages an array or a list of Pair<K,
	 * V> elements. Supply the following methods :
	 * <ul>
	 * <li>get(K k) : get the value associated with a key,
	 * <li>size() : return the number of pairs this table has.
	 * <li>put(K k, V v): put a value v for a key k,
	 * <li>remove(K k): remove any pair with a key k.
	 * <li>asArray() : return all pairs in the table as an array of elements of
	 * type Pair<K,T>
	 * </ul>
	 * and the following two constructors:
	 * <ul>
	 * <li>Table() : construct an empty Table.
	 * <li>Table(Table<?,?> table) : construct a table by copying the contents
	 * of an existing table.
	 * </ul>
	 * You must implement this class by providing suitable data structure to
	 * hold all pairs data in the table.
	 * 
	 * @author chencc
	 *
	 * @param <K>
	 *            type of key
	 * @param <V>
	 *            type of value
	 */

	public static class Table<K, V> {
		// add required data fields for storing instances of Pair<K,V>.
		ArrayList<Pair> n = new ArrayList<Pair>();

		/**
		 * Construct an empty table without any (key,value) Pair.
		 */
		public Table() {
			// put your code here
			n.clear();
		}

		/**
		 * Given two arrays of keys and values of the same size, populate the
		 * table with pairs consisting of key and value from both array with the
		 * same index. All elements of keys are assumed to be distinct.
		 * 
		 * @param keys
		 *            an array of keys
		 * @param values
		 *            an array of values
		 */
		public Table(K[] keys, V[] values) {
			
			// put your code here!
			
			for(int i = 0; i < keys.length; i++){
				n.add(new Pair(keys[i], values[i]));
				
			}
			
		}

		/**
		 * Copy the content of an input table to construct a new Table.
		 * 
		 * Change the wildcard type ? to bounded above or bounded below type so
		 * that this constructor can accept as many types of input as possible.
		 * 
		 */
		public Table(Table<? /* extends/super K */ , ? /* extends/super V */> table) {
			// put your code here to copy pairs from input table to your Table
			// data structure
			Pair<?, ?>[] c = table.toArray();
			for(int i = 0; i < c.length; i++){
				n.add(new Pair(c[i].getKey(), c[i].getValue()));
			}
			

		}

		/**
		 * Return your table content as an array of elements of type Pair<K,V>.
		 * 
		 * @return
		 */
		public Pair<K, V>[] toArray() {
			// replace the following code by yours!
			Pair<K, V> [] a = new Pair [n.size()];
			for(int i = 0; i < n.size(); i++){
				a[i] = n.get(i);
			}
			return a;
		}

		/**
		 * return the value-part of a pair(key, value) if the table contains a
		 * pair(key value) whose key-part is key. Return null if there is no
		 * such pair.
		 * 
		 * @param key
		 *            the key to search
		 * @return val if the is a pair(key, val) in the table; return null
		 *         otherwise.
		 */
		public V get(K key) {
			// replace the following code by yours!
			for(Pair a : n){
				if(a.getKey().equals(key)){
					return (V) a.getValue();
				}
			}
			return null;
		}

		/**
		 * Return the number of pairs this table has.
		 * 
		 * @return
		 */
		public int size() {
			// Replace following code by yours.
			return n.size();
		}

		/**
		 * Remove a pair with key part given by the input. return true if there
		 * is such pair and it is removed and return fals eif there is no such
		 * pair.
		 * 
		 * @param key
		 * @return true if a pair is removed and false if there is no such pair.
		 */
		public boolean remove(K key) {
			// replace the following code by yours!
			for(Pair a : n){
				if(a.getKey().equals(key)){
					n.remove(a);
					return true;
				}
			}
			
			return false;
		}

		/**
		 * Insert a new pair (key, val) into the table if there is no pair whose
		 * key-part is equal to the input key, or update the pair (key,
		 * old_value) to (key, val) if there is already a pair (key, old_value)
		 * whose key-part is equal to the first input argument.
		 * 
		 * @param key
		 *            the key value of the pair to be searched
		 * @param val
		 *            the new value to put into
		 * @return old_value if there is such a pair with content (key,
		 *         old_value) or null if there is no such pair.
		 */
		public V put(K key, V val) {
			// replace the following code by yours!
			for(Pair a : n){
				if(a.getKey().equals(key)){
					V old = (V) a.getValue();
					a.setValue(val);
					return old;
				}
			}
			n.add(new Pair(key, val));
			return null;
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();

			for (Pair<K, V> pair : toArray()) {
				sb.append(pair.toString() + "\n");
			}

			return sb.toString();

		}

	}

	/**
	 * Implement a class PQueue<E> that manages a list of ordered elements of
	 * type E which implement the Comparable<E> interface. The class is a
	 * priority queue (instead of a stack) providing the following methods:
	 * <ul>
	 * <li>push(E e) : insert a new element e into the queue.
	 * <li>push(E... es) : insert a series of new element es into the queue.
	 * <li>isEmpty() : check if the queue is empty
	 * <li>peek() : return the element e in the queue with largest priority
	 * (i.e, e.compareTo(e') should return negative value for all e' != e.)
	 * <li>pop() : same as peek() but also delete the return element from the
	 * queue.
	 * <li>drain() : successively pop elements form the queue until the queue is
	 * empty and return the popped elements as an array ordered by the time they
	 * are popped.
	 * </ul>
	 * Note: Except that the JVM is run out of memory, your implementation
	 * should not cause the queue to overflow.
	 * 
	 * 
	 *
	 * @param <E>
	 *            The type of elements of the queue
	 */

	public static class PQueue<E extends Comparable<E>> {
		// put required data structure/fields here!!
		
		ArrayList<E> n = new ArrayList<>();

		/**
		 * Construct an empty queue.
		 */
		public PQueue() {
			// put your code here IF NECESSARY!

		}

		public boolean isEmpty() {
			// replace the following code by yours!
			if(n.size() == 0){
				return true;
			}
			else
				return false;
		}

		/**
		 * Insert a new element e into the queue.
		 * 
		 * @param e
		 */
		public void push(E e) {
			// put your code here!
			if(this.isEmpty()){
				n.add(e);
				return;
			}
			else{
				for(int i = 0; i < n.size(); i++){
					if(e.compareTo(n.get(i)) < 0){
						n.add(i, e);
						return;
					}
				}
				n.add(e);
				
			}

		}

		/**
		 * insert a series of new element es into the queue.
		 * 
		 * @param es
		 */
		@SafeVarargs
		final public void push(E... es) {
			// put your code here!
			for(E e : es){
				this.push(e);
			}
			
		}

		/**
		 * Return the element e in the queue with largest priority (i.e,
		 * e.compareTo(e') should return negative value for all e' != e.)
		 * 
		 * @return
		 */
		public E peek() {
			// replace the following code by yours!
			return n.get(0);
		}

		/**
		 * Return the element e in the queue with largest priority and delete
		 * the returned element from the queue as well.
		 * 
		 * @return an element != null .
		 */
		public E pop() {
			// replace the following code by yours!
			return n.remove(0);

		}

		/**
		 * Successively pop elements form the queue until the queue is empty and
		 * return the popped elements as an array list ordered by the time when
		 * they are popped. Note: After this operation, the queue should be
		 * empty unless it is pushed new elements.
		 * 
		 * @return
		 */

		public List<E> drain() {
			// replace the following code by yours!
			ArrayList<E> c = new ArrayList<>();
			
			for(E e : n){
				c.add(e);
			}
			n.clear();
			return c;
		}

	}

	public static class Test {

		int nTests = 0; // each test may produce multiple errors!
		int nErrors = 0;
		int nTestErrors = 0;
		boolean newTest = true;

		void error() {
			error("");
		}

		void error(String s) {
			nErrors++;
			if (newTest) {
				nTestErrors++;
				newTest = false;
				System.out.println(">>> new TestError! <<< ");
			}
			System.out.println("*** error " + nErrors + "*** :" + s);
		}

		void error(String s, Object... args) {
			error(String.format(s, args));
		}

		void test() {
			nTests++;
			newTest = true;
		}

		// static void test(int k) {
		// nTests += k ;
		// }

		String summary() {

			StringBuilder sb = new StringBuilder();
			sb.append("There are totally " + nTests + " full tests! \n");
			sb.append("There are " + nTestErrors + " test errors! \n");
			sb.append("There are " + nErrors + " detailed errors! \n");
			sb.append(" The passing rate is " + (nTests - nTestErrors) + "/" + nTests + "="
					+ ((nTests - nTestErrors) * 100 / nTests) + "!\n");

			int score = (nTests - nTestErrors) * 60 / nTests + 40;

			sb.append("The score is " + score + " provided your source passes compilation!");

			return sb.toString();

		}

		public final void testTable() {
			// fail("Not yet implemented"); // TODO
			test();
			Table<String, Integer> table = new Table<>();
			if (table.size() != 0) {
				error("'new Table<>()' has %d elements while expecting 0 element", table.size());
			}

		}

		public final void testTableTableAA1() {

			test();
			Integer[] ints = new Integer[] { 1, 7, 5, 3, 9 };
			Double[] dbls = new Double[] { 10D, 8D, 6D, 8D, 2D };
			Table<Integer, Double> table = new Table<>(ints, dbls);

			try {
				if (table.get(1) != 10D) {
					error("table.get(1) return %s, while 10.0 is expected", table.get(1));
				}
			} catch (Exception e) {
				error("table.get(1) throw Exception, while 10.0 is expected");
			}

			try {
				if (table.get(7) != 8D) {
					error("table.get(7) return %s, while 8.0 is expected", table.get(1));
				}
			} catch (Exception e) {
				error("table.get(7) throw Exception, while 8.0 is expected");
			}

			try {
				if (table.get(5) != 6D) {
					error("table.get(5) return %s, while 6.0 is expected", table.get(1));
				}
			} catch (Exception e) {
				error("table.get(5) throw Exception, while 6.0 is expected");
			}

			try {
				if (table.get(3) != 8D) {
					error("table.get(3) return %s, while 8.0 is expected", table.get(1));
				}
			} catch (Exception e) {
				error("table.get(3) throw Exception, while 8.0 is expected");
			}

			try {
				if (table.get(9) != 2D) {
					error("table.get(9) return %s, while 2.0 is expected", table.get(1));
				}
			} catch (Exception e) {
				error("table.get(9) throw Exception, while 2.0 is expected");
			}

		}

		public final void testTableTableAA2() {

			test();
			Integer[] ints = new Integer[] { 1, 7, 5, 3, 9 };
			Double[] dbls = new Double[] { 10D, 8D, 6D, 8D, 2D };
			Table<Integer, Double> table = new Table<>(ints, dbls);

			try {
				if (table.get(2) != null) {
					error("table.get(2) return %s, while null is expected", table.get(2));
				}
			} catch (Exception e) {
				error("table.get(2) throws Exception, while null is expected");
			}

			try {
				if (table.get(8) != null) {
					error("table.get(8) return %s, while null is expected", table.get(8));
				}
			} catch (Exception e) {
				error("table.get(8) throws Exception, while null is expected");
			}
		}

		public final void testTableTableKV() {

			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table1 = new Table<>(strs, ints);

			test();

			Table<String, Integer> table = new Table<>(table1);

			try {

				if (table.get("BB") != 10) {
					error("table.get(BB) return %s, while 10 is expected", table.get("BB"));
				}
				if (table.get("AA") != 8) {
					error("table.get(AA) return %s, while 8 is expected", table.get("AA"));
				}
				if (table.get("DD") != 6) {
					error("table.get(DD) return %s, while 6 is expected", table.get("DD"));
				}
				if (table.get("FF") != 8) {
					error("table.get(FF3) return %s, while 8 is expected", table.get("FF"));
				}
				if (table.get("KK") != 2) {
					error("table.get(KK) return %s, while 2 is expected", table.get("KK"));
				}

			} catch (Exception e) {
				error("testTableTableKV() throws an abnormal Exception !");
			}

			test();

			try {

				if (table.get("LL") != null) {
					error("table.get(LL) return %s, while null is expected", table.get("LL"));
				}
				if (table.get("HH") != null) {
					error("table.get(HH) return %s, while null is expected", table.get("HH"));
				}

			} catch (Exception e) {
				error("testTableTableKV() throws an abnormal Exception !");
			}

		}

		public final void testTableTableKV2() {

			Integer[] ints = new Integer[] { 1, 7, 5, 3, 9 };
			Double[] dbls = new Double[] { 10D, 8D, 6D, 8D, 2D };
			Table<Integer, Double> table1 = new Table<>(ints, dbls);

			test();

			Table<Number, Object> table = new Table<>(table1);

			try {

				if (!table.get(1).equals(10D)) {
					error("table.get(1) return %s, while 10D is expected", table.get(1));
				}
				if (!table.get(7).equals(8D)) {
					error("table.get(7) return %s, while 8D is expected", table.get(7));
				}
				if (!table.get(5).equals(6D)) {
					error("table.get(5) return %s, while 6D is expected", table.get(5));
				}
				if (!table.get(3).equals(8D)) {
					error("table.get(3) return %s, while 8D is expected", table.get(3));
				}
				if (!table.get(9).equals(2D)) {
					error("table.get(9) return %s, while 2D is expected", table.get(9));
				}

			} catch (Exception e) {
				error("testTableTableKV2() throws an abnormal Exception !");
			}

			test();

			try {

				if (table.get(8) != null) {
					error("table.get(8) return %s, while null is expected", table.get(8));
				}
				if (table.get(4) != null) {
					error("table.get(4) return %s, while null is expected", table.get(4));
				}
			} catch (Exception e) {
				error("testTableTableKV2() throws an abnormal Exception !");
			}

		}

		public final void testToArray() {

			Integer[] ints = new Integer[] { 1, 7, 5, 3, 9 };
			Double[] dbls = new Double[] { 10D, 8D, 6D, 8D, 2D };
			Table<Integer, Double> table = new Table<>(ints, dbls);

			test();

			Pair<Integer, Double>[] rlt = table.toArray();

			if (!Arrays.deepEquals(ints, keys(rlt))) {
				error("testToArray() generated unexpected result! ");
			}

		}

		public final void testToArray2() {

			String[] strs = { "ab", "cd", "ef", "bk", "hr" };
			Double[] dbls = new Double[] { 10D, 8D, 6D, 8D, 2D };
			Table<String, Double> table = new Table<>(strs, dbls);

			test();

			Pair<String, Double>[] rlt = table.toArray();

			if (!Arrays.deepEquals(strs, keys(rlt))) {
				error("testToArray() generated unexpected result! ");
			}

		}

		public static <K, V> K[] keys(Pair<K, V>[] ps) {

			@SuppressWarnings("unchecked")
			K[] rlt = (K[]) new Object[ps.length];
			for (int c = 0; c < ps.length; c++) {
				rlt[c] = ps[c].getKey();
			}

			return rlt;

		}

		public static <K, V> V[] values(Pair<K, V>[] ps) {

			@SuppressWarnings("unchecked")
			V[] rlt = (V[]) new Object[ps.length];
			for (int c = 0; c < ps.length; c++) {
				rlt[c] = ps[c].getValue();
			}

			return rlt;

		}

		public final void testGet() {

			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			try {

				if (table.get("BB") != 10) {
					error("table.get(BB) return %s, while 10 is expected", table.get("BB"));
				}

				if (table.get("AA") != 8) {
					error("table.get(AA) return %s, while 8 is expected", table.get("AA"));
				}

				if (table.get("DD") != 6D) {
					error("table.get(DD5) return %s, while 6.0 is expected", table.get("DD"));
				}

				if (table.get("FF") != 8D) {
					error("table.get(FF) return %s, while 8.0 is expected", table.get("FF"));
				}

				if (table.get("KK") != 2D) {
					error("table.get(KK) return %s, while 2.0 is expected", table.get("KK"));
				}
			} catch (Exception e) {
				error("Table.get(...) throws Exception while expecting a normal value!");
			}

		}

		public final void testGet2() {

			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			try {

				if (table.get("GG") != null) {
					error("table.get(GG) return %s, while null is expected", table.get("GG"));
				}

				if (table.get("ABC") != null) {
					error("table.get(ABC) return %s, while null is expected", table.get("ABC"));
				}

			} catch (Exception e) {
				error("Table.get(...) throws Exception while expecting a null value!");
			}

		}

		public final void testRemove() {
			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			boolean rlt = table.remove("DD");
			if (rlt == false) {
				error("table.remove(DD) return false, while expectign true");
			}
			if (table.size() != 4) {
				error("table.size return %s after table.remove(DD), while expecting 4", table.size());
			}

		}

		public final void testRemove2() {
			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			boolean rlt = table.remove("JJ");
			if (rlt == true) {
				error("table.remove(JJ) return true, while expecting false");
			}
			if (table.size() != 5) {
				error("table.size return %s after table.remove(JJ), while expecting 5", table.size());
			}

		}

		public final void testPut() {

			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			Integer rlt = table.put("LL", 30);
			if (rlt != null) {
				error("table.put(LL,30) returns %s, while expecting null", rlt);
			}
			if (table.size() != 6) {
				error("tabel.size() return %s after table.put(LL,30), while expecting 6", table.size());
			}

		}

		public final void testPut2() {

			String[] strs = { "BB", "AA", "DD", "FF", "KK" };
			Integer[] ints = { 10, 8, 6, 8, 2 };
			Table<String, Integer> table = new Table<>(strs, ints);

			test();

			try {

				Integer rlt = table.put("FF", 30);
				if (rlt != 8) {
					error("table.put(FF,30) returns %s, while expecting 8", rlt);
				}

				if (table.size() != 5) {
					error("tabel.size() return %s after table.put(FF,30), while expecting 5", table.size());
				}

			} catch (Exception e) {
				error("Table.put(...) throws an Eception, while expecting a normal value! ");
			}

		}

		public void testPQueue() {
			test();

			PQueue<String> q = new PQueue();

			if (q.isEmpty() != true) {
				error("new PQueue().isEmpty() return false, while expecting true");
			}

		}

		public void testIsEmpty() {
			test();

			PQueue<String> q = new PQueue<>();

			if (q.isEmpty() != true) {
				error("new PQueue().isEmpty() return false, while expecting true");
			}

		}

		public void testIsEmpty2() {
			test();

			PQueue<String> q = new PQueue<>();
			q.push("BB");
			q.push("AA");
			q.push("CC");

			if (q.isEmpty() != false) {
				error("q.isEmpty() return true, while expecting false");
			}

		}

		public void testPeek() {

			test();

			try {

				PQueue<String> q = new PQueue<>();
				q.push("BB");
				q.push("AA");
				q.push("DD");
				q.push("CC");

				if (!q.peek().equals("AA")) {
					error("q.peek() return %s, while expecting AA", q.peek());
				}

			} catch (Exception e) {
				error("testPeek() throws an abnormal Exception !");
			}

		}

		public void testPop() {
			test();

			try {
				PQueue<String> q = new PQueue<>();
				q.push("BB");
				q.push("AA");
				q.push("DD");
				q.push("CC");

				String rlt = q.pop();
				if (!rlt.equals("AA")) {
					error("q.pop() return %s, while expecting AA", rlt);
				}

			} catch (Exception e) {
				error("testPop() throws an abnormal Exception !");
			}

		}

		public void testPop2() {
			test();
			try {
				PQueue<String> q = new PQueue<>();
				q.push("DD");
				q.push("BB");
				q.push("AAA");
				q.push("BB");
				q.push("EE");

				String rlt = q.pop();
				if (!rlt.equals("AAA")) {
					error("q.pop() return %s, while expecting AAA", rlt);
				}

				test();
				String rlt2 = q.pop();
				if (!rlt2.equals("BB")) {
					error("q.pop() return %s, while expecting BB", rlt);
				}
			} catch (Exception e) {
				error("testPush2() throws an abnormal Exception !");
			}

		}

		public void testPush() {

			test();

			PQueue<String> q = new PQueue<>();
			q.push("DD");

			try {
				String rlt = q.peek();
				if (!rlt.equals("DD")) {
					error("after q.push(DD), q.peek() return %s, while expecting DD", rlt);
				}
			} catch (Exception e) {
				error("testPush() throws an abnormal Exception !");
			}

			test();

			try {
				q.push("EE");

				String rlt2 = q.peek();
				if (!rlt2.equals("DD")) {
					error("After q.push(EE), q.peek() return %s, while expecting DD", rlt2);
				}
			} catch (Exception e) {
				error("testPush() throws an abnormal Exception !");
			}

			test();
			try {
				q.push("CC");

				String rlt3 = q.peek();
				if (!rlt3.equals("CC")) {
					error("After q.push(CC), q.peek() return %s, while expecting CC", rlt3);
				}
			} catch (Exception e) {
				error("testPush() throws an abnormal Exception !");
			}

		}

		public void testDrain() {

			PQueue<String> q = new PQueue<>();
			q.push("DD");
			q.push("BB");
			q.push("AAA");
			q.push("BB");
			q.push("EE");

			List<String> rlt = q.drain();

			test();

			if (!q.isEmpty()) {
				error("q is not empty after q.drain(), while expecting empty");
			}

			if (!rlt.equals(Arrays.asList(new String[] { "AAA", "BB", "BB", "DD", "EE" }))) {
				error("q.drain() behaves not as expected");
			}

		}

		public void testMinMax() {

			test();

			if (minMax() == null || minMax().length != 0) {
				error("minMax() != {}, while expecting {} ");
			}

			test();
			double[] rlt2 = minMax(5);
			if (!Arrays.equals(rlt2, new double[] { 5D, 5D })) {
				error("minMax(5) != {5d,5d}, while expecting {5d,5d} ");
			}

			test();
			double[] rlt3 = minMax(5L, 20D, 3);
			if (!Arrays.equals(rlt3, new double[] { 3D, 20D })) {
				error("minMax(5L, 20D, 3) != {3D,20D}, while expecting {3d,20d} ");
			}

			test();
			double[] rlt4 = minMax(new BigInteger("200"), 20D, -3L);
			if (!Arrays.equals(rlt4, new double[] { -3D, 200D })) {
				error("minMax(new BigInteger(\"200\"), 20D, -3L) != {-3D,200D}, while expecting {-3d,200d} ");
			}

		}

	}

	public static void mainTest() {


		Test t = new Test();

		// test Table
		t.testTable();
		t.testTableTableAA1();
		t.testTableTableAA2();
		t.testToArray();
		t.testToArray2();

		t.testGet();
		t.testGet2();
		t.testRemove();
		t.testRemove2();

		t.testPut();
		t.testPut2();

		t.testTableTableKV();
		t.testTableTableKV2();

		// test PQueue
		t.testPQueue();
		t.testIsEmpty();
		t.testIsEmpty2();

		t.testPush();
		t.testPeek();
		t.testPop();
		t.testPop2();

		t.testDrain();

		t.testMinMax();

		System.out.println(t.summary());

	}

}
