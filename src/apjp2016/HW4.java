package apjp2016;

import static java.lang.System.out;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HW4 {

	/**
	 * Given an input file named 'inFileName' and a result file named
	 * 'outFileName', read bytes from the input file, convert each byte into its
	 * hexadecimal representation (which is a string of length 2 or 2
	 * characters) and write it to the result file. The named result file must
	 * be created if it does not exist before and its previous content must be
	 * cleared/truncated if it is an existing file.
	 * 
	 * Notes: 1.The content of the result file must also be returned to the
	 * caller as return value. 2. the result content is the empty string "" if
	 * the named input file does not exist. 3. Remember to close files before
	 * return.
	 * 
	 * 
	 * @param inFileName
	 *            : name of an input file
	 * @param outFielName
	 *            : name of the result file.
	 * @return "" if the input file does not exist or the converted hexadecimal
	 *         strings of the bytes read from the input file.
	 * 
	 */

	static String bytes2HexString(String inFileName, String outFileName) {
		// put your code here!
		try{
			Path inPath = Paths.get(inFileName);
			Path outPath = Paths.get(outFileName);
			Files.deleteIfExists(outPath);
			byte[] fileArray;
			fileArray = Files.readAllBytes(inPath);
			OutputStream outfile = Files.newOutputStream(outPath);
			
			char[] outArray = new char[fileArray.length*2];
			//for(byte n : fileArray){
			char[] hexArray = "0123456789ABCDEF".toCharArray();
			for(int i = 0; i < fileArray.length; i++){
				int v = fileArray[i] & 0xFF;
		        outArray[i * 2] = hexArray[v >>> 4];
		        outArray[i * 2 + 1] = hexArray[v & 0x0F];
		    }
			
			String result = "";
			for(char n : outArray){
				outfile.write(n);
				result += n;
				
			}
			outfile.close();

			return result;
		}catch(Exception e){
		// put your code here!
			return "";
		}

	}

	/**
	 * 
	 * @param c
	 *            a hexadecimal digit.
	 * @return decimal value of c.
	 */
	static private int hex2byte(char c) {
		if (c <= '9') {
			return c - '0';
		} else if (c <= 'F') {
			return c - 'A' + 10;
		} else {
			return c - 'a' + 10;
		}
	}

	public static void testBytes2HexString() throws Exception {

		String data = "010203040507071112131415161718" + "F1F2F3F4F5F6F7A8A9AAABACADAEAF";

		String inFileName = "./HW4Data1.in";
		String outFileName = "./HW4Data1.out";

		Path byteFilePath = Paths.get(inFileName);
		Files.deleteIfExists(byteFilePath);

		OutputStream byteout = Files.newOutputStream(byteFilePath);

		char[] dataArray = data.toCharArray();
		byte[] bytes = new byte[dataArray.length / 2];

		for (int k = 0; k < bytes.length; k++) {
			bytes[k] = (byte) (hex2byte(dataArray[k << 1]) * 16 + hex2byte(dataArray[(k << 1) +1]));
		}
		byteout.write(bytes);
		byteout.close();

		String rlt1 = bytes2HexString(inFileName, outFileName);
		if (Files.notExists(Paths.get(outFileName))) {
			out.println("Output file:" + outFileName + " was not created!");
			return;
		}
		String rlt2 = Files.lines(Paths.get(outFileName)).collect(Collectors.joining());
		if (!rlt1.equals(data)) {
			out.println("Incorrect return value from the method bytes2HexString! ");
		}
		if (!rlt2.equals(data)) {
			out.println("Incorrect value wriiten to output from the method bytes2HexString! ");
		}

	}

	public static class Customer implements java.io.Serializable {
		public String name;
		public double bonus;
		public transient int age;
		public java.util.Date birthDay;

		public Customer() {
			this("", 0, new Date());
		};

		public Customer(String name, double bonus, Date birthDay) {
			this.name = name;
			this.bonus = bonus;
			this.birthDay = birthDay;
			age = getAgeFromBirthDay(birthDay);
		};

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Customer)) {
				return false;
			}

			Customer c = (Customer) o;

			return name.equals(c.name) && bonus == c.bonus && birthDay.equals(c.birthDay) && age == c.age;
		}

		/**
		 * Compute the age of a person from his birth day.
		 * 
		 * @return
		 */
		static int getAgeFromBirthDay(Date birthDay) {

			Date now = new Date();

			return (int) ((now.getTime() - birthDay.getTime()) / (1000 * 3600 * 24 * 365));

		}

		/**
		 * Implement this method to customize the serialization of this object
		 * so that all fields but the age field can be recovered from
		 * defaultReadObject() of objIn, and the age field need be recovered by
		 * computing the difference between current time and birthday date.
		 * 
		 * @param objIn
		 * @throws Exception
		 * 
		 */
		private void readObject(ObjectInputStream objIn) throws Exception {
			// add your code here to recover object written to objIn previously.
			objIn.defaultReadObject();
			Date now = new Date();
			this.age = (int) ((now.getTime() - birthDay.getTime()) / (1000 * 3600 * 24 * 365));

            return  ;
		}

		/**
		 * This method in fact is not needed since it has the same behavior as
		 * the default writeObejct method.
		 * 
		 * @param objOut
		 * @throws IOException
		 */
		private void writeObject(ObjectOutputStream objOut) throws IOException {

			objOut.defaultWriteObject();

		}

	}

	@SuppressWarnings("deprecation")
	public static void testCustomer() throws Exception {

		ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("HW4Data2"));

		Customer[] customers = { new Customer("Chen", 100, new Date(2000-1900, 11, 20)),
				new Customer("Chang", 500, new Date(1990-1900, 10, 1)), new Customer("Chen", 300, new Date(2010-1900, 1, 2)),
				new Customer("Chen", 400, new Date(1965-1900, 11, 11)), new Customer("Chen", 100, new Date(2005-1900, 3, 29)) };

		for (Customer c : customers) {
			objOut.writeObject(c);
		}

		objOut.close();

		ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("HW4Data2"));

		List<Customer> cs2 = new ArrayList<>();

		try {
			while (true) {
				Customer c = (Customer) objIn.readObject();
				out.println(c);
				cs2.add(c);
			}
		} catch (EOFException eof) {
		} finally {
			objIn.close();
		}

		Customer[] customers2 = cs2.toArray(new Customer[customers.length]);

		if (!Arrays.equals(customers, customers2)) {
			out.println("Incorrect Customer.readObject()");
		}

	}

	public static void main(String[] args) throws Exception {

		testBytes2HexString();

		testCustomer();

	}

}
