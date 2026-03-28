package com.shriram;

public class handleSessions {

    public void regularPomo(int sessions) throws InterruptedException {
        int session = 1;
        timer timer = new timer();

        while(session <= sessions){
            timer.workTimer(25, session);

            if(session % 4 == 0){
                timer.breakTimer(false);
            }else{
                timer.breakTimer(true);
            }

            session++;
        }

        System.out.println("Pomodoro complete!");
    }


    public void customPomo(int workTime, int shortBreakTime, int longBreakTime, int longBrakeAfter, int sessions) throws InterruptedException {
        int session = 1;
        timer timer = new timer();

        while(session <= sessions){
            timer.workTimer(workTime, session);

            if(session % longBrakeAfter == 0){
                timer.breakTimer(false, shortBreakTime, longBreakTime);
            }else{
                timer.breakTimer(true, shortBreakTime, longBreakTime);
            }

            session++;
        }

        System.out.println("Pomodoro complete!");
    }
}
