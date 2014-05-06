package org.yvka.Beleg1.interpreter;
/*
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. CHUCK MCMANIS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT
 * OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */



import java.util.ArrayList;

import org.yvka.Beleg1.data.MatrixImpl;


/**
 * This class parses the keywords and symbols out of a line of BASIC
 * code (or command line) and returns them as tokens. Each tokenizer
 * maintains state on where it is in the process.
 */
class LexicalTokenizer {

    int currentPos = 0;
    int previousPos = 0;
    int markPos = 0;
    char buffer[];
    boolean isParsingMatrix = false;

    public LexicalTokenizer(char data[]) {
        buffer = data;
        currentPos = 0;
    }

    /**
     * Returns true if there are more tokens to be returned.
     */
    boolean hasMoreTokens() {
        return currentPos < buffer.length;
    }

    /**
     * Set's the current "mark" so that the line can be re-parsed
     * from this point.
     */
    void mark() {
        markPos = currentPos;
    }

    /**
     * Reset the line pointer to the mark for reparsing.
     */
    void resetToMark() {
        currentPos = markPos;
    }

    /**
     * Reset the tokenizer with a new data buffer.
     */
    void reset(char buf[]) {
        buffer = buf;
        currentPos = 0;
    }

    /**
     * Reset the current buffer to zero.
     */
    void reset() {
        currentPos = 0;
    }

    /**
     * Reset the tokenizer by first filling its data buffer with the
     * passed in string, then reset the mark to zero for parsing.
     */
    void reset(String x) {
        int l = x.length();
        for (int i = 0; i < l; i++) {
            buffer[i] = x.charAt(i);
        }
        buffer[l] = '\n'; // mark the end of the line.
        currentPos = 0;
    }

    /**
     * Given that there has been an error, return the string in the buffer
     * and a line of dashes (-) followed by a caret (^) at the current position.
     * This indicates where the tokenizer was when the error occured.
     */
    String showError() {
        int errorPos = previousPos;
        currentPos = 0;
        String txt = asString();
        StringBuffer sb = new StringBuffer();
        sb.append(txt+"\n");
        for (int i = 0; i < errorPos; i++) {
            sb.append('-');
        }
        sb.append('^');
        return sb.toString();
    }

    /**
     * Give back the last token, basically a reset to this token's start. This
     * function is used extensively by the parser to "peek" ahead in the input
     * stream.
     */
    void unGetToken() {
        if (currentPos != previousPos) {
            currentPos = previousPos;
        }
    }
    
    /**
     * This method will attempt to parse out a matrix constant.
     * A matrix constand descripes the form:
     * 		[[1.2 1.3 1.4 1.5] [1.2 1.3 1.4 1.5]]
     * Where '[' descripes the beginning of a matrix and ']' the end of a matrix.
     * @return
     */
    private Token parseMatrixConstant() {
    	Token token = null;		
    	ArrayList<ArrayList<Double>> matrix = new ArrayList<>(); 
    	int row = -1;
    	int openedBracked = 1;
    	currentPos++;
    	// read Numbers
    	while(hasMoreTokens() && openedBracked != 0) {
    		token = nextToken();   	    		
    		if(token.isSymbol('[')){
    			openedBracked++;
    			if(openedBracked > 2) {
    				return new Token(Token.Type.ERROR, "Parsing error a mastrix can only be 2 dimensional."); 
    			}
    			matrix.add(new ArrayList<>());
    			row++;
    			continue;
    		}
    		
    		if(token.isSymbol(']')){
    			openedBracked--;
    			continue;
    		}
    		
    		if(!token.isType(Token.Type.NUMERIC_CONSTANT)) {
    			return new Token(Token.Type.ERROR, "Illegal matrix element seperator.");
    		}
    		
    		matrix.get(row).add(token.getDoubleValue());
    	}
    	
    	if(openedBracked != 0) {
    		return new Token(Token.Type.ERROR, "Missing brackets");
    	} 
    	
    	
    	double [][] matrixArray = new double[matrix.size()][matrix.size() > 0 ? matrix.get(0).size() : 0]; 
    	for(int i = 0; i < matrix.size(); i++) {
    		for(int j = 0; j < matrix.get(i).size(); j++) {
    			matrixArray[i][j] = matrix.get(i).get(j); // need to unboxing Double to double
    		}
    	}
    	
    	return new Token(Token.Type.MATRIX_CONSTANT, new MatrixImpl(matrixArray));
	}
    
    /**
     * This method will attempt to parse out a numeric constant.
     * A numeric constant satisfies the form:
     *      999.888e777
     * where '999' is the optional integral part.
     * where '888' is the optional fractional part.
     * and '777' is the optional exponential part.
     * The '.' and 'E' are required if the fractional or exponential
     * part are present, there can be no internal spaces in the number.
     * Note that unary minuses are always stripped as a symbol.
     *
     * Also note that until the second character is read .5 and .and.
     * appear to start similarly.
     */
    Token parseNumericConstant() {
        double m = 0;   // Mantissa
        double f = 0;   // Fractional component
        int oldPos = currentPos; // save our place.
        boolean wasNeg = false;
        boolean isConstant = false;
        Token r = null;

        // Look for the integral part.
        while (isDigit(buffer[currentPos])) {
            isConstant = true;
            m = (m*10.0) + (buffer[currentPos++] - '0');
        }

        // Now look for the fractional part.
        if (buffer[currentPos] == '.') {
            currentPos++;
            double t = .1;
            while (isDigit(buffer[currentPos])) {
                isConstant = true;
                f = f + (t * (buffer[currentPos++] - '0'));
                t = t/10.0;
            }
        }

        m = (m + f);
        /*
         * If we parse no mantissa and no fractional digits, it can't be a
         * numeric constant now can it?
         */
        if (! isConstant) {
            currentPos = oldPos;
            return null;
        }

        // so it was a number, perhaps we are done with it.
        if ((buffer[currentPos] != 'E') && (buffer[currentPos] != 'e'))
            return new Token(Token.Type.NUMERIC_CONSTANT, m); // no exponent return value.

        currentPos++; // skip over the 'e'

        int p = 0;
        double e;
        wasNeg = false;

        // check for negative exponent.
        if (buffer[currentPos] == '-') {
            wasNeg = true;
            currentPos++;
        } else if (buffer[currentPos] == '+') {
            currentPos++;
        }

        while (isDigit(buffer[currentPos])) {
            p = (p * 10) + (buffer[currentPos++] - '0');
        }

        try {
            e = Math.pow(10, (double)p);
        } catch (ArithmeticException zzz) {
            return new Token(Token.Type.ERROR, "Illegal numeric constant.");
        }

        if (wasNeg)
            e = 1/e;
        return new Token(Token.Type.NUMERIC_CONSTANT, (m+f) * e);
    }

    /** return true if char is between a-z or A=Z */
    static boolean isLetter(char c) {
        return (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')));
    }

    /** Return true if char is between 0 and 9 */
    static boolean isDigit(char c) {
        return ((c >= '0') && (c <= '9'));
    }

    /** Return true if  char is whitespace. */
    static boolean isSpace(char c) {
        return ((c == ' ') || (c == '\t'));
    }

    // we just keep this around 'cuz we return it a lot.
    Token EOLToken = new Token(Token.Type.EOL, 0);

    /**
     * This is the meat of this class, return the "next" token from
     * the current tokenizer buffer. If the token isn't recognized an
     * ERROR token will be returned.
     */
    Token nextToken() {
        Token r;
        // if we recurse then we need to know what the position was
        int savePos = currentPos;

        /*
         * Always return a token, even if it is just EOL
         */
        if (currentPos >= buffer.length)
            return EOLToken;

        /*
         * Save our previous position for unGetToken() to work.
         */
        previousPos = currentPos;

        /*
         * eat white space.
         */
        while (isSpace(buffer[currentPos]))
            currentPos++;

        /*
         * Start by checking all of the special characters.
         */
        switch (buffer[currentPos]) {

            // Various lexical symbols that have meaning.
            case '+' :
                currentPos++;
                return new Token(Token.Type.OPERATOR, "+", Expression.Operation.ADD);

            case '-' :
                currentPos++;
                return new Token(Token.Type.OPERATOR, "-", Expression.Operation.SUB);

            case '*' :
                currentPos++;
                return new Token(Token.Type.OPERATOR, "*", Expression.Operation.MUL);

            case '/' :
                currentPos++;
                return new Token(Token.Type.OPERATOR, "/", Expression.Operation.MUL);
    
            case '[' :
            	if(!isParsingMatrix) {
            		isParsingMatrix = true;
            		Token token = parseMatrixConstant();
            		isParsingMatrix = false;
            		return token;
            	}
            case ']' :
            case '(' :
            case '\'':
            case '?' :
            case ')' :
            case ':' :
            case ';' :
            case ',' :
            case '.' :
                return new Token(Token.Type.SYMBOL, (double) buffer[currentPos++]);
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                r = parseNumericConstant();
                if (r != null)
                    return r;
                return new Token(Token.Type.SYMBOL, (double) buffer[currentPos++]);
            // process EOL characters. (dump <CR><LF> as just EOL)
            case '\r' :
            case '\n' :
                while (currentPos < buffer.length)
                    currentPos++;
                return EOLToken;

            // text enclosed in "quotes" is a string constant.
        }    
        if (! isLetter(buffer[currentPos]))
           return new Token(Token.Type.ERROR, "Unrecognized input.");

        /* compose an identifier */
        StringBuffer q = new StringBuffer();

        while (isLetter(buffer[currentPos]) || isDigit(buffer[currentPos])) {
            q.append(Character.toLowerCase(buffer[currentPos]));
            currentPos++;
            
        }

        String t = q.toString();
//        /*
//        /* Is it a matrix function name ? */
//        for (int i = 0; i < FunctionExpression.functions.length; i++) {
//            if (t.compareTo(FunctionExpression.functions[i]) == 0) {
//                return new Token(Token.FUNCTION, FunctionExpression.functions[i], i);
//            }
//        }
//
//        /* Is it a BASIC keyword ? */
//        for (int i = 0; i < Statement.keywords.length; i++) {
//            if (t.compareTo(Statement.keywords[i]) == 0) {
//                return new Token(Token.KEYWORD, Statement.keywords[i], i);
//            }
//        }
//
//        /* Is it a command ? */
//        for (int i = 0; i < CommandInterpreter.commands.length; i++) {
//            if (t.compareTo(CommandInterpreter.commands[i]) == 0) {
//                return new Token(Token.COMMAND, CommandInterpreter.commands[i], i);
//            }
//        }
        
        /*
         * It must be a variable.
         *
         */
        return new Variable(t);
    }

	/*
     * Return the buffer from the current position to the end as a string.
     */
    String asString() {
        int ndx = currentPos;

        while ((buffer[ndx] != '\n') && (buffer[ndx] != '\r'))
            ndx++;
        String result = new String(buffer, currentPos, ndx - currentPos);
        previousPos = currentPos;
        currentPos = ndx;
        return (result);
    }
}