/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oktador.numberguess;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Oktay
 */
public class Main {

    public static void main(String[] args) {
        int intStart = 0;
        int intEnd = 0;
        int numberToGuess;
        int currentGuess;
        int attemptCountLeft = 0;
        boolean help = false;
        boolean running = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's play a game.\nYou will define an interval.\nI will chose a number from this interval.\nYou will try to find this number.");
        while (running) {
            //Get the smallest number - must be >=0
            while (true) {
                System.out.println("Enter the smallest number of the interval:");
                try {
                    intStart = scanner.nextInt();
                    if (intStart < 0) {
                        System.out.println("You must enter a positive number.");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("You must enter a number.");
                    scanner.nextLine();
                }
            }

            //Get the biggest number - must be >(intStart + 8)
            int conditionNumber = intStart + 8;
            while (true) {
                System.out.println("Enter the last number of the interval (grater than " + conditionNumber + "):");
                try {
                    intEnd = scanner.nextInt();
                    if (intEnd <= conditionNumber) {
                        System.out.println("You must enter a number grater than " + conditionNumber + ".");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.print("You must enter a number.");
                    scanner.nextLine();
                }
            }

            //Get the attempt count - must be>0 and power of 2 of attempt count must be < number count of the interval
            int intNumberCount = intEnd - intStart + 1;
            conditionNumber = (int) Math.ceil(Math.log(intNumberCount) / Math.log(2));
            if (Math.pow(2, conditionNumber - 1) > intNumberCount) {
                conditionNumber--;
            }
            while (true) {
                System.out.println("Enter the attempt count (less than " + conditionNumber + "):");
                try {
                    attemptCountLeft = scanner.nextInt();
                    if (attemptCountLeft <= 0) {
                        System.out.println("You must enter a positive number.");
                    } else if (attemptCountLeft >= conditionNumber) {
                        System.out.println("You must enter an attempt count less than " + conditionNumber + ".");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.print("You must enter a number.");
                    scanner.nextLine();
                }
            }

            //Ask users if they want to see help messages
            while (true) {
                System.out.println("Do you want to see 'greater than', 'less than' messages (y/n)?");
                String s = scanner.next();
                if (!s.equals("y") && !s.equals("n")) {
                    continue;
                }
                help = s.equals("y");
                break;
            }

            Random random = new Random(System.currentTimeMillis());
            numberToGuess = random.nextInt(intNumberCount) + intStart;

            //The actual "guess" part
            while (true) {
                System.out.println("Guess the number I chose from " + intStart + " - " + intEnd);
                System.out.println("Attemp Count Left: " + attemptCountLeft);
                try {
                    currentGuess = scanner.nextInt();

                    if (currentGuess == numberToGuess) {
                        System.out.println("You Win!");
                        break;
                    }

                    System.out.println("That's not correct.");
                    attemptCountLeft--;
                    if (attemptCountLeft <= 0) {
                        System.out.println("You Lose! The number I chose: " + numberToGuess);
                        break;
                    } else if (help) {
                        if (currentGuess > numberToGuess) {
                            System.out.println("The number I chose is smaller then " + currentGuess);
                        } else {
                            System.out.println("The number I chose is bigger than " + currentGuess);
                        }
                    }

                } catch (Exception e) {
                    System.out.print("You must enter a number:");
                    scanner.nextLine();
                }
            }

            //Ask users if they want to play again
            while (true) {
                System.out.println("Do you want to play again?");
                String s = scanner.next();
                if (!s.equals("y") && !s.equals("n")) {
                    continue;
                }
                running = s.equals("y");
                break;
            }
        }
        System.out.println("Goodbye!");
    }
}
