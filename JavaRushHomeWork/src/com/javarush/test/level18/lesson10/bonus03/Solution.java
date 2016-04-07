package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        BufferedWriter fileWriter;
        ArrayList<String> fileStrings = new ArrayList<>();

        while (fileReader.ready())
        {
            String str = fileReader.readLine();
            fileStrings.add(str);
        }
        fileReader.close();

// C:\Users\Эм\Desktop\test\aa.txt
        switch (args[0])
        {
            case "-u":
            {
                String id = setSpaces(args[1], 8);

                String productName = "";
                for (int i = 2; i < args.length - 2; i++)
                {
                    productName += (args[i] + " ");
                }
                productName = productName.trim();
                productName = setSpaces(productName, 30);

                String price = setSpaces(args[args.length - 2], 8);
                String quantity = setSpaces(args[args.length - 1], 4);

                fileWriter = new BufferedWriter(new FileWriter(file));
                for (int i = 0; i < fileStrings.size(); i++)
                {
                    String sId = fileStrings.get(i).substring(0, 8).replaceAll("\\s", "");

                    if (sId.equals(id.trim()))
                    {
                        fileStrings.set(i, (id + productName + price + quantity));
                    }

                    fileWriter.write(fileStrings.get(i));
                    fileWriter.newLine();
                }
                fileWriter.close();
                break;
            }

            case "-d":
            {
                fileWriter = new BufferedWriter(new FileWriter(file, false));

                for (int i = 0; i < fileStrings.size(); )
                {
                    String id = fileStrings.get(i).substring(0, 8).replaceAll("\\s", "");

                    if (id.equals(args[1]))
                    {
                        fileStrings.remove(i);
                    } else
                    {
                        fileWriter.write(fileStrings.get(i));
                        fileWriter.newLine();
                        i++;
                    }
                }
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
