package com.huffman_cli.utils;

public class Bundle {
    public byte[] compressed;
    public int extraBits;

    public Bundle(byte[] compressed, int extraBits) {
        this.compressed = compressed;
        this.extraBits = extraBits;
    }
}
