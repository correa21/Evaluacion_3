package iteso.calendar;

public class Date {

    private int day=1, month=1, year = 2000;
    private String mName;
    public Date(int day, int month, int year){
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public Date() {

    }

    public boolean isLeap(int year) {
        boolean isLeapYear = (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0)) ? true:false;
        return isLeapYear;
    }
    public boolean isLeap() {
        boolean isLeapYear = (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0)) ? true:false;
        return isLeapYear;
    }
    public void setYear(int year){
         this.year = (year >= 1900 && year <= 3000) ? year : this.year;
     }
    public void setMonth(int month){

        if(month >= 1 && month <= 12)
            this.month = month;
    }
    public void setDay(int day){
        if(day >= 1 && day <= monthVerify(this.month))
            this.day = day;
    }
    public int monthVerify(int month) {
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if(isLeap())
                    return 29;
                else
                    return 28;
            default:
                return 0;
        }
    }
    public void setmName (String mName){
        this.mName = mName;
    }
    public int getDay (){ return day; }
    public int getMonth (){ return month; }
    public int getYear () { return year; }
    public String getmName () { return mName; }

}
