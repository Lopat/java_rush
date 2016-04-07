package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        HashMap<String, Double> map = new HashMap<>();
        double max = 0d;

        while (fileReader.ready())
        {
            String input = fileReader.readLine();
            String name = input.substring(0, input.indexOf(" "));
            double num = Double.parseDouble(input.substring(input.indexOf(" ")));


            if (map.containsKey(name))
            {
                double d = map.get(name);
                d += num;
                map.put(name, d);
            }
            else
            {
                map.put(name, num);
            }
        }
        fileReader.close();

        for(double x : map.values())
        {
            if(x > max)
            {
                max = x;
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet())
        {
            if(pair.getValue().equals(max))
            {
                System.out.println(pair.getKey());
            }
        }
    }
}
