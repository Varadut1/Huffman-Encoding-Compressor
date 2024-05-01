package com.huffman_cli.utils.file_handle;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class FileUtils {
    public static String getFileContent(FilePath path) {
        try {
            File myObj = new File(path.path);
            Scanner myReader = new Scanner(myObj);
            StringBuilder content = new StringBuilder();
            System.out.println("Reading file...");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                content.append(data + "\n");
            }
            myReader.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    public static byte[] getFileBytes(FilePath path) throws IOException {
        FileInputStream inStream = new FileInputStream(path.path);
        byte[] b = new byte[inStream.available()];
        inStream.read(b);
        inStream.close();
        return b;
    }

    public static HashMap<Byte, Integer> getFileByteFrequency(FilePath path) throws FileNotFoundException {
        File file = null;
        Byte bt;
        HashMap<Byte, Integer> frequency = new HashMap<>();
        file = new File(path.path);
        try {
            FileInputStream file_input = new FileInputStream(file);
            DataInputStream data_in = new DataInputStream(file_input);
            while (true) {
                try {

                    bt = data_in.readByte();
                    if (frequency.containsKey(bt)) {
                        frequency.put(bt, frequency.get(bt) + 1);
                    } else {
                        frequency.put(bt, 1);
                    }
                } catch (EOFException eof) {
                    System.out.println("End of File");
                    break;
                }
            }
            file_input.close();
            data_in.close();
        } catch (IOException e) {
            System.out.println("IO Exception =: " + e);
        }
        file = null;
        return frequency;
    }

    public static void writeFile(FilePath path, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path.path)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileInString(FilePath path, String data) throws IOException {
        // write file in the form of bits and not string
        // String pathWithExt = path.path + "_codes.txt";
        File file = new File(path.path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.write(Paths.get(path.path), data.getBytes());
    }
}
