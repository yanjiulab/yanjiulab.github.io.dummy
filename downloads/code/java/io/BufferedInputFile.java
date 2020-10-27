package io;

import java.io.*;

/**
 * FileReader
 * BufferedReader
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        // Reading input by lines
        FileReader file = new FileReader(filename);
        BufferedReader in = new BufferedReader(file);

        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();

    }
    public static void main(String[] args) throws IOException {
        System.out.print(read("./basics/src/top/liyanjiu/io/BufferedInputFile.java"));
    }
}

