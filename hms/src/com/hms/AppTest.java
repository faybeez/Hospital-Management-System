package com.hms;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.hms.items.AppointmentManager;
import com.hms.items.Appointment.Status;
import com.hms.users.UserManager;

public class AppTest {
    @Test
    public void test() {
        assertEquals(2, 2);
    }
    @Test
    public void test2() {
        assertEquals(2, 1);
    }
    @Test
    public void patientMapTest() {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        assertEquals(1, apptmanager.getPatientMap());
    }
    @Test
    public void doctorMapTest() {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        assertEquals(apptmanager.getDoctorMap(), apptmanager.getDoctorMap());
    }
    @Test
    public void apptMapTest() {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        assertEquals(apptmanager.getApptMap(), apptmanager.getApptMap());
    }


    public static void main(String[] args) {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();

        //apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails(usermanager,true);
        //apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails();
        //apptmanager.printAppts(apptmanager.getPatientAppts(1020000011, Status.Completed), usermanager);
    }
}
