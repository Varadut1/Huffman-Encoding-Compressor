package com.huffman_cli.utils.io;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {
    private OutputStream output;
    private int currentByte;
    private int numBitsInCurrentByte;

    public BitOutputStream(OutputStream out) {
        output = out;
        currentByte = 0;
        numBitsInCurrentByte = 0;
    }

    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1) {
            throw new IllegalArgumentException("Invalid bit: " + bit);
        }
        currentByte = currentByte << 1 | bit;
        numBitsInCurrentByte++;
        if (numBitsInCurrentByte == 8) {
            output.write(currentByte);
            numBitsInCurrentByte = 0;
            currentByte = 0;
        }
    }

    public void flush() throws IOException {
        while (numBitsInCurrentByte != 0) {
            writeBit(0);
        }
    }
}
