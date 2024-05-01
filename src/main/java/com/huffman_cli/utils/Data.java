package com.huffman_cli.utils;

public class Data {
    public Byte data;
    int frequency;

    public Data(Byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return data == null ? "$" + "" : (char) data.byteValue() + "";
    }
}
