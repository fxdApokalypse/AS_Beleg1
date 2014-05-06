package org.yvka.Beleg1.utils;

import java.util.StringJoiner;
import java.util.regex.Pattern;

public class StringUtil {
	
   public static final String LINE_SEPERATOR = System.lineSeparator();
	
   public static String wrapHorizontalBorders(String content, String title) {
	   StringBuilder str = new StringBuilder();
	   String label =  title + " = ";
	   String indent = createLine(label.length(), ' ');
	   String[] lines = content.split(LINE_SEPERATOR);
	   
	   for(int i = 0; i < lines.length; i++) {
		   lines[i] = (i == 0 ? label : indent) + "|" + lines[i] + "|";
		   str.append(lines[i]).append(LINE_SEPERATOR);
	   }
	 
	  return  str.toString();
   }
   
	
   public static String wrapTopBottomBorders(String content, String title) {
		
		int indexOfFirstLineSpearator = content.indexOf(LINE_SEPERATOR);
		int sizeOfFirstLine = indexOfFirstLineSpearator >= 0 ? indexOfFirstLineSpearator + 1 : content.length();
		
		if(sizeOfFirstLine < (title.length() + 4)) {
			sizeOfFirstLine = title.length() + 4 + 10;
		}
		
		StringBuilder str = new StringBuilder();
		str.append(createLine(title.length() + 4, '_'));
		str.append(LINE_SEPERATOR);
		str.append("| " + title + " |");
		str.append(LINE_SEPERATOR);
		str.append(createLine(sizeOfFirstLine));
		str.append(LINE_SEPERATOR);
		str.append(content);
		str.append(createLine(sizeOfFirstLine));
		return str.toString();
	}
	
	public static String createLine(int length) {
		return createLine(length, '-');
	}
	
	public static String createLine(int length, char lineElement) {
		if(length <= 0) {
			throw new IllegalArgumentException("Invalid length: length must be > 0");
		}
		StringBuilder str = new StringBuilder();
		while(--length >= 0) str.append(lineElement);
		return str.toString();
	}
}
