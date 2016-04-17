package com.github.adorow.template;

import java.io.*;

/**
 * I/O class with utility methods for reading and writing data.<br/>
 * This file is not Thread-Safe.
 */
public class IO {

    private static final int IN_BUFFER_LENGTH = 8192;
    private static final int OUT_BUFFER_LENGTH = 8192;

    private static final int EOF = -1;

    private InputStream in;
    private PrintStream out;

    private final byte[] input_buffer = new byte[IN_BUFFER_LENGTH];
    private int pos = 0;
    private int lastReadLength = 0;

    private final byte[] output_buffer = new byte[OUT_BUFFER_LENGTH];
    private int posOut = 0;

    /**
     * Creates a new instance of IO that uses STDIN and STDOUT.
     */
    public IO() {
        useStdIn();
        useStdOut();
    }

    // INPUT CHECKS

    // is the character minus (-)
    private static boolean isMinus(int cur) {
        return cur == '-';
    }

    // is the character plus (+)
    private static boolean isPlus(int cur) {
        return cur == '+';
    }

    // true if the input is a number
    private static boolean isNum(int v) {
        return v >= '0' && v <= '9';
    }

    // true if the input is a lowercase character
    private static boolean isLowercase(int cur) {
        return cur >= 'a' && cur <= 'z';
    }

    // true if the input is an uppercase character
    private static boolean isUppercase(int cur) {
        return cur >= 'A' && cur <= 'Z';
    }

    // is a letter (alphabetic)
    private static boolean isAlpha(int cur) {
        return isLowercase(cur) || isUppercase(cur);
    }

    // is a blank space
    private static boolean isSpaceOrTab(int cur) {
        return cur == ' ' || cur == '\t';
    }

    // is a new line character
    private static boolean isNewLine(int cur) {
        return cur == '\r' || cur == '\n';
    }

    // is alphanumeric (letters or numbers)
    private static boolean isAlphaNum(int cur) {
        return isAlpha(cur) || isNum(cur);
    }

    /**
     * Returns whether the input is currently at the end of the line.
     */
    public boolean isEndOfLine() throws IOException {
        while (true) {
            int data = read();
            if (data == EOF)
                return true;
            if (isNewLine(data)) {
                return true;
            }
            if (isSpaceOrTab(data)) {
                continue;
            }
            pos--;
            return false;
        }
    }

    /**
     * Returns whether the input is currently at the end of the file.
     */
    public boolean isEndOfFile() throws IOException {
        int data = read();
        if (data == EOF)
            return true;
        pos--;
        return false;
    }

    /**
     * Tells whether there is still some input to be read.
     */
    public boolean hasInput() throws IOException {
        while (true) {
            int data = read();
            if (data == EOF)
                return false;
            if ((data & 0xE0) != 0) {
                pos--;
                return true;
            }
        }
    }

    // INPUT AND OUTPUT USE

    /**
     * Uses STDIN for input from this point on.
     */
    public void useStdIn() {
        in = System.in;
    }

    /**
     * Takes a file and uses it as input from this point on.
     *
     * @param filename the file to be used as input.
     * @throws FileNotFoundException if the given file does not exist.
     */
    public void useFileIn(String filename) throws FileNotFoundException {
        in = new FileInputStream(filename);
    }

    /**
     * Uses STDOUT for output from this point on.
     */
    public void useStdOut() {
        out = System.out;
    }

    /**
     * Takes a file and uses it as output from this point on.
     *
     * @param filename the file to be used as output.
     * @throws FileNotFoundException if the given file does not exist.
     */
    public void useFileOut(String filename) throws FileNotFoundException {
        out = new PrintStream(new FileOutputStream(filename));
    }

    // INPUT MANIPULATION

    /**
     * Moves the input pointer to the beginning of the next word.
     */
    public void moveToNextWord() throws IOException {
        int cur;
        while ((cur = read()) != EOF) {
            if (cur > 32) {
                pos--;
                return;
            }
        }
    }

    /**
     * Skip the next word from the input (text, number, etc).
     */
    public void skipWord() throws IOException {
        int cur;
        while ((cur = read()) < 33) {
            if (cur == EOF)
                return;
        }
        while (read() > 32)
            ;
    }

    /**
     * Skip the next blanks in the input.
     */
    public void skipBlanks() throws IOException {
        char c = (char) read();
        while (c < 33) {
            if (c == EOF) return;
            c = (char) read();
        }
        pos--;
    }

    /**
     * Move the input pointer to the next input.
     */
    public void skipToNextNewLine() throws IOException {
        char c = (char) read();
        while (c != '\n') {
            if (c == EOF) return;
            c = (char) read();
        }
    }

    // reads the next byte in the input
    private int read() throws IOException {
        if (pos >= lastReadLength) {
            lastReadLength = in.read(input_buffer);
            if (lastReadLength < 0)
                return EOF;
            pos = 0;
        }
        return input_buffer[pos++] & 0xff;
    }

    /**
     * Reads a single character from the input.
     */
    public char readChar() throws IOException {
        int cur;
        for (; ; ) {
            cur = read();
            if (cur == EOF)
                return 0;
            if (isSpaceOrTab(cur) || isNewLine(cur))
                continue;

            return (char) cur;
        }
    }

    /**
     * Reads the next number in the input as an {@code int}.
     */
    public int readInt() throws IOException {
        int cur;
        while (!isNum(cur = read()) && !isMinus(cur) && !isPlus(cur))
            if (cur == EOF)
                return EOF;
        int v = 0;
        boolean minus = false;
        if (isMinus(cur)) {
            minus = true;
            cur = read();
        }
        if (isPlus(cur)) {
            cur = read();
        }
        while (isNum(cur)) {
            v *= 10;
            v += (cur - '0');
            cur = read();
        }
        return minus ? -v : v;
    }

    /**
     * Reads the next number in the input as an {@code long}.
     */
    public long readLong() throws IOException {
        int cur;
        while (!isNum(cur = read()) && !isMinus(cur) && !isPlus(cur))
            if (cur == EOF)
                return EOF;
        long v = 0;
        boolean minus = false;
        if (isMinus(cur)) {
            minus = true;
            cur = read();
        }
        if (isPlus(cur)) {
            cur = read();
        }
        while (isNum(cur)) {
            v *= 10;
            v += (cur - '0');
            cur = read();
        }
        return minus ? -v : v;
    }

    /**
     * Reads a decimal number, as {@code double}.
     */
    public double readDouble() throws IOException {
        int cur;
        cur = read();
        while (cur < 33) {
            cur = read();
            if (cur < 0) return EOF;
        }
        boolean negative = false;
        if (isMinus('-')) {
            negative = true;
            cur = read();
        }
        long v = 0;
        while (cur > 32 && cur != '.') {
            v *= 10;
            v += ((cur - '0') & 0xFF);
            cur = read();
        }
        long after = 0;
        if (cur == '.') {
            cur = read();
            while (cur > 32) {
                after *= 10;
                after += ((cur - '0') & 0xFF);
                cur = read();
            }
        }

        double ret = v;
        if (after > 0) {
            ret += (1.0 / after);
        }
        return negative ? -ret : ret;
    }

    /**
     * Reads a String from the input, of a given maximum length.
     */
    public String readText(int length) throws IOException {
        int len = 0;
        int cur;
        while ((cur = read()) < 33) {
            if (cur == -1) return "";
        }

        char[] tmpcharr = new char[length];
        tmpcharr[len++] = (char) cur;
        while ((cur = (char) read()) > 32 && len < length) {
            tmpcharr[len++] = (char) cur;
        }

        return new String(tmpcharr, 0, len);
    }


    /**
     * Reads a text into a {@code char[]}, returning the length of the read text.
     */
    public int readText(char[] input_text) throws IOException {
        char c = (char) read();
        while (c < 33) {
            c = (char) read();
        }
        int len = 0;
        while (c > 32 && c < 65535) {
            input_text[len++] = c;
            c = (char) read();
        }

        return len;
    }

    /**
     * Reads into the input array exactly the amount of input given.
     */
    public void readText(char[] input_text, int len) throws IOException {
        char c = readChar();
        while ((c & 0xE0) == 0) {
            c = readChar();
        }
        for (int i = 0; i < len; i++) {
            input_text[i] = c;
            c = readChar();
        }
    }

    // OUTPUT

    /**
     * Writes a single byte into the output.
     */
    public void writeOut(byte b) {
        if (posOut >= OUT_BUFFER_LENGTH) {
            writeOutToStream();
        }
        output_buffer[posOut++] = b;
    }

    /**
     * Writes a single character into the output.
     */
    public void writeOut(char c) {
        writeOut((byte) c);
    }

    /**
     * Writes a new line character in the output.
     */
    public void writeOutLn() {
        writeOut('\n');
    }

    /**
     * Writes out an entire {@code byte[]} into the output.
     */
    public void writeOut(byte[] bs) {
        writeOut(bs, 0, bs.length);
    }

    /**
     * Writes a {@code byte[]} into the output.
     *
     * @param bytes  the content.
     * @param offset the initial position to write out.
     * @param length the amount of data to write out.
     */
    public void writeOut(byte[] bytes, int offset, int length) {
        final int end = offset + length;
        for (int i = offset; i < end; i++) {
            writeOut(bytes[i]);
        }
    }

    /**
     * Writes a {@code char[]} in the output.
     */
    public void writeOut(char[] cs) {
        writeOut(cs, 0, cs.length);
    }

    /**
     * Writes a {@code char[]} into the output.
     *
     * @param chars  the content.
     * @param offset the initial position to write out.
     * @param length the amount of data to write out.
     */
    public void writeOut(char[] chars, int offset, int length) {
        final int end = offset + length;
        for (int i = offset; i < end; i++) {
            writeOut(chars[i]);
        }
    }

    /**
     * Writes out a number in the output.
     *
     * @param value the number to be written.
     */
    public void writeOutNumber(int value) {
        int len = 0;
        char[] tmpcharr = new char[10];
        int pos = tmpcharr.length;
        if (value == 0) {
            writeOut('0');
            return;
        }
        boolean negative = value < 0;
        if (negative)
            value = -value;
        while (value > 0) {
            tmpcharr[--pos] = (char) ((value % 10) + '0');
            len++;
            value /= 10;
        }
        if (negative)
            writeOut('-');
        writeOut(tmpcharr, pos, len);
    }

    /**
     * Writes out a number in the output.
     *
     * @param value the number to be written.
     */
    public void writeOutNumber(long value) {
        int len = 0;
        char[] tmpcharr = new char[20];
        int pos = tmpcharr.length;
        if (value == 0) {
            writeOut('0');
            return;
        }
        boolean negative = value < 0;
        if (negative)
            value = -value;
        while (value > 0) {
            tmpcharr[--pos] = (char) ((value % 10) + '0');
            len++;
            value /= 10;
        }
        if (negative)
            writeOut('-');
        writeOut(tmpcharr, pos, len);
    }

    /**
     * Writes a {@code String} into the output.
     */
    public void writeOutString(String str) {
        for (int i = 0; i < str.length(); i++) {
            writeOut(str.charAt(i));
        }
    }

    /**
     * Writes a number in the output, as binary.
     */
    public void writeOutNumberAsBinary(int num) {
        int len = 0;
        char[] tmpcharr = new char[32];
        int pos = tmpcharr.length;
        if (num == 0) {
            writeOut('0');
            return;
        }
        while (num > 0) {
            tmpcharr[--pos] = ((num & 1) == 1) ? '1' : '0';
            len++;
            num >>>= 1;
        }
        writeOut(tmpcharr, pos, len);
    }

    // writes from the input_buffer to the stream. Should never be called directly.
    private void writeOutToStream() {
        out.write(output_buffer, 0, posOut);
        posOut = 0;
    }

    /**
     * Flushes all the remaining data from the buffer into the output. This method should be called always in the end of every problem.
     */
    public void flush() {
        writeOutToStream();
        out.flush();
    }
}
