package com.shriram;

import java.util.Scanner;


public class Pomodoro {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        handleSessions handleSessions = new handleSessions();

        System.out.println("Choose your preferred pomodoro type");
        while(true){
            System.out.println("1. Regular pomodoro");
            System.out.println("2. Custom pomodoro");
            System.out.println("3. End program");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();

            switch (choice){
                case 1:
                    System.out.print("Enter number of sessions: ");
                    int sessions = sc.nextInt();
                    handleSessions.regularPomo(sessions);
                    break;

                case 2:
                    System.out.print("Enter work time: ");
                    int workTime = sc.nextInt();
                    System.out.println();

                    System.out.print("Number of sessions: ");
                    int customSession = sc.nextInt();
                    System.out.println();

                    System.out.print("Enter short break time: ");
                    int shortBreakTime = sc.nextInt();
                    System.out.println();

                    System.out.print("Enter long break time: ");
                    int longBreakTime = sc.nextInt();
                    System.out.println();

                    System.out.print("After how many sessions should long break commence? : ");
                    int longBreakAfter = sc.nextInt();
                    System.out.println();

                    handleSessions.customPomo(workTime,shortBreakTime,longBreakTime,longBreakAfter,customSession);
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Enter a valid choice!");
            }
        }
    }
}
