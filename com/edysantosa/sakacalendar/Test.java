package com.edysantosa.sakacalendar;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test{
    public static void main(String[] args)
    {
        Test test = new Test();


        SakaCalendar a = new SakaCalendar(1970, 0, 1);
        SakaCalendar b = new SakaCalendar(2100, 0, 1);

        long startTime = 0;
        long finishTime = 0;

        startTime = System.currentTimeMillis();
        int x = a.getDateDiff(b,a);
        finishTime = System.currentTimeMillis();
        System.out.println("New took: "+(finishTime-startTime)+ " ms");







        System.out.println(y);
        System.out.println(x);


    }
}