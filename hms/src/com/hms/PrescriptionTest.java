package com.hms;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import com.hms.items.Prescription;

public class PrescriptionTest {
    @Test
    public void createPrescriptionTest() {
        Prescription p1 = new Prescription();
        Prescription p2 = new Prescription();
        Prescription p3 = new Prescription();

        assertEquals(3, p2.prescriptionNumber);
    }

    public static void main(String[] args) {
        Prescription p1 = new Prescription();

        p1.addMedicine("test1", 30, "notes1");
        p1.addMedicine("test2", 40, "notes2");

        p1.readPrescription();
    }
}
