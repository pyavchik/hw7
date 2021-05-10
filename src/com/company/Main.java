package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("Morning", "Ранок");
        map.put("Umbrella", "Зонт");

        String text = "Morning Morning Umbrella";

        try(FileOutputStream fos = new FileOutputStream("English.in");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(text);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String textFrom = "";

        try(FileInputStream fis = new FileInputStream("English.in");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
            textFrom = (String) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        final String[] words = textFrom.split(" ");

        final StringBuilder builder = new StringBuilder();

        Arrays.stream(words).forEach(w -> {
            builder.append(map.get(w)).append(" ");
        });

        System.out.println(builder.toString());

        final Map<String, Long> collect = Stream.of(words)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        System.out.println(collect);

    }
}
