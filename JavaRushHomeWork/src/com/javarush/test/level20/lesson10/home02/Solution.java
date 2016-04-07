package com.javarush.test.level20.lesson10.home02;

import java.io.*;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуйте объект в методе getOriginalObject предварительно определив, какого именно типа там объект.
Реализуйте интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable{

//    public static void main(String[] args) throws IOException {
//        B obj = new B();
//        File file = File.createTempFile("temp", null);
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//        oos.writeObject(obj);
//
//        try {
//            A loaded = getOriginalObject(new ObjectInputStream(new FileInputStream(file)));
//        }
//        catch (ClassNotFoundException e) {
//            System.out.println("fAIL!!!!");
//        }
//    }

    public A getOriginalObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException {
        A a = (A) objectStream.readObject();
        if (a instanceof B)
            return (B) a;
        else
            return a;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }
}
