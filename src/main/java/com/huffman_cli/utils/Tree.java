package com.huffman_cli.utils;

public class Tree {
    public Tree right;
    public Tree left;
    public Data data;

    public Tree(Data data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
