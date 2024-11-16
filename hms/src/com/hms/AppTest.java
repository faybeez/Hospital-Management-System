package com.hms;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.hms.items.AppointmentManager;
import com.hms.items.Appointment.Status;

public class AppTest {
    @Test
    public void test() {
        assertEquals(2, 2);
    }
    @Test
    public void test2() {
        assertEquals(2, 1);
    }

    public static void main(String[] args) {
        AppointmentManager apptmanager = new AppointmentManager();

        apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails();
        //apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails();
        //apptmanager.printAppts(apptmanager.getPatientAppts(1020000011, Status.Completed));
    }
}
