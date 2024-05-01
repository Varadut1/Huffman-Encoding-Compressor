package com.huffman_cli;

import picocli.CommandLine;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        new CommandLine(new HuffmanCommand()).execute(args);
    }
}