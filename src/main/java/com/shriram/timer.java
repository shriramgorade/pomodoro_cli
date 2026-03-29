package com.shriram;

import javax.sound.sampled.*;

public class timer{

    public void startTimer(int minutes) throws InterruptedException {
        int totalSeconds = minutes * 60;
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
    }

    public void workTimer(int minutes, int session) throws InterruptedException {
        System.out.println("Session: "+ session);
        startTimer(minutes);

        System.out.println();
        System.out.println("Session completed");
    }

    public void breakTimer(boolean shortBreak, int shortBreakTime, int longBreakTime) throws InterruptedException {
        if(shortBreak){
            System.out.println("Short break");
            startTimer(shortBreakTime);
        }else{
            System.out.println("Long Break");
            startTimer(longBreakTime);
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
}
