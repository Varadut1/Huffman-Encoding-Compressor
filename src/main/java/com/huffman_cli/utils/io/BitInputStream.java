package com.huffman_cli.utils.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BitInputStream {
    private ByteArrayInputStream input;
    private int nextBits;
    private int bitsLeft;

    public BitInputStream(byte[] bytes) {
        input = new ByteArrayInputStream(bytes);
        fillBuffer();
    }

    private void fillBuffer() {
        nextBits = input.read();
        if (nextBits == -1) {
            bitsLeft = 0; // No more bits left when end of stream is reached
        } else {
            bitsLeft = 8; // Reset the bit count for a new byte
        }
    }

    public int readBit() {
        if (bitsLeft == 0) {
            return -1; // Properly return -1 if there are no bits left
        }

        int bit = (nextBits >> (bitsLeft - 1)) & 1; // Extract the current bit
        bitsLeft--; // Decrement the count of bits left in the current byte

        if (bitsLeft == 0) {
            fillBuffer(); // Refill buffer if needed after current bit is processed
        }

        return bit;
    }

    public void close() throws IOException {
        input.close();
    }
}
