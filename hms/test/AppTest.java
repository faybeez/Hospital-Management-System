package test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.junit.Assert;
import org.junit.Test;

import com.hms.items.AppointmentManager;
import com.hms.items.SchedulerManager;
import com.hms.items.Appointment.Status;
import com.hms.users.UserManager;
import com.hms.users.Doctor;

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
        assertEquals(1, apptmanager.getDoctorMap());
    }
    @Test
    public void apptMapTest() {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        assertEquals(1, apptmanager.getApptMap());
    }


    public static void main(String[] args) {
        UserManager usermanager = new UserManager();
        AppointmentManager apptmanager = new AppointmentManager();
        SchedulerManager smanager = new SchedulerManager(usermanager);
        
        ((Doctor)usermanager.getUserFromName("jonas")).printSchedule();
        

        //apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails(usermanager,true);
        //apptmanager.getAppointmentFromID(2000000009).printAppointmentDetails();
        //apptmanager.printAppts(apptmanager.getPatientAppts(1020000011, Status.Completed), usermanager);
    }
}
