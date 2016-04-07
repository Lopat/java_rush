package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution
{
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));

        while (fileReader.ready())
        {
            String[] data = fileReader.readLine().split(" ");
            int len = data.length;
            Date date = new Date(data[len - 2] + "/" + data[len - 3] + "/" + data[len - 1]);
            String name = "";

            for (int i = 0; i < len - 3; i ++)
            {
                name += (data[i] + " ");
            }

            PEOPLE.add(new Person(name.trim(), date));
        }
        fileReader.close();
    }
}
