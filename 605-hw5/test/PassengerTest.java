package test;

import Passenger.Passenger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PassengerTest {
    @Test
    public void testtoString(){
        Passenger p1 = new Passenger("Bob",'A',1);
        Assertions.assertEquals("Bob in seat A1",p1.toString());
    }

    @Test
    public void testCompare(){
        Passenger p1 = new Passenger("Amoonguss",'A',1);
        Passenger p2 = new Passenger("Butterfree",'B',2);
        Passenger p3 = new Passenger("Cresselia",'C',1);
        Passenger p4 = new Passenger("Darkrai",'A',2);

        Assertions.assertEquals(true,p1.compareTo(p2) > 0);
        Assertions.assertEquals(true,p1.compareTo(p4) > 0);
        Assertions.assertEquals(true,p2.compareTo(p4) < 0);
        Assertions.assertEquals(true,p4.compareTo(p3) > 0);
    }
}