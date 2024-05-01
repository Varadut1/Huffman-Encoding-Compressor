package com.huffman_cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(mixinStandardHelpOptions = true, name = "huffman", description = "Compress a file using Huffman coding")
public class HuffmanCommand implements Runnable {

    @CommandLine.Option(names = { "-c", "--compress" }, description = "Compress the input file")
    boolean compress;

    @CommandLine.Option(names = { "-d", "--decompress" }, description = "Decompress the input file")
    boolean decompress;

    @CommandLine.Option(names = { "-i", "--input" }, description = "Path of input file to compress")
    String inputFilePath;

    @CommandLine.Option(names = { "-o", "--output" }, description = "Path of output file to save compressed data")
    String outputFilePath;

    @Override
    public void run() {
        if (compress && decompress) {
            System.out.println("Cannot compress and decompress at the same time");
            return;
        }

        if (!compress && !decompress) {
            System.out.println("Please specify either compress or decompress");
            return;
        }
        Operations operations = new Operations();
        if (compress) {
            try {
                operations.compress(inputFilePath, outputFilePath);
            } catch (Exception e) {
                System.out.println("Error compressing file: " + e.getMessage());
            }
        } else {
            try {
                operations.decompress(inputFilePath, outputFilePath);
            } catch (Exception e) {
                System.out.println("Error decompressing file: " + e.getMessage());
            }
        }
    }
}
