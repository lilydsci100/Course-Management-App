package persistence;

import model.Course;
import static org.junit.jupiter.api.Assertions.assertEquals;
// citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkCourse(String courseType, String courseNum, String professorInformation, String officeHourLocation,
                               String officeHourTime,String gradeWeight, Course course) {
        assertEquals(courseType, course.getCourseType());
        assertEquals(courseNum, course.getCourseNum());
        assertEquals(professorInformation, course.getProInfor());
        assertEquals(officeHourLocation, course.getOfficeHourLocation());
        assertEquals(officeHourTime, course.getOfficeHourTime());
        assertEquals(gradeWeight, course.getGradeWeight());
    }
}
