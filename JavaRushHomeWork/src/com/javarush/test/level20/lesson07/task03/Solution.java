package com.javarush.test.level20.lesson07.task03;

import java.io.*;
import java.util.List;

/* Externalizable Person
Класс Person должен сериализоваться с помощью интерфейса Externalizable.
Подумайте, какие поля не нужно сериализовать.
Исправьте ошибку сериализации.
Сигнатуры методов менять нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        File file = File.createTempFile("temp", null);
        Person dima = new Person("Dmitry", "Demyanov", 26);
        Person roma = new Person("Roman", "Demyanov", 45);
        Person natalya = new Person("Natalia", "Demyanova", 45);
        dima.setMother(natalya);
        dima.setFather(roma);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

        dima.writeExternal(objectOutputStream);

        Person person = new Person();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        person.readExternal(objectInputStream);

        System.out.println(person.toString());

    }

    public static class Person implements Externalizable
    {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List children;

        public Person(String firstName, String lastName, int age)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person()
        { // конструктор по умолчанию
        }

        @Override
        public String toString()
        {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    ", mother=" + mother +
                    ", father=" + father +
                    ", children=" + children +
                    '}';
        }

        public void setMother(Person mother)
        {
            this.mother = mother;
        }

        public void setFather(Person father)
        {
            this.father = father;
        }

        public void setChildren(List<Person> children)
        {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException
        {
            out.writeObject(this.father);
            out.writeObject(this.mother);
            out.writeObject(this.firstName);
            out.writeObject(this.lastName);
            out.writeInt(this.age);
            out.writeObject(this.children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
        {
            father = (Person) in.readObject();
            mother = (Person) in.readObject();
            firstName = (String)in.readObject();
            lastName = (String)in.readObject();
            age = in.readInt();
            children = (List<Person>)in.readObject();
        }
    }
}