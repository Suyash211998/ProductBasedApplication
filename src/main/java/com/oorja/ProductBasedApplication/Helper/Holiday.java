package com.oorja.ProductBasedApplication.Helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Holiday {
    enum PublicHoliday {

        //republic day 26 jan
        republic("01-26"),

        //earth day 12 june
        earthDay("06-12");

        //test 1
       // Holiday1("05-10"),

        //test 2
        //Holiday2("05-11");

        private final String dayInString;

        PublicHoliday(String day)
        {
            this.dayInString =day;
        }

        public String getHoliday()
        {
            return this.dayInString;
        }
    }


    public static boolean isPublicDay() {

        Date date =Calendar.getInstance().getTime();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String dateInString=df.format(date);

        for(PublicHoliday x : PublicHoliday.values()){
            String holidayValue= x.getHoliday();

            if(holidayValue.equals(dateInString.substring(5,10))){
                System.out.println("its Holiday");
                return true;
            }
        }
        return false;

    }

}