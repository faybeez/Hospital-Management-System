package com.hms.items;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Scheduler {

    private LocalDate lastSaved;
    private final LocalTime start = LocalTime.of(8,0);
    private final int row = 7;
    private final int col = 20; 

    //7 columns (up to 1 week), 20 rows (30 minute intervals from 8 AM to 6 PM)
    int[][] schedule = {{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};

    public Scheduler() {
        lastSaved = LocalDate.now();
    }

    public void printSchedule() {
        int day = 0;
        LocalTime t = LocalTime.of(8,0);
        System.out.printf("%n%26s%s%26s%n%s%n","","DOCTOR SCHEDULER","","---------------------------------------------------------------------");
        for(int i = 0; i < col; i++) {
            System.out.printf("%s ",t);
            for(int j = 0; j < row; j++) {
                if(schedule[j][i] == -1) {
                    System.out.printf("|%-3s%1s%3s|", " ", "", " ");
                }
                else if(schedule[j][i] == 0) {
                    System.out.printf("|%-3s%1s%3s|", " ", "x", " ");
                }
                else {
                    System.out.printf("|%-3s%1s%3s|", " ", "o", " ");
                }
            }
            System.out.println();
            day++;
            t = t.plusMinutes(30);
        }
    }

    public void updateLastSaved() {

        int dateDifference;
        if(lastSaved != LocalDate.now()) {
            dateDifference = LocalDate.now().compareTo(lastSaved);

            //deleting past date data
            for(int i = 0; i < dateDifference; i++) {
                for(int j = 0; j < col; j++) {
                    schedule[i][j] = -1;
                } 
            }

            //moving calendar back
            for(int i = 0; i < row - dateDifference; i++) {
                for(int j = 0; j < col; j++) {
                    if(schedule[i + dateDifference][j] != -1) {
                        schedule[i][j] = schedule[i + dateDifference][j];
                        schedule[i + dateDifference][j] = -1;
                    }
                }
            }

            lastSaved = LocalDate.now();
        }
    }

    public void setUnavailable(LocalDate d, LocalTime t) {
        int date = d.compareTo(lastSaved);

        if(date < 0) {
            System.out.println("Date has already passed!");
            return;
        }

        long minutes = MINUTES.between(start, t);
        schedule[date][(int)minutes/30] = 0;
    }

    //assuming doctor is free
    public void addAppointmentToSchedule(Appointment a) {
        int id = a.getAppointmentID();

        int date = a.getDate().compareTo(lastSaved);

        if(date < 0) {
            System.out.println("Date has already passed!");
            return;
        }

        long minutes = MINUTES.between(start, a.getTime());
        schedule[date][(int)minutes/30] = id;
    }

    public boolean checkIfFree(Appointment a) {
        //appointment checks alr if the date is valid
        int r = a.getDate().compareTo(lastSaved);
        int c = (int)MINUTES.between(start, a.getTime()) / 30;
        if(schedule[r][c] == -1) {
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Integer> getAppointmentIDs() {

        ArrayList<Integer> apptID = new ArrayList<Integer>(); 

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(schedule[i][j] != -1) {
                    apptID.add(schedule[i][j]);
                }
            }
        }

        return apptID;
    }
}
