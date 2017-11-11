package com.example.nathan.checmicalcalculation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Nathan on 10/30/2017.
 */

public class Record {
    private List<String> log;
    private List<Date> logTime;

    Record() {
        log = new ArrayList<>();
        logTime = new ArrayList<>();
    }

    void displayLog() {
        for (int i = 0; i < log.size(); i++) {
            System.out.print(logTime.get(i) + " - " + log.get(i));
        }
    }

    void addToLog(String toAdd) {
        Date currentTime = Calendar.getInstance().getTime();
        log.add(toAdd);
        logTime.add(currentTime);
    }

    void deleteMostRecent() {
        int loc = log.size() - 1;
        log.remove(loc);
        logTime.remove(loc);
    }

    void getLogEntry() {

    }
}
