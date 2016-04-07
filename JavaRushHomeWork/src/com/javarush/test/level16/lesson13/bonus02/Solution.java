package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution
{
    public static List<Thread> threads = new ArrayList<Thread>(5);
    static {

        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());
    }

    private static void sleep()
    {
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        Thread thread2 = threads.get(1);
        thread2.start();
        sleep();
        thread2.interrupt();

        Thread thread4 = threads.get(3);
        Message message = (Message) thread4;
        thread4.start();
        sleep();
        message.showWarning();
        System.out.println(thread4.isAlive());

    }

    public static class ThreadOne extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {

            }
        }
    }

    public static class ThreadTwo extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                while (!isInterrupted())
                {

                }
                throw new InterruptedException();
            }
            catch (InterruptedException e)
            {
                System.out.println("InterruptedException");
            }
        }
    }


    public static class ThreadThree extends Thread
    {
        public void run()
        {
            try
            {
                while (true)
                {
                    System.out.println("Ура");
                    sleep(500);
                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadFour extends Thread implements Message
    {
        public void run()
        {
            Thread current = Thread.currentThread();

            while (!current.isInterrupted())
            {

            }
        }
        @Override
        public void showWarning()
        {
            this.interrupt();
            try
            {
                this.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadFive extends Thread
    {
        BufferedReader reader;
        int count;

        public void run()
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
            count = 0;

            try
            {
                while(!isInterrupted())
                {
                    String tmp = reader.readLine();

                    if(tmp.equals("N"))
                    {
                        this.interrupt();
                    }
                    else
                    {
                        int temp = Integer.parseInt(tmp);
                        count += temp;
                    }
                }

                throw new InterruptedException();
            }
            catch (InterruptedException ex)
            {
                System.out.println(count);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
