package com.javarush.test.level20.lesson02.task03;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполните карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуйте логику записи в файл и чтения из файла для карты properties.
*/
public class Solution
{
    public static Map<String, String> properties = new HashMap<>();

    public static void main(String[] args) throws Exception
    {
        new Solution().fillInPropertiesMap();
        System.out.println(properties);
        new Solution().save(new FileOutputStream("d:/2.properties"));
    }

    public void fillInPropertiesMap() throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        Properties props = new Properties();
        props.load(inputStream);

        for (Map.Entry<Object, Object> pairs : props.entrySet())
        {
            properties.put((String) pairs.getKey(), (String) pairs.getValue());
        }
    }

    public void save(OutputStream outputStream) throws Exception
    {
        Properties prop = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet())
        {
            prop.put(entry.getKey(), entry.getValue());
        }
        prop.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception
    {
        Properties prop = new Properties();
        prop.load(inputStream);

        for (Map.Entry<Object, Object> pair : prop.entrySet())
        {
            properties.put((String) pair.getKey(), (String) pair.getValue());
        }
    }
}
