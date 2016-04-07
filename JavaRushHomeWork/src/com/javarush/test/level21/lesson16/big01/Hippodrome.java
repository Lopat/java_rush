package com.javarush.test.level21.lesson16.big01;


import java.util.ArrayList;

public class Hippodrome {

    private static ArrayList<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public static void main(String[] args) {

        game = new Hippodrome();

        Horse horseOne = new Horse("Leila", 3, 0);
        Horse horseTwo = new Horse("Olesya", 3, 0);
        Horse horseThree = new Horse("Olga", 3, 0);

        game.getHorses().add(horseOne);
        game.getHorses().add(horseTwo);
        game.getHorses().add(horseThree);

        game.run();
        game.printWinner();
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void run() {

        for (int i = 0; i < 100; i++) {
            move();
            print();
        }
    }

    public void move(){
        for (Horse horse : getHorses()) {
            horse.move();
        }
    }

    public void print(){

        for (Horse horse : getHorses()) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner(){

        double dist = 0.0d;
        Horse winner = null;

        for (Horse horse : getHorses()) {
            if(horse.getDistance() > dist)
            {
                dist = horse.getDistance();
            }
        }
        for (Horse horse : getHorses()) {
            if(horse.getDistance() == dist )
            {
                winner = horse;
            }
        }

        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
