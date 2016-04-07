package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution
{
   static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args)
    {
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream
        // в соответствии с путем к вашему реальному файлу

        try
        {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //инициализируйте поле users для объекта javaRush тут
            {
                User user = new User();
                user.setFirstName("First Name");
                user.setLastName("Last Name");
                user.setBirthDate(Solution.dateFormat.parse("01.06.2014"));
                user.setMale(true);
                user.setCountry(User.Country.RUSSIA);
                javaRush.users.add(user);
            }

            {
                User user = new User();
                user.setFirstName("Second First Name");
                user.setBirthDate(Solution.dateFormat.parse("04.06.2014"));
                user.setMale(false);
                javaRush.users.add(user);
            }
            {
                User user = new User();
                javaRush.users.add(user);
            }

            javaRush.save(outputStream);
            outputStream.flush();

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

//            for (User u : loadedObject.users)
//            {
//                System.out.println("NEW USERRRRRRRR");
//                System.out.println(u.getFirstName());
//                System.out.println(u.getLastName());
//                System.out.println(u.getBirthDate());
//                System.out.println(u.isMale());
//                System.out.println(u.getCountry());
//            }

            outputStream.close();
            inputStream.close();

        }
        catch (IOException e)
        {
//            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        }
        catch (Exception e)
        {
           e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush
    {
        public List<User> users = new ArrayList<>();


        public void save(OutputStream outputStream) throws Exception
        {
            PrintWriter printWriter = new PrintWriter(outputStream);

            for (User user : users)
            {
                printWriter.println("###");

                String firstName = user.getFirstName();
                if (firstName == null)
                {
                    firstName = "noName";
                }
                printWriter.println(firstName);

                String lastName = user.getLastName();
                if (lastName == null)
                {
                    lastName = "noLName";
                }
                printWriter.println(lastName);

                if (user.getBirthDate() != null)
                    printWriter.println(dateFormat.format(user.getBirthDate()));
                else
                    printWriter.println("noBDay");

                printWriter.println(String.valueOf(user.isMale()));

                if(user.getCountry() != null)
                    printWriter.println(user.getCountry().getDisplayedName());
                else
                    printWriter.println("noCountry");
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));



            while (reader.ready())
            {
                String input = reader.readLine();

                if (input.equals("###"))
                {
                    User user = new User();
                    String firstName = reader.readLine();
                    if (firstName.equals("noName"))
                    {
                        firstName = null;
                    }
                    user.setFirstName(firstName);

                    String lastName = reader.readLine();
                    if (lastName.equals("noLName"))
                    {
                        lastName = null;
                    }
                    user.setLastName(lastName);

                    String birthday = reader.readLine();
                    if (birthday.equals("noBDay"))
                        user.setBirthDate(null);
                    else
                        user.setBirthDate(dateFormat.parse(birthday));

                    user.setMale(Boolean.parseBoolean(reader.readLine()));

                    String countries = reader.readLine();
                    switch (countries)
                    {
                        case "Ukraine":
                            user.setCountry(User.Country.UKRAINE);
                            break;
                        case "Russia":
                            user.setCountry(User.Country.RUSSIA);
                            break;
                        case "noCountry":
                            user.setCountry(null);
                            break;
                        default:
                            user.setCountry(User.Country.OTHER);
                            break;
                    }
                    this.users.add(user);
                }
            }
            reader.close();
        }
    }
}