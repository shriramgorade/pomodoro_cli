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
                    System.out.println("");
            }
        }
    }
}
