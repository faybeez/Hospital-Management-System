package com.hms;
import java.io.ByteArrayInputStream;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import java.util.Scanner;

import com.hms.users.Doctor;
import com.hms.users.User.BloodType;
import com.hms.users.User.Gender;

public class DoctorTest {
    //@Test
    public static void main(String[] args) {
        Doctor d = new Doctor(1, "test", "2024-01-12", Gender.FEMALE, BloodType.ABMINUS, "test1", "test1");
        Scanner sc = new Scanner(System.in);
        d.UpdateUnavailable(sc);

        d.getSchedule().printSchedule();

    }
}
