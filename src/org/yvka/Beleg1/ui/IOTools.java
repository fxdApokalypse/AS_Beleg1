package org.yvka.Beleg1.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class IOTools {

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	private static StringTokenizer input;
	private static String string;

	
	public static int readNumberInRange(String prompt, int min, int max ) {
		int size = 0; 
		boolean isVaidInput = false;
		while(!isVaidInput) {
			size = IOTools.readInteger(
				String.format("%s [%d-%d] : ",prompt, min, max)
			);
			isVaidInput = size >= min && size <= max;
			if(!isVaidInput) {
				System.out.println("The specified size is out of the range [1-7].");
				System.out.println("Please repeat the input ...");
			}
		}
		return size;
	}
	
	public static void flush() {
		input = null;
	}

	private static void init() {
		string = null;
		if ((input != null) && (input.hasMoreTokens()))
			return;
		while ((input == null) || (!input.hasMoreTokens())) {
			input = new StringTokenizer(readLine());
		}
	}

	private static void error(Exception paramException, String paramString) {
		System.out.println("Input error " + paramException);
		System.out.println("Please repeat the input ...");
		System.out.print(paramString);
	}

	public static String readLine(String paramString) {
		flush();
		String str = "";
		System.out.print(paramString);
		try {
			str = in.readLine();
		} catch (IOException localIOException) {
			System.err.println(localIOException
					+ "\n Program terminated...\n");
			System.exit(1);
		}
		if (str == null) {
			System.err
					.println("Reached end of file.\nProgram terminated...\n");
			System.exit(1);
		}
		return str;
	}

	public static int readInteger(String paramString) {
		System.out.print(paramString);
		init();
		for (;;) {
			try {
				return Integer.parseInt(input.nextToken());
			} catch (NumberFormatException localNumberFormatException) {
				error(localNumberFormatException, paramString);
			}
			init();
		}
	}

	public static long readLong(String paramString) {
		System.out.print(paramString);
		init();
		for (;;) {
			try {
				return Long.parseLong(input.nextToken());
			} catch (NumberFormatException localNumberFormatException) {
				error(localNumberFormatException, paramString);
			}
			init();
		}
	}

	public static double readDouble(String paramString) {
		System.out.print(paramString);
		init();
		for (;;) {
			try {
				return Double.valueOf(input.nextToken()).doubleValue();
			} catch (NumberFormatException localNumberFormatException) {
				error(localNumberFormatException, paramString);
			}
			init();
		}
	}

	public static float readFloat(String paramString) {
		System.out.print(paramString);
		init();
		for (;;) {
			try {
				return Float.valueOf(input.nextToken()).floatValue();
			} catch (NumberFormatException localNumberFormatException) {
				error(localNumberFormatException, paramString);
			}
			init();
		}
	}

	public static short readShort(String paramString) {
		System.out.print(paramString);
		init();
		for (;;) {
			try {
				return Short.valueOf(input.nextToken()).shortValue();
			} catch (NumberFormatException localNumberFormatException) {
				error(localNumberFormatException, paramString);
			}
			init();
		}
	}

	public static boolean readBoolean(String paramString) {
		String str = readString(paramString);
		while ((!str.equals("true")) && (!str.equals("false")))
			str = readString();
		return str.equals("true");
	}

	public static String readString(String paramString) {
		System.out.print(paramString);
		init();
		return input.nextToken();
	}

	public static char readChar(String paramString) {
		System.out.print(paramString);
		if ((string == null) || (string.length() == 0))
			string = readString("");
		char c = string.charAt(0);
		string = string.length() > 1 ? string.substring(1)
				: null;
		return c;
	}

	public static String readLine() {
		return readLine("");
	}

	public static int readInteger() {
		return readInteger("");
	}

	public static long readLong() {
		return readLong("");
	}

	public static double readDouble() {
		return readDouble("");
	}

	public static short readShort() {
		return readShort("");
	}

	public static float readFloat() {
		return readFloat("");
	}

	public static char readChar() {
		return readChar("");
	}

	public static String readString() {
		return readString("");
	}

	public static boolean readBoolean() {
		return readBoolean("");
	}

	public static String toString(double paramDouble) {
		if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
			return String.valueOf(paramDouble);
		return new BigDecimal(paramDouble).toString();
	}
}
