package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        BufferedWriter fileWriter;

        switch (args[0])
        {
            case "-c":
            {
                String productName = "";

                for (int i = 1; i < args.length - 2; i++)
                {
                    productName += (args[i] + " ");
                }
                productName = productName.trim();
                productName = setSpaces(productName, 30);
                String price = setSpaces(args[args.length - 2], 8);
                String quantity = setSpaces(args[args.length - 1], 4);

                int maxId = 0;

                while (fileReader.ready())
                {
                    String fileString = fileReader.readLine();
                    String strId = fileString.substring(0, 8).replaceAll("\\s", "");

                    if(maxId < Integer.parseInt(strId))
                    {
                        maxId = Integer.parseInt(strId);
                    }
                }
                fileReader.close();

                maxId += 1;
                fileWriter = new BufferedWriter(new FileWriter(filename, true));
                fileWriter.write(setSpaces(String.valueOf(maxId), 8) + productName + price + quantity);
                fileWriter.close();
                break;
            }
        }


    }

    public static String setSpaces(String attribute, int length)
    {
        StringBuilder sb = new StringBuilder(attribute);

        if (attribute.length() < length)
        {
            for (int i = attribute.length(); i < length; i++)
            {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
