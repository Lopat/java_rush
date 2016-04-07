package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Person person = new Person("Dm", "De", "Rus", Sex.MALE);
//        File file = File.createTempFile("temp", null);
//        person.writeExternal(new ObjectOutputStream(new FileOutputStream(file)));
//        System.out.println(person);
//
//
//        Person newPerson = new Person();
//        newPerson.readExternal(new ObjectInputStream(new FileInputStream(file)));
//        System.out.println(newPerson);
//    }

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        final transient String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;


        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));

        }

//        @Override
//        public void writeExternal(ObjectOutput out) throws IOException {
//            out.writeObject(firstName);
//            out.writeObject(lastName);
//            out.writeObject(fullName);
//            out.writeObject(country);
//            out.writeObject(sex);
//            out.close();
//        }
//
//        @Override
//        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//            firstName = ((String) in.readObject());
//            lastName = ((String) in.readObject());
//            fullName = ((String) in.readObject());
//            country = ((String) in.readObject());
//            sex = ((Sex) (in.readObject()));
//            this.outputStream = System.out;
//            this.logger = Logger.getLogger(String.valueOf(Person.class));
//            in.close();
//        }
//
//        @Override
//        public String toString() {
//            return "Person{" +
//                    "firstName='" + firstName + '\'' +
//                    ", lastName='" + lastName + '\'' +
//                    ", fullName='" + fullName + '\'' +
//                    ", greetingString='" + greetingString + '\'' +
//                    ", country='" + country + '\'' +
//                    ", sex=" + sex +
//                    ", outputStream=" + outputStream +
//                    '}';
//        }
    }

    enum Sex {
        MALE,
        FEMALE
    }
}
