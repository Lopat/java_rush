package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        int world =0;

        while (fileReader.ready())
        {
            String str = fileReader.readLine().replaceAll("\\p{Punct}", " ");
            String[] array = str.split(" ");

            for (String s : array)
            {
                if (s.equals("world"))
                {
                    world++;
                }
            }
        }

        fileReader.close();
        System.out.println(world);
    }
}
