package com.javarush.test.level20.lesson04.task04;

import java.io.*;

/* Как сериализовать static?
Сделайте так, чтобы сериализация класса ClassWithStatic была возможной
*/
public class Solution
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ClassWithStatic cs = new ClassWithStatic();
        cs.j = 5;
        cs.i = 7;
        System.out.println(cs.staticString + "|" + cs.i + "|" + cs.j);

        File file = new File("testfiles/test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(cs);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        ClassWithStatic lcs = (ClassWithStatic) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(lcs.staticString + "|" + lcs.i + "|" + lcs.j);
    }

    public static class ClassWithStatic implements Serializable
    {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
    }
}
