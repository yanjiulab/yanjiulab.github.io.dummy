package io;

import java.io.*;

public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read("./basics/src/top/liyanjiu/io/MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char)c);
        }
    }
}
