package persistence;

import model.Course;
import model.CourseList;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals("My course list", cl.getName());
            assertEquals(0, cl.numCourses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            CourseList wl = reader.read();
            assertEquals("My course list", wl.getName());
            List<Course> courseList = wl.getCourseList();
            assertEquals(2, courseList.size());
            checkCourse("CPSC","110","Megan hello.cpsc@gmail.com",
                    "AMS","MWF 6pm", "assignment 10%,midterm 30%,final 60%",
                    courseList.get(0));
            checkCourse("MATH","220","Logan logan.math220@gmail.com",
                    "DMP","TW 1pm-2pm",
                    "assignment:15%,Matlab:5%,midterm:25%,final:55%",
                    courseList.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}