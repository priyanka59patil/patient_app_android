package com.werq.patient.Utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static String dateFormatmmddyyyy(Date date) throws ParseException {

        String outputPattern = "MMMM dd',' yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        //Date date1=outputFormat.parse(dateString);
        String cdate = outputFormat.format(date);
        return cdate;
    }

    public static String dateFormatMMMddyyyy(Date date) throws ParseException {

        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        //Date date1=outputFormat.parse(dateString);
        String cdate = outputFormat.format(date);
        return cdate;
    }

    public static Date dateFromUtc(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;

    }



    public static String dayFromDate(Date date, String returnFormat) {
        String getString = null;
        try {
            switch (returnFormat) {


                case "day":
                    getString = (String) DateFormat.format("dd", date);

                    break;
                case "month":
                    getString = (String) DateFormat.format("MMM", date);
                    break;
                case "time":
                    getString = (String) DateFormat.format("hh:mm a", date);
                    break;

            }
        }
        catch (Exception e){
            getString="";
        }

        return getString;

    }


}
