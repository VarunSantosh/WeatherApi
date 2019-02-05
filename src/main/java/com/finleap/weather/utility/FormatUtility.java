package com.finleap.weather.utility;

import com.finleap.weather.model.Temperature;
import com.finleap.weather.model.WeatherResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtility {

    public static WeatherResponse mapToResponse(String result) throws ParseException {
        WeatherResponse response = new WeatherResponse();
        Temperature temperature = new Temperature();

        String curDate = getCurrentDate();
        String day1 = getNextDate(curDate);
        String day2 = getNextDate(day1);
        String day3 = getNextDate(day2);

        Double dTemp = 0d;
        Double nTemp = 0d;
        Double pres = 0d;
        int tDayCounter = 0;
        int tNightCounter = 0;
        int pCounter = 0;


        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);

        JSONArray tempAndPreArray = (JSONArray) json.get("list");


        for (int i = 0; i < tempAndPreArray.size(); i++) {
            JSONObject obj = (JSONObject) tempAndPreArray.get(i);
            String dateAndTime = (String) obj.get("dt_txt");
            String[] dateTimeArr = dateAndTime.split("\\s+");
            if (dateTimeArr[0].equals(day1) || dateTimeArr[0].equals(day2) || dateTimeArr[0].equals(day3)) {
                JSONObject main = (JSONObject) obj.get("main");
                if (dateTimeArr[1].substring(0, 2).equals("06") || dateTimeArr[1].substring(0, 2).equals("09") || dateTimeArr[1].substring(0, 2).equals("12") || dateTimeArr[1].substring(0, 2).equals("15") || dateTimeArr[1].substring(0, 2).equals("18")) {
                    //increasing day time temp
                    dTemp += Double.parseDouble(String.valueOf(main.get("temp")));
                    tDayCounter++;
                } else {
                    //increasing night time temp
                    nTemp += Double.parseDouble(String.valueOf(main.get("temp")));
                    tNightCounter++;
                }
                //increasing pressure
                pres += Double.parseDouble(String.valueOf(main.get("pressure")));
                pCounter++;
            }
        }

        Double avgDayTemp = (dTemp / tDayCounter) - 273;
        Double avgNightTemp = (nTemp / tNightCounter) - 273;
        Double avgPressure = pres / pCounter;

        temperature.setDayTemp(new DecimalFormat("##.##").format(avgDayTemp));
        temperature.setNightTemp(new DecimalFormat("##.##").format(avgNightTemp));

        response.setTemperature(temperature);
        response.setPressure(new DecimalFormat("##.##").format(avgPressure));

        return response;
    }

    public static String getNextDate(String reqDate) {
        String nextDate = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(reqDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            nextDate = format.format(today.getTime());
        } catch (Exception e) {
            return nextDate;
        }
        return nextDate;
    }

    private static String getCurrentDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

}
