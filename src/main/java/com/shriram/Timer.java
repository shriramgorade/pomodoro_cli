package com.shriram;

import javax.sound.sampled.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class Timer {

    public void startTimer(int minutes, String type) throws InterruptedException {
        int totalSeconds = minutes * 60;

        LocalDateTime startLocalTime = LocalDateTime.now(); //start time to be stored in db

        for(int countdown = totalSeconds; countdown >= 0; countdown--){
            int min = countdown / 60;
            int sec = countdown % 60;

            System.out.print("\r⏱ "+ String.format("%02d : %02d",min,sec));

            /*
            \r moves cursor to start of same line
            print() stays on same line
            */

            //progress bar
            int elapsed = totalSeconds-countdown;
            double percent = (double) elapsed/totalSeconds;
            int filled = (int)(percent*20); //20 slots bar
            String bar = "█".repeat(filled) + "░".repeat(20-filled);

            System.out.print(" ["+ bar+"]");

            //force print output
            System.out.flush();

            //delay of 1 sec
            Thread.sleep(1000);
        }

        //indication of timer completion
        playSound();

        LocalDateTime endLocalDateTime = LocalDateTime.now();  //end time to be stored in db
        saveSession(startLocalTime,endLocalDateTime,minutes,type);

    }

    public void workTimer(int minutes, int session) throws InterruptedException {
        System.out.println("Session: "+ session);
        startTimer(minutes, "WORK");

        System.out.println();
        System.out.println("Session completed");
    }

    public void breakTimer(boolean shortBreak, int shortBreakTime, int longBreakTime) throws InterruptedException {
        if(shortBreak){
            System.out.println("Short break");
            startTimer(shortBreakTime,"SHORT_BREAK");
        }else{
            System.out.println("Long Break");
            startTimer(longBreakTime, "LONG_BREAK");
        }

        System.out.println();
        System.out.println("Break completed");
    }

    public void playSound(){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sounds/beep-04.wav")
            );

            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connect() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/pomodoro";
        String username = "postgres";
        String pass = "your_pass";
        return DriverManager.getConnection(url,username,pass);
    }

    public void saveSession(LocalDateTime start, LocalDateTime end, int duration, String type){
        String query = "insert into sessions(start_time,end_time,duration_minutes,type) values(?,?,?,?)";

        try(Connection conn = connect()){
            PreparedStatement prepStat = conn.prepareStatement(query);

            prepStat.setObject(1,start);
            prepStat.setObject(2,end);
            prepStat.setInt(3,duration);
            prepStat.setString(4,type);

            prepStat.executeUpdate();

        }catch(Exception e){
            System.out.println("DB ERROR: "+ e.getMessage());
        }
    }
}
