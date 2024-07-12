package persistence;

import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            CourseList cl = new CourseList("My course list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            CourseList cl = new CourseList("My course list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            cl = reader.read();
            assertEquals("My course list", cl.getName());
            assertEquals(0, cl.numCourses());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCourseList() {
        try {
            CourseList cl = new CourseList("My course list");
            cl.addList(new Course("CPSC","110","Megan hello.cpsc@gmail.com",
            "AMS","MWF 6pm", "assignment 10%,midterm 30%,final 60%"));
            cl.addList(new Course("MATH","220","Logan logan.math220@gmail.com",
                    "DMP","TW 1pm-2pm",
                    "assignment:15%,Matlab:5%,midterm:25%,final:55%"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            cl = reader.read();
            assertEquals("My course list", cl.getName());
            List<Course> courseList = cl.getCourseList();
            assertEquals(2, courseList.size());
            checkCourse("CPSC","110","Megan hello.cpsc@gmail.com",
                    "AMS","MWF 6pm", "assignment 10%,midterm 30%,final 60%",
                    courseList.get(0));
            checkCourse("MATH","220","Logan logan.math220@gmail.com",
                    "DMP","TW 1pm-2pm",
                    "assignment:15%,Matlab:5%,midterm:25%,final:55%", courseList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}