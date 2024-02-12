package org.example.stringtheory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        String lineOfWords = scanner.nextLine().trim();
        String searchWord = scanner.nextLine().trim();

        // Call the function with the provided input
        findWordIndex(lineOfWords, searchWord);
    }

    public static void findWordIndex(String sentence, String searchWord) {
        String[] words = sentence.split("\\s+");
        int index = -1;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(searchWord)) {
                index = i + 1;
                break;
            }
        }

        if (index != -1) {
            System.out.println(index);
        } else {
            System.out.println("Not Found");
        }
    }
}
