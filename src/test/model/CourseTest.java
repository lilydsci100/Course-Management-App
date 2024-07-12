package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseTest {
    private Course course;

    @BeforeEach
    void runBefore() {
        course = new Course("CPSC","110","Megan hello.cpsc@gmail.com",
                "AMS","MWF 6pm",
                "assignment 10%,midterm 30%,final 60%");
    }

    @Test
    void getCourseTypeTest() {
        course.setType("APSC");
        assertEquals("APSC",course.getCourseType());
    }

    @Test
    void getCourseNumTest() {
        course.setCourseNum("121");
        assertEquals("121",course.getCourseNum());
    }

    @Test
    void getProInforTest() {
        course.setProInfor("Megan hello.cpsc@gmail.com");
        assertEquals("Megan hello.cpsc@gmail.com",course.getProInfor());
    }

    @Test
    void getOfficeHourLocationTest() {
        course.setOfficeHourLocation("DMP");
        assertEquals("DMP",course.getOfficeHourLocation());
    }

    @Test
    void getOfficeHourTimeTest() {
        course.setOfficeHourTime("MFS 9:00am-10:am");
        assertEquals("MFS 9:00am-10:am",course.getOfficeHourTime());
    }

    @Test
    void getGradeWeightTest() {
        course.setGradeWeight("assignment 10%,midterm 30%,final 60%");
        assertEquals("assignment 10%,midterm 30%,final 60%",course.getGradeWeight());
    }

    @Test
    void modifyCourseTest() {
        course.modifyCourse("CPSC","210","Hello hello.cpsc@gmail.com",
                "DMP","MWF 7pm",
                "assignment 25%,midterm 30%,final 45%");
        assertEquals("CPSC",course.getCourseType());
        assertEquals("210",course.getCourseNum());
        assertEquals("Hello hello.cpsc@gmail.com",course.getProInfor());
        assertEquals("DMP",course.getOfficeHourLocation());
        assertEquals("MWF 7pm",course.getOfficeHourTime());
        assertEquals("assignment 25%,midterm 30%,final 45%",course.getGradeWeight());
    }
}