package com.huffman_cli;

import java.io.IOException;
import java.util.HashMap;

import com.huffman_cli.utils.Bundle;
import com.huffman_cli.utils.HuffmanCoding;
import com.huffman_cli.utils.Tree;
import com.huffman_cli.utils.file_handle.FilePath;
import com.huffman_cli.utils.file_handle.FileUtils;

public class Operations {
    public void compress(String inputPath, String destinationPath) throws IOException {
        FilePath input = new FilePath(inputPath);
        System.out.println(destinationPath);
        System.out.println("Reading file");
        // String content = FileUtils.getFileContent(input);
        byte[] contentInBytes = FileUtils.getFileBytes(input);
        HashMap<Byte, Integer> frequency = FileUtils.getFileByteFrequency(input);
        // System.out.println(frequency);

        System.out.println("Compressing file: " + inputPath);
        HuffmanCoding huffman = new HuffmanCoding();
        Tree tree = huffman.buildTree(frequency);
        // BTreePrinter.printNode(tree);

        huffman.generateCodes(tree, "");

        Bundle compressedData = huffman.compress(contentInBytes);

        byte[] compressed = compressedData.compressed;
        int extraBits = compressedData.extraBits;

        String destinationFolder = destinationPath;
        FileUtils.createFolder(destinationFolder);

        String compressedFilePath = destinationFolder + "/" + input.fileName + "_compressed." + "huff";
        FileUtils.writeFile(new FilePath(compressedFilePath), compressed);

        String extraBitsFilePath = destinationFolder + "/" + input.fileName + "_compressed_extra_bits." + "txt";
        FileUtils.writeFileInString(new FilePath(extraBitsFilePath), String.valueOf(extraBits));

        System.out.println("Writing codes to file...");
        FileUtils.writeFileInString(new FilePath(destinationFolder + "/" + input.fileName + "_compressed_codes." + "txt"), frequency.toString());

        System.out.println("File compressed successfully!");
    }

    public void decompress(String inputPath, String destinationPath) throws IOException {
        System.out.println("Decompressing file: " + inputPath);
        
        // read frequency from file from the same folder
        String frequencyPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + "_codes.txt";
        FilePath frequencyFile = new FilePath(frequencyPath);
        String frequencyContent = FileUtils.getFileContent(frequencyFile);
        HashMap<Byte, Integer> frequency = new HashMap<>();
        // System.out.println(frequencyContent);
        String[] pairs = frequencyContent.substring(1, frequencyContent.length() - 2).split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            frequency.put(Byte.parseByte(keyValue[0]), Integer.parseInt(keyValue[1]));
        }
        // System.out.println(frequency.toString());
        // build tree
        HuffmanCoding huffman = new HuffmanCoding();
        Tree huffmanTree = huffman.buildTree(frequency);

        // BTreePrinter.printNode(huffmanTree);

        // read compressed file
        byte[] compressed = FileUtils.getFileBytes(new FilePath(inputPath));

        // read extra bits
        String extraBitsPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + "_extra_bits.txt";
        FilePath extraBitsFile = new FilePath(extraBitsPath);
        String extraBitsContent = FileUtils.getFileContent(extraBitsFile);
        int extraBits = Integer.parseInt(extraBitsContent.substring(0, extraBitsContent.length() - 1));

        // decompress
        try {
            byte[] decompressed = huffman.decompress(compressed, huffmanTree, extraBits);
            FileUtils.writeFile(new FilePath(destinationPath), decompressed);
            System.out.println("File decompressed successfully!");
        } catch (IOException e) {
            System.out.println("Error decompressing file: " + e.getMessage());
        }
    }
}
