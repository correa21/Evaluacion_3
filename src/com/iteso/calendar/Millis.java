/** TODO check add method*/

package com.iteso.calendar;

public class Millis extends DateTime{
public static final int YEAR = 0;
public static final int MONTH = 1;
public static final int DAY = 2;
public static final int HOUR = 3;
public static final int MINUTE = 4;
public static final int SECOND = 5;
public static final int MILLIS = 6;

    String[] short_month_lookup = { " ","Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep",
            "Oct", "Nov", "Dic" };

    String[] month_lookup = { " ","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiempbre",
            "Octubre", "Noviembre", "Diciembre" };

    private long mills = 0;
    private long timeStamp = 0;
    private int format_select = 0;
    private String format_str = "";


    public Millis(){
        getDate(System.currentTimeMillis());
        setFormat(0);

    }
    public Millis(long millis){
        if(1000 >= millis) {
            setMillis(millis);
        }
        else{
            this.getDate(millis);
        }
        setFormat(0);
    }
    public Millis(int day, int month, int year) {
        super(day, month, year);
        setFormat(0);
    }
    public Millis(int hours, int min, int sec, int day, int month, int year) {
        super(hours, min, sec, day, month, year);
        setFormat(0);
    }
    public Millis(int hours, int min, int sec, long millis, int day, int month, int year) {
        super(hours, min, sec, day, month, year);
        setMillis(millis);
        setFormat(0);
    }
    public void setMillis(long mills) {
        if (1000 >= mills){
            this.mills = mills;
        }
        else{
            getDate(mills);
        }
    }
    public long getMills() {
        return mills;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        getDate(timeStamp);
    }
    public long getTimeStamp() {

        long millYear, millMonth, millDay, millHour, millMin, millSec, millMill;
        int i;
        long tmpMonth = 0; long tmpYear = 0;

        millMill = getMills();
        millSec = (getSeconds()*1_000) + millMill;
        millMin = (getMinutes()*60*1_000) + millSec;
        millHour = (getHours()*60*60*1_000) + millMin;
        millDay = ((getDay())*24*60*60*1_000)+millHour;
        for (i = 1; i < this.getMonth() ; i++) {
            tmpMonth += this.monthVerify(i);
        }
        millMonth = (tmpMonth*24*60*60*1_000)+millDay;
        for(int h = 1970; h < this.getYear() ;h++){
            if(this.isLeap(h))
                tmpYear += 366;
            else
                tmpYear += 365;
        }
        millYear = (tmpYear*24*60*60*1_000)+millMonth;// ts = 1544931952785

        //System.out.println(millYear);

        return (millYear);
    }
    private void getDate(long timeStamp){
        long tmpTimestamp = timeStamp;
        long yearCompare = 0;
        int i;
        int h=0;
        long a;
        long tmpMonth = 0; long tmpYear = 0;

        for(h = 1970; yearCompare < tmpTimestamp ;h++){
            if(this.isLeap(h)) {
                tmpTimestamp -= (366 * 24 * 60 * 60 * 1000L);
                yearCompare = 366 * 24 * 60 * 60 * 1000L;
            }
            else {
                tmpTimestamp -= (365 * 24 * 60 * 60 * 1000L);
                yearCompare = 365 * 24 * 60 * 60 * 1000L;
            }
        }
        this.setYear(h);

        for(i = 1; (monthVerify(i) * 24 * 60 * 60 * 1000L) < tmpTimestamp ; i++) {
            i = ((i%13) == 0) ? 1 : i;
            a = (tmpTimestamp - (monthVerify(i) * 24 * 60 * 60 * 1000L));
            if(a > 86_400_000L)
            {
                tmpTimestamp -= (monthVerify(i) * 24 * 60 * 60 * 1000L);
            }
            else{
                i = 12;
                break;
            }
                    //System.out.println("\nEL VALOR DE L ES: "+a+"\n");
        }

        this.setMonth(i);
        //System.out.println("\nNUEVO TS "+tmpTimestamp+"\n");
        this.setDay((int)((tmpTimestamp/24/60/60/1000)));

        this.mills = tmpTimestamp%1_000;
        this.setSeconds((int)((tmpTimestamp)/1000)%60);
        this.setMinutes((int)((tmpTimestamp/1_000)/60)%60);
        this.setHours((int)(((((tmpTimestamp/1000/60)/60))%24)));
        this.setFormat(format_select);
        //System.out.println(millYear);
    }
    public void add(int select, int value) {
        long tmpMonth = 0;
        long tmpYear = 0;
        long tmpTimeStamp = this.getTimeStamp();
        int h = 1;

        switch (select) {
            case YEAR:
                for(h = 1970; h < (this.getYear()+value) ;h++){
                    if(this.isLeap(h))
                        tmpYear += 366;
                    else
                        tmpYear += 365;
                }
                tmpTimeStamp += tmpYear*24*60*60*1000;
                break;
            case MONTH:
                for(int i = 1 ; i < (getMonth()+value) ; i++) {
                    if(0 == h)
                        h=1;
                    else
                        h = i%13;

                    tmpMonth += monthVerify(h);
                    h++;
                }
                tmpTimeStamp += tmpMonth*24*60*60*1000;
            break;
            case DAY:
                tmpTimeStamp += value*24*60*60*1000;
            break;
            case HOUR:
                tmpTimeStamp += value*60*60*1000;
            break;
            case MINUTE:
                tmpTimeStamp += value*60*1000;
            break;
            case SECOND:
                tmpTimeStamp += value*1000;
            break;
            case MILLIS:
                tmpTimeStamp += value;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + select);
        }
        timeStamp = tmpTimeStamp;
        getDate(timeStamp);
    }
    public void setFormat(int format){
        format_select = format;
        switch (format) {
            case 0 -> format_str = "[" + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds() + "." + this.getMills() + "]" +
                    "   " + this.getDay() + "/" + this.getMonth() + "/" + this.getYear() + "/";
            case 1 -> format_str = "[" + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds() + "]" + "    " +
                    this.getDay() + "-" + this.short_month_lookup[this.getMonth()] + "-" + this.getYear() +
                    "   " + "(" + this.getTimeStamp() + ")";
            case 2 -> format_str = "[" + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds() + "]" + "    " +
                    this.getDay() + " de " + this.month_lookup[this.getMonth()] + " de " + this.getYear() +
                    "   " + "(" + this.getTimeStamp() + ")";
        }
    }
    public String toString(){
        return this.format_str;
    }
    public void next(){
        this.timeStamp+=1L;
        setFormat(this.format_select);
    }
    public boolean isBefore(Millis date) {
        boolean isBeforeFlag = (date.getTimeStamp() > this.getTimeStamp()) ? true : false;
        return isBeforeFlag;
    }
    public boolean isAfter(Millis date) {
        boolean isAfterFlag = (date.getTimeStamp() < this.getTimeStamp()) ? true : false;
        return isAfterFlag;
    }
    public static long timestampOf(Date d){
            Millis m1 = new Millis(d.getDay(), d.getMonth(), d.getYear());
            return m1.getTimeStamp();
    }
    public static long timestampOf(DateTime dt){
            Millis m1 = new Millis(dt.getHours(), dt.getMinutes(), dt.getSeconds(), dt.getDay(), dt.getMonth(), dt.getYear());
            return m1.getTimeStamp();
    }
}
