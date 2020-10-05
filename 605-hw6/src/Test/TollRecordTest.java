package Test;

import Toll.*;
import org.junit.jupiter.api.Test;
import java.lang.*;

import static org.junit.jupiter.api.Assertions.*;
/**
 * test class for TollRecord
 * tested methods are depart(),equals(),toString,hashCode(),and Compareto()
 *
 * @author Steve Gao, sg2369@rit.edu
 * @author Qiwen Quan, qq5575@g.rit.edu
 */
class TollRecordTest {

    @Test
    public void testdepart() throws Exception {
        TollRecord t1 = new TollRecord(60,"DR_J",45);
        TollRecord t2 = new TollRecord(61,"I_AM_STROOT",45);
        t1.depart(150,39);
        assertEquals("[       DR_J] on #45, time    60; off #39, time   150",t1.report());
        t2.depart(100,39);
        assertEquals("[I_AM_STROOT] on #45, time    61; off #39, time   100",t2.report());
        assertEquals("2.46",String.format("%.2f",t1.getToll()));
        assertEquals("2.46",String.format("%.2f",t2.getToll()));
    }

    @Test
    void testEquals() throws Exception {
        TollRecord t1 = new TollRecord(60,"DR_J",45);
        t1.depart(150,39);
        TollRecord t2 = new TollRecord(61,"I_AM_STROOT",45);
        t2.depart(100,39);
        TollRecord t3 = new TollRecord(60,"DR_J",45);
        t3.depart(150,39);
        assertFalse(t1.equals(t2));
        assertTrue(t1.equals(t3));
    }

    @Test
    public void testToString() throws Exception {
        TollRecord t1 = new TollRecord(60,"DR_J",45);
        assertTrue("[DR_J]{(45,60)}".equals(t1.toString()));
        t1.depart(150,39);
        assertTrue("[DR_J]{(45,60),(39,150)}".equals(t1.toString()));
    }

    @Test
    void testHashCode() throws Exception {
        TollRecord t1 = new TollRecord(60,"DR_J",45);
        t1.depart(150,39);
        TollRecord t2 = new TollRecord(61,"I_AM_STROOT",45);
        t2.depart(100,39);
        TollRecord t3 = new TollRecord(60,"DR_J",45);
        t3.depart(150,39);
        assertEquals(t1.hashCode(),t3.hashCode());
        assertTrue(t1.equals(t3));
    }

    @Test
    void testcompareTo() throws Exception {
        TollRecord t1 = new TollRecord(10,"DR_WHO",45);
        TollRecord t2 = new TollRecord(61,"I_AM_STROOT",45);
        TollRecord t3 = new TollRecord(62,"BEN_KENOBI",45);
        t1.depart(26,19);
        t2.depart(100,39);

        assertTrue(t1.compareTo(t2)<0);
        assertTrue(t2.compareTo(t3)<0);
        assertTrue(t1.compareTo(t3)<0);
    }
}