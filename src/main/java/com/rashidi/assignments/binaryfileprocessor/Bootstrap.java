package com.rashidi.assignments.binaryfileprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Reading first 1000 bytes of binary files and calculating sum of their values.
 * Print out the sums and the corresponding file names sorted by the sum value in ascending order.
 *
 * @author Mina rashidi
 */
public class Bootstrap {
    public static void main(String[] args) {

        Map<Integer, String> sumNameMap = new TreeMap<>();
        Stream.of("look1.jpg", "look2.jpg", "look3.jpg").forEach(imageName ->

                Optional.ofNullable(getBytes(imageName)).ifPresent(imageByte -> {
                    int sum = IntStream.range(0, 1000).map(idx -> imageByte[idx]).sum();
                    sumNameMap.put(sum, imageName);
                })
        );
        sumNameMap.entrySet().forEach((entry -> System.out.println(entry.getKey() + ": " + entry.getValue())));
    }

    /**
     * gets byte array from a binary file
     *
     * @return byte array
     */
    public static byte[] getBytes(String imageName) {
        File file = new File(imageName);
        byte[] imageInByte = null;
        try {
            imageInByte = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Exception while reading the file " + e);
        }
        return imageInByte;
    }
}
