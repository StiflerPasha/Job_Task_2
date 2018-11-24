package com.packages;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file PATH (for example D:/someFolder/someFile.txt): ");
        String path = sc.nextLine();

        String delMarks = " \t\n\r\f,.!?-+=";

        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            StringTokenizer tokenizer = new StringTokenizer(content, delMarks);

            Map<String, Integer> words = new TreeMap<String, Integer>();
            String word;
            Integer count;
            while (tokenizer.hasMoreTokens()) {
                word = tokenizer.nextToken().toLowerCase();
                count = words.get(word);
                count = (count == null) ? 1 : count + 1;
                words.put(word, count);
            }

            Map<String, Integer> topTen = words.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(10)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


            for (Map.Entry entry : topTen.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }

        } catch (NoSuchFileException ex) {
            System.out.println("File not Found");
        }
    }
}