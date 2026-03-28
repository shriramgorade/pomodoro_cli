package com.shriram;

public class timer{

    public void startTimer(int minutes) throws InterruptedException {
        int totalSeconds = minutes * 60;
        for(int countdown = totalSeconds; countdown >= 0; countdown--){
            int min = countdown / 60;
            int sec = countdown % 60;

            System.out.print("\r"+ min +" : "+ sec);

            /*
            \r moves cursor to start of same line
            print() stays on same line
            */

            //force print output
            System.out.flush();

            //delay of 1 sec
            Thread.sleep(1000);
        }
    }

    public void workTimer(int minutes, int sessions) throws InterruptedException {
        for(int session = 1; session <= sessions; session++){
            System.out.println("Session: "+ session);
            startTimer(minutes);
            System.out.println("Session completed");
        }
    }

    public void breakTimer(boolean shortBreak) throws InterruptedException {
        if(shortBreak == true){
            System.out.println("Short break");
            startTimer(5);
        }else{
            System.out.println("Long Break");
            startTimer(15);
        }

        System.out.println("Break completed");
    }



}
