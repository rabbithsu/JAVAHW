package apjp2016;


import java.util.Arrays;
import java.util.EnumSet;

import apjp2016.HW1.BasicColor;

import static apjp2016.HW1.BasicColor.* ;

public class HW1 {

	/**
	 * It is well known that a color may be represented as a composition of 3
	 * primary colors: (red, green,blue). Suppose each primary color component
	 * is represented as a value between 0 and 255. So, for example, the white
	 * color is composed of (red,green,blue)=(255,255,255) and represented
	 * compactly as an int (red << 24 + green << 16 + blue << 8), and CYAN is
	 * composed of (red,green, blue) = (0, 255, 255) or as a compact int (0<<24
	 * + 255<< 16 + 255<< 8).
	 * 
	 * This homework requires you to define an enumeration type called
	 * BasicColor for the eight combinations of primary colors: BLACK, RED,
	 * BLUE, GREEN, CYAN, MAGENTA, YELLOW, and WHITE, each represented
	 * internally compactly as an int. In addition, you are required to
	 * implement also miscellaneous methods documented in the body.
	 *
	 * 
	 */

	public static enum BasicColor {
		// BLACK(?), RED(?), BLUE(), GREEN(), CYAN(), MAGENTA(), YELLOW(),
		// WHITE(),;
		// replace the following code with yours!
		BLACK(0, 0, 0), BLUE(0, 0, 255), GREEN(0, 255, 0), CYAN(0, 255, 255), RED(255, 0, 0), MAGENTA(255, 0,
				255), YELLOW(255, 255, 0), WHITE(255, 255, 255);

		// rgb = r<<24 + g << 16 + b << 8 where r,g and b are an integer in [0,
		// 255]
		// note: since we use the left 8 bits of an int to represent the red
		// value, the
		// internal color value would be negative if the basic color contains
		// red(=255) color.
		private int rgb;

		private static final int NO_PRIMARY_COLORS = 8;

		private BasicColor(int r, int g, int b) {
			// Replace the following code by yours!
			rgb = r<<24 | g<<16 | b<<8;
			
			
			
			// Don't change following code!
			if (r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0) {
				throw new IllegalArgumentException();
			}

			// Don't change following code!
			if (this.ordinal() >= NO_PRIMARY_COLORS) {
				throw new RuntimeException("Could not create duplicated BasicColor!");
			}

		}

		/**
		 * Find the red component of this color. The result is either 0 or 255.
		 */
		public int getRed() {
			// replace the following code by yours!
			if(this.rgb >>> 24 > 0)
				return 255;
			else
				return 0;
		}

		/**
		 * Find the green component of this color. The result is either 0 or
		 * 255.
		 */
		public int getGreen() {
			// replace the following code by yours!
			if(rgb << 8 >>>24 > 0)
				return 255;
			else
				return 0;
		}

		/**
		 * Find the blue component of this color. The result is either 0 or 255.
		 */
		public int getBlue() {
			// replace the following code by yours!
			if(rgb << 16 >>>24 > 0)
				return 255;
			else
				return 0;
		}

		/**
		 * get the internal rgb int of this color for the purpose of test.
		 * 
		 * @return
		 */
		public int getRGB() {
			return rgb;
		}

		/**
		 * Get the complement color of this color. For instance,
		 * WHITE.complement() == BLACK.
		 * 
		 * @return the complement color of this color.
		 */
		public BasicColor complement() {
			// replace the following code by yours!
			int cRGB = ~this.rgb;
			return getColor(cRGB >>> 24, cRGB << 8 >>>24 , cRGB << 16 >>> 24);
		}

		/**
		 * Find the resulting BasicColor consisting of primary colors common to
		 * both this and input color c.
		 * 
		 * @param c
		 *            another BasicColor
		 * @return
		 */
		public BasicColor common(BasicColor c) {
			// replace follow code by yours.
			int cRGB = this.rgb & c.rgb;
			return getColor(cRGB >>> 24, cRGB << 8 >>>24 , cRGB << 16 >>> 24);

		}

		/**
		 * Find the resulting BasicColor consisting of primary colors contained
		 * in one and only one of both colors.
		 * 
		 * @param c
		 *            another BasicColor
		 * @return
		 */
		public BasicColor exclusiveOr(BasicColor c) {
			// replace follow code by yours.
			int cRGB = this.rgb ^ c.rgb;
			return getColor(cRGB >>> 24, cRGB << 8 >>>24 , cRGB << 16 >>> 24);
		}

		/**
		 * What will a color become if it is mixed with another color ?
		 * 
		 * @param c
		 *            a BasicColor
		 * @return
		 */
		public BasicColor mixedWith(BasicColor c) {
			// replace follow code by yours.
			int cRGB = this.rgb | c.rgb;
			return getColor(cRGB >>> 24, cRGB << 8 >>>24 , cRGB << 16 >>> 24);
		}

		/**
		 * Find the resulting BasicColor by combining all BasicColors given in
		 * the argument list. Return BLACK if there is no argument in the
		 * argument list.
		 * 
		 * @param colors
		 *            a BasicColor argument list
		 * @return
		 */
		public static BasicColor combine(BasicColor... colors) {
			// replace follow code by yours.
			int cRGB = 0;
			if(colors.length == 0){
				return BLACK;
			}
			for(BasicColor n : colors){
				cRGB = cRGB | n.rgb;
			}
			return getColor(cRGB >>> 24, cRGB << 8 >>>24 , cRGB << 16 >>> 24);

		}
		
		
		/**
		   * Find a BasicColor whose primary color composition is as the given input arguments.
		   * Return null if there is no BasicColor with the given composition.
		   * @param r
		   * @param g
		   * @param b
		   * @return 
		   */
		  public static BasicColor getColor(int r, int g, int b){
				// replace follow code by yours.
			  	for(BasicColor n: BasicColor.values()){
			  		if(n.getRed()==r && n.getGreen() == g && n.getBlue() == b)
			  			return n;
			  	}
			  	
				return null;
			 
		  }

		/**
		 * Return an array containing all BasicColors sorted in ascending order
		 * according to the priority of composing primary colors as specified in
		 * the input. For example, if invoking sort(BLUE, RED, GREEN), then the
		 * resulting array would have the following color priority: (BLUE first
		 * , then Red and finally GREEN) and the result would be:
		 * (red=0,green=0, blue=0), (0,255,0), (255,0,0), ... (255,255,255).
		 * 
		 * @param p1,p2,p3
		 *            is a permutation of RED, GREEN and BLUE.
		 * @return
		 */
		public static BasicColor[] sort(BasicColor p1, BasicColor p2, BasicColor p3) {
			if (!EnumSet.of(p1, p2, p3).equals(EnumSet.of(RED, GREEN, BLUE))) {
				throw new IllegalArgumentException();
			}

			// Replace following code by yours
            return null ;

		}

		/**
		 * Represent a color array as a string.
		 * 
		 * @param colors
		 * @return
		 */
		public static String toString(BasicColor... colors) {
			if (colors == null) return "null";
			StringBuffer sb = new StringBuffer();
			for (int k = 0; k < colors.length; k++) {
				if (k > 0)
					sb.append(",");
				sb.append(colors[k]);
			}
			return sb.toString();
		}
		
		/**
		 * Represent a color array as a rgb.
		 * 
		 * @param colors
		 * @return
		 */
		public static String toRGB(BasicColor... colors) {
			if (colors == null) return "null";
			StringBuffer sb = new StringBuffer();
			for (int k = 0; k < colors.length; k++) {
				if (k > 0)
					sb.append(",");
				sb.append(String.format("(%s,%s,%s)", colors[k].getRed(), colors[k].getGreen(), colors[k].getBlue()));
			}
			return sb.toString();
		}
		
		

	}

	public static void main(String... args) {

		// write your own test here!
		System.out.println(Integer.toBinaryString(255<<24));
		System.out.println(Integer.toBinaryString(255<<24>>>24));
		
		
		 
		System.out.println("=====Begin of Test======");
		// test with tests given by the instructor!
		mainTest(args);
		System.out.println("=====End of Test=========");
		System.out.println(summary());
	}

	public static void mainTest(String... args) {
		
		HW1 hw11 = new HW1();		
		System.out.println(BasicColor.toString(BasicColor.values()));
		
		hw11.testGetRed();
		hw11.testGetGreen();
		hw11.testGetBlue();
		hw11.testComplement();		 
	    hw11.testCommon();
		hw11.testExclusiveOr();
		hw11.testMixedWith() ;
		hw11.testCombine();
		hw11.testGetColor();
		hw11.testSort1();
		hw11.testSort2();

	}

	

	static int nTests = 0;   // each test may produce multiple errors!
	static int nErrors = 0;
	static int nTestErrors = 0;
	static boolean newTest = true;

	static void error() {
		error("");
	}

	static void error(String s) {
		nErrors++;
		if(newTest){
			nTestErrors ++ ;
			newTest = false;
			System.out.println(">>> new TestError! <<< ");
		}
		System.out.println("*** error " + nErrors + "*** :" + s);
	}
	
	static void error(String s, Object ... args){
		error( String.format(s, args));
	}

	static void test() {
		nTests++;
		newTest = true ;
	}
	
//	static void test(int k) {
//		nTests += k ;
//	}

	static String summary() {

		StringBuilder sb = new StringBuilder();
		sb.append("There are totally " + nTests + " full tests! \n");
		sb.append("There are " + nTestErrors + " test errors! \n");
		sb.append("There are " + nErrors + " detailed errors! \n");
		sb.append(" The passing rate is " + (nTests - nTestErrors) + "/" + nTests + "="
				+ ((nTests - nTestErrors) * 100 / nTests) + "!\n");

		int score = (nTests - nTestErrors) * 50 / nTests + 50 ;

		sb.append("The score is " + score + " provided your source passes compilation!");

		return sb.toString();

	}

	
	public final void testGetRed() {

		test();
		int[] rlt = new int[] { 0, 0, 0, 0, 255, 255, 255, 255 };
		BasicColor[] cs = BasicColor.values();
		for (int k = 0; k < BasicColor.values().length; k++) {
			if (rlt[k] != cs[k].getRed()) {
				error("the red value: " + cs[k].getRed() + " of " + cs[k] + "is not correct!");
			}
		}
	}

	
	public final void testGetGreen() {
		test();
		int[] rlt = new int[] { 0, 0, 255, 255, 0, 0, 255, 255 };
		BasicColor[] cs = BasicColor.values();
		for (int k = 0; k < BasicColor.values().length; k++) {
			if (rlt[k] != cs[k].getGreen()) {
				error("the green value: " + cs[k].getGreen() + " of " + cs[k] + "is not correct!");
			}
		}
	}

	
	public final void testGetBlue() {
		test();
		int[] rlt = new int[] { 0, 255, 0, 255, 0, 255, 0, 255 };
		BasicColor[] cs = BasicColor.values();
		for (int k = 0; k < BasicColor.values().length; k++) {
			if (rlt[k] != cs[k].getBlue()) {
				error("the blue value: " + cs[k].getBlue() + " of " + cs[k] + "is not correct!");
			}
		}
	}

	
	public final void testComplement() {
		test();
		BasicColor[] rlt = BasicColor.values();
		// reverse array!
		for (int l = 0, r = rlt.length - 1; l < r; l++, r--) {
			BasicColor tmp = rlt[l];
			rlt[l] = rlt[r];
			rlt[r] = tmp;
		}

		BasicColor[] cs = BasicColor.values(); // get another array
		for (int k = 0; k < BasicColor.values().length; k++) {
			if (rlt[k] != cs[k].complement()) {
				error("the complement Color: " + cs[k].complement() + " of " + cs[k] + "is not correct!");
			}
		}
	}

	
	public final void testCommon() {
		
		BasicColor[][] answer = new BasicColor[][]{			
			{ BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK},
			{ BLACK,BLUE,BLACK,BLUE,BLACK,BLUE,BLACK,BLUE},
			{ BLACK,BLACK,GREEN,GREEN,BLACK,BLACK,GREEN,GREEN},
			{ BLACK,BLUE,GREEN,CYAN,BLACK,BLUE,GREEN,CYAN},
			{ BLACK,BLACK,BLACK,BLACK,RED,RED,RED,RED},
			{ BLACK,BLUE,BLACK,BLUE,RED,MAGENTA,RED,MAGENTA},
			{ BLACK,BLACK,GREEN,GREEN,RED,RED,YELLOW,YELLOW},
			{ BLACK,BLUE,GREEN,CYAN,RED,MAGENTA,YELLOW,WHITE}			
		};
		
		test();
		BasicColor[] colors = BasicColor.values();
		BasicColor[][] rlt = new BasicColor[8][8];
			
		
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				rlt[k][j] = colors[k].common(colors[j]);
				if(rlt[k][j]!= answer[k][j]){
					error("The return value of " + colors[k] + ".common(" + colors[j] +") = " + rlt[k][j] + " is not the expected "+
				     answer[k][j]) ;
					
				}
			}
		}
		
	}

	

	
	public final void testExclusiveOr() {
		BasicColor[][] answer = 
		{
		{BLACK,BLUE,GREEN,CYAN,RED,MAGENTA,YELLOW,WHITE},
		{BLUE,BLACK,CYAN,GREEN,MAGENTA,RED,WHITE,YELLOW},
		{GREEN,CYAN,BLACK,BLUE,YELLOW,WHITE,RED,MAGENTA},
		{CYAN,GREEN,BLUE,BLACK,WHITE,YELLOW,MAGENTA,RED},
		{RED,MAGENTA,YELLOW,WHITE,BLACK,BLUE,GREEN,CYAN},
		{MAGENTA,RED,WHITE,YELLOW,BLUE,BLACK,CYAN,GREEN},
		{YELLOW,WHITE,RED,MAGENTA,GREEN,CYAN,BLACK,BLUE},
		{WHITE,YELLOW,MAGENTA,RED,CYAN,GREEN,BLUE,BLACK} } ;
		
		test();
		BasicColor[] colors = BasicColor.values();
		BasicColor[][] rlt = new BasicColor[8][8];
			
		
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				rlt[k][j] = colors[k].exclusiveOr(colors[j]);
				if(rlt[k][j]!= answer[k][j]){
					error("The return value of " + colors[k] +".exclusiveOr(" + colors[j] +") = " + rlt[k][j] + " is not the expected "+
				    answer[k][j]) ;
					
				}
			}
		}	
		
	}

	
	public final void testMixedWith() {
		BasicColor[][] answer = {		
		{BLACK,BLUE,GREEN,CYAN,RED,MAGENTA,YELLOW,WHITE},
		{BLUE,BLUE,CYAN,CYAN,MAGENTA,MAGENTA,WHITE,WHITE},
		{GREEN,CYAN,GREEN,CYAN,YELLOW,WHITE,YELLOW,WHITE},
		{CYAN,CYAN,CYAN,CYAN,WHITE,WHITE,WHITE,WHITE},
		{RED,MAGENTA,YELLOW,WHITE,RED,MAGENTA,YELLOW,WHITE},
		{MAGENTA,MAGENTA,WHITE,WHITE,MAGENTA,MAGENTA,WHITE,WHITE},
		{YELLOW,WHITE,YELLOW,WHITE,YELLOW,WHITE,YELLOW,WHITE},
		{WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE}} ;
		
		test();
		BasicColor[] colors = BasicColor.values();
		BasicColor[][] rlt = new BasicColor[8][8];
			
		
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < 8; j++) {
				rlt[k][j] = colors[k].mixedWith(colors[j]);
				if(rlt[k][j]!= answer[k][j]){
					error("The return value of " + colors[k] +".mixedWith(" + colors[j] +") = " + rlt[k][j] + " is not the expected "+
				    answer[k][j]) ;
					
				}
			}
		}
		
		
		
	}

	
	public final void testCombine() {
       test() ;
       
       if(combine(RED, BLUE, GREEN) != WHITE) {
    	   error("Error on testing combine()!") ;
       }       
       
       if(combine(RED, BLACK, MAGENTA) != MAGENTA) {
    	   error("Error on testing combine()!") ;
       }
       
       if(combine(BLUE,CYAN, GREEN) != CYAN) {
    	   error("Error on testing combine()!") ;
       }
		

	}
	
	public final void testGetColor() {
		
		test();
		
		BasicColor[] answer = {BLACK, BLUE, GREEN, CYAN,  RED, MAGENTA, YELLOW, WHITE };
		int[][] input = new int[][]{
			{0,0,0}, {0,0,255}, {0,255,0}, {0,255,255}, 
			{255,0,0},{255,0,255},{255,255,0}, {255,255,255} 
		} ;
	
		for(int c = 0; c< 8; c++){
			BasicColor rlt = getColor(input[c][0], input[c][1], input[c][2]) ;
			if( rlt != answer[c]){
				error("Fail to pass testing of getColor(%s, %s, %s): result is %s, expecting %s", input[c][0], input[c][1], input[c][2], rlt, answer[c] ) ;
			}
		}
		
	}

	
	public final void testSort1() {
		
	 test() ;
	 
	 BasicColor[] answer = {BLACK, BLUE, GREEN, CYAN,  RED, MAGENTA, YELLOW, WHITE };
	 
	 BasicColor[] rlt = BasicColor.sort(RED,  GREEN,  BLUE) ;
	 
	 if(! Arrays.equals(answer, rlt) ){
		 error("Fail to pass test of testSort1!"); 
		 System.out.println(BasicColor.toString(rlt)) ;
		 System.out.println(BasicColor.toRGB(rlt)) ;
	 }
	}
	 
	 public final void testSort2() {
			
		 test() ;
		 
		 int[][] ans = new int[][]{
				{0,0,0}, {255,0,0}, {0,0,255}, {255,0,255}, 
				{0,255,0}, {255,255,0}, {0,255,255}, {255,255,255}
			} ;
			BasicColor[] answer = new BasicColor[8] ;
			for(int k = 0; k < 8; k++){
				answer[k] = getColor(ans[k][0], ans[k][1], ans[k][2]) ;
			}
			
			answer = new BasicColor[] { BLACK,RED,BLUE,MAGENTA,GREEN,YELLOW,CYAN,WHITE } ;
			
			System.out.println(BasicColor.toString(answer)) ;
		 
		 
		 BasicColor[] rlt = BasicColor.sort(GREEN,  BLUE, RED) ;
		
			 
		 
		 if(! Arrays.equals(answer, rlt)){
			 error("Fail to pass test of testSort2!") ;
			 System.out.println(BasicColor.toString(rlt)) ;
			 System.out.println(BasicColor.toRGB(rlt)) ;
		 } 
		
	}	

}
