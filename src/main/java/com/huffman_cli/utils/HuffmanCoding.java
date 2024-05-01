package com.huffman_cli.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

import com.huffman_cli.utils.io.BitInputStream;
import com.huffman_cli.utils.io.BitOutputStream;

public class HuffmanCoding {
    PriorityQueue<Tree> queue = new PriorityQueue<Tree>((a, b) -> a.data.frequency - b.data.frequency);
    public HashMap<Byte, String> codes = new HashMap<Byte, String>();
    public Tree huffmanTree = null;
    int extraBits = 0;

    public Tree buildTree(HashMap<Byte, Integer> frequency) {
        for (Byte key : frequency.keySet()) {
            queue.add(new Tree(new Data(key, frequency.get(key))));
        }

        while (queue.size() > 1) {
            Tree left = queue.poll();
            Tree right = queue.poll();
            Tree parent = new Tree(new Data(null, left.data.frequency + right.data.frequency));
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }
        huffmanTree = queue.peek();
        return queue.poll();
    }

    public void generateCodes(Tree root, String code) {
        if (root.data.data != null) {
            codes.put(root.data.data, code);
            return;
        }

        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    public void printCodes() {
        for (Byte key : codes.keySet()) {
            System.out.println((char) key.byteValue() + ": " + codes.get(key));
        }
    }

    public Bundle compress(byte[] bytes) throws IOException {
        this.extraBits = 0;
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(codes.get(b));
        }
        while (builder.length() % 8 != 0) {
            builder.append('0');
            this.extraBits++;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        BitOutputStream bitOutput = new BitOutputStream(output);
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '0') {
                bitOutput.writeBit(0);
            } else {
                bitOutput.writeBit(1);
            }
        }
        bitOutput.flush();

        // System.out.println(builder);
        return new Bundle(output.toByteArray(), extraBits);
    }

    public byte[] decompress(byte[] compressed, Tree huffmanTree, int extraBits) throws IOException {
        BitInputStream bitInput = new BitInputStream(compressed);
        ByteArrayOutputStream decompressedOutput = new ByteArrayOutputStream();
        Tree currentNode = huffmanTree; // Assume huffmanTree is the root of your Huffman tree
        int bitsToRead = compressed.length * 8 - extraBits;
        int bit;
        while (bitsToRead >= 0 && (bit = bitInput.readBit()) != -1) {
            if (bit == 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
            bitsToRead--;
            if (currentNode.data != null && currentNode.data.data != null) {
                decompressedOutput.write(currentNode.data.data.intValue()); // 'data' should be the original byte value
                currentNode = huffmanTree; // Reset to start of Huffman tree
            }
        }

        bitInput.close();
        // System.out.println(decompressedOutput.toString());
        return decompressedOutput.toByteArray();
    }

    public static void printTree(Tree node, int level) {
        if (node == null) {
            return;
        }

        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(node);
        printTree(node.left, level + 1);
    }
}