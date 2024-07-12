package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//Citation: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Sensor open at door");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("Sensor open at door", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
	}

    @Test
    void testEquals() {
        Event otherEvent = new Event("abc");
        Event otherEvent1 = null;
        Course course = new Course("1","1","1","1",
                "1","1");
        Event e = new Event("Sensor open at door");
        assertFalse(e.equals(otherEvent1));
        assertFalse(e.equals(course));
        assertFalse(e.equals(otherEvent));
        assertTrue(e.equals(e));
        assertTrue(e.hashCode() == e.hashCode());
        assertFalse(e.hashCode() == otherEvent.hashCode());
    }
}
