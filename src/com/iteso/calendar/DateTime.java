package com.iteso.calendar;

public class DateTime extends Date{
    private int hours, minutes, seconds;

    public DateTime() {
        super();
    }

    public DateTime(int day, int month, int year) {
        super(day, month, year);
    }

    public DateTime(int hours, int minutes, int seconds,int day, int month, int year) {
        super(day, month, year);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }
/*
    public DateTime(int hours, int minutes, int seconds)
    {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }
*/
    public void setHours(int hours) {
        if(23 >= hours) {
            this.hours = hours;
        }
        else{
            this.hours = (hours%24);
            setDay((hours/24)+getDay());
        }
    }

    public void setMinutes(int minutes) {
        if(59 >= minutes) {
            this.minutes = minutes;
        }
        else{
            this.minutes = (minutes%60);
            setHours((minutes/60)+this.hours);
        }
    }

    public void setSeconds(int seconds) {
        if(59 >= seconds) {
            this.seconds = seconds;
        }
        else{
            this.seconds = (seconds%60);
            setMinutes((seconds/60)+this.minutes);
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
