package com.hms.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.hms.App;
import com.hms.users.Doctor;
import com.hms.readwrite.TextDB;
import com.hms.users.UserManager;

public class SchedulerManager {
    private Map<Integer, Scheduler> schedules;

    public SchedulerManager(UserManager um) {
        TextDB reader = new TextDB();
        Scheduler s;

        try {
            schedules = reader.readSchedules(App.schedulerDB);
        } catch (Exception e) {
            System.out.println("Scheduler Manager " + e);
        }

        Iterator<Scheduler> i = schedules.values().iterator();

        while(i.hasNext()) {
            s = i.next();
            ((Doctor)um.getUserFromID(s.getDoctorID())).setSchedule(s);
        }
    }
}
