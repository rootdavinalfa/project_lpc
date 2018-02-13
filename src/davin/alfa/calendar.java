/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package davin.alfa;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class calendar {
    public int Year;
    public int Month,Day,sec,min,hour;
    public String getKodeProduksi(){
        int m = getMonth()+1;
        String a = new String().valueOf(getYear()+m+getDay());
        return a;

    }
    public String getShift(){
        int s = getHour();
        String a = null;
        if(s >= 8 && s<=15){
            a = "1";

        }
        else if (s >=16 && s<=23){
            a = "2";

        }
        else if (s >= 0 && s <= 7){
            a = "3";

        }
        return a;
    }
    public void getNow() {
        /*
                    Calendar cal = new GregorianCalendar();
                    int Year = cal.get(Calendar.YEAR);
                    int Month = cal.get(Calendar.MONTH);
                    int Day = cal.get(Calendar.DATE);
                    int sec = cal.get(Calendar.SECOND);
                    int min = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    this.Year = Y;
                    this.Month = M;
                    this.Day = D;
                    this.hour = H;
                    this.min = m;int Y,int M,int D,int H,int m,int s
                    this.sec = s;*/
        /*
        int Year = getYear();
        int Month = getMonth();
        int Day = getDay();
        int Hour =getHour();
        int Min = getMin();
        int Sec = getSec();
        */


    }
    public int getYear(){
        Calendar cal = new GregorianCalendar();
        int Year = cal.get(Calendar.YEAR);
        return Year;
    }
    public int getMonth(){
        Calendar cal = new GregorianCalendar();
        int Month = cal.get(Calendar.MONTH);
        return Month;
    }
    public int getDay(){
        Calendar cal = new GregorianCalendar();
        int Day = cal.get(Calendar.DAY_OF_MONTH);
        return Day;
    }
    public int getHour(){
        Calendar cal = new GregorianCalendar();
        int Hour = cal.get(Calendar.HOUR_OF_DAY);
        return Hour;
    }
    public int getMin(){
        Calendar cal = new GregorianCalendar();
        int min = cal.get(Calendar.MINUTE);
        return min;
    }
    public int getSec(){
        Calendar cal = new GregorianCalendar();
        int sec = cal.get(Calendar.SECOND);
        return sec;
    }
}