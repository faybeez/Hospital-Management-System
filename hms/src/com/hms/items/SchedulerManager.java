package com.hms.items;

import java.util.Iterator;
import java.util.Map;

import com.hms.dao.Dao;
import com.hms.dao.ScheduleDao;
import com.hms.users.Doctor;
import com.hms.users.UserManager;

public class SchedulerManager {
    private Map<Integer, Scheduler> schedules;
    private static final String schedulerDB = "hms/resources/schedulerdb.txt";

    public SchedulerManager(UserManager um) {
        Dao<Scheduler> reader = new ScheduleDao();
        Scheduler s;

        try {
            schedules = reader.read(schedulerDB);
        } catch (Exception e) {
            System.out.println("Scheduler Manager " + e);
        }
        
        Iterator<Scheduler> i = schedules.values().iterator();
        while(i.hasNext()) {
            s = i.next();
            //System.out.println("name: " + ((Doctor)(um.getUserFromID(s.getDoctorID()))).getName());
            //s.printSchedule();
            //System.out.println("AFTER");
            ((Doctor)(um.getUserFromID(s.getDoctorID()))).setSchedule(s);
            //System.out.println(((Doctor)(um.getUserFromID(s.getDoctorID()))).getName());
            //s.printSchedule();
        }
    }

    public void addToSchedules(int id, Scheduler s) {
        schedules.put(id, s);
    }

    public void saveSchedules() {
        Dao<Scheduler> writer = new ScheduleDao();
        
        try {
            writer.save(schedulerDB, schedules.values());
        } catch (Exception e) {
            System.out.println("scheduler Manager " + e);
        }
    }
    
}
