package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.

Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {

    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        ) {
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine() + " ");
            }
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        String[] words = stringBuilder.toString().split(" ");

        for (int i = 0; i < words.length - 1; i++) {

            StringBuilder sb = new StringBuilder(words[i]).reverse();
            if (sb.toString().equals(words[i + 1])) {
                Pair p = new Pair();
                p.first = words[i];
                p.second = words[i + 1];
            }
            for (Pair pair : result) {
                System.out.println(pair);
            }

        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}


