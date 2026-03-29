package com.shriram;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Pomodoro {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        HandleSessions handleSessions = new HandleSessions();

        while(true){
            System.out.println();
            System.out.println("---------------Menu---------------");
            System.out.println("1. Regular pomodoro");
            System.out.println("2. Custom pomodoro");
            System.out.println("3. Check stats");
            System.out.println("4. End program");
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
                    System.out.println("1. Today's stats");
                    System.out.println("2. Overall stats");
                    System.out.print("Enter your choice: ");
                    int ch = sc.nextInt();

                    if(ch == 1) {
                        String query = "SELECT SUM(duration_minutes) AS today_focus, COUNT(ID) AS count_id FROM sessions WHERE type = 'WORK' AND DATE(start_time) = CURRENT_DATE";

                        try (Connection conn = new Timer().connect();
                             Statement statement = conn.createStatement();
                             ResultSet rs = statement.executeQuery(query)) {
                            if (rs.next()) {
                                int todayDuration = rs.getInt("today_focus");
                                int NumberOfSessions = rs.getInt("count_id");

                                System.out.println();
                                System.out.println("Today's stats: ");
                                System.out.println("Number of work sessions: " + NumberOfSessions);
                                System.out.println("Total Duration: " + todayDuration + " minutes");
                                System.out.println();
                            }
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if(ch == 2){
                        String query = "SELECT SUM(duration_minutes) AS total_duration, COUNT(id) AS total_sessions FROM sessions WHERE type='WORK'";

                        try(Connection conn = new Timer().connect();
                            Statement statement = conn.createStatement();
                            ResultSet rs = statement.executeQuery(query)){

                            if(rs.next()){
                                int totalDuration = rs.getInt("total_duration");
                                int totalSessions = rs.getInt("total_sessions");

                                System.out.println();
                                System.out.println("Overall stats:");
                                System.out.println("Number of sessions: "+ totalSessions);
                                System.out.println("Total Duration: "+ totalDuration);
                                System.out.println();
                            }
                            break;
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else{
                        System.out.println("Invalid choice!");
                        break;
                    }

                case 4:
                    System.exit(0);

                default:
                    System.out.println("Enter a valid choice!");
            }
        }
    }
}
