package com.shriram;

public class handleSessions {

    public void regularPomo(int sessions) throws InterruptedException {
        int session = 1;
        timer timer = new timer();

        while(session <= sessions){
            timer.workTimer(25, session);

            if(session % 4 == 0){
                timer.breakTimer(false,5,15);
            }else{
                timer.breakTimer(true,5,15);
            }

            session++;
        }

        System.out.println("Pomodoro complete!");
    }


    public void customPomo(int workTime, int shortBreakTime, int longBreakTime, int longBreakAfter, int sessions) throws InterruptedException {
        int session = 1;
        timer timer = new timer();

        while(session <= sessions){
            timer.workTimer(workTime, session);

            if(session % longBreakAfter == 0){
                timer.breakTimer(false, shortBreakTime, longBreakTime);
            }else{
                timer.breakTimer(true, shortBreakTime, longBreakTime);
            }

            session++;
        }

        System.out.println("Pomodoro complete!");
    }
}
