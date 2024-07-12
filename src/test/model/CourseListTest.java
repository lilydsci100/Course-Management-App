package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseListTest {

    private CourseList courseList;
    private Course course1;
    private Course course2;
    private List<Course> testCourseList;

    @BeforeEach
    void runBefore() {
        courseList = new CourseList("CourseList");
        course1 = new Course("MATH","220","Logan logan.math220@gmail.com",
                "DMP","TW 1pm-2pm",
                "assignment:15%,Matlab:5%,midterm:25%,final:55%");
        course2 = new Course("PHYS","118","John john.phys118@gmail.com",
                "OC","TT 1pm-2pm",
                "assignment:15%,lab:5%,midterm:25%,final:55%");
        testCourseList = new ArrayList<>();
    }

    @Test
    void getNameTest() {
        assertEquals("CourseList",courseList.getName());
    }

    @Test
    void numCoursesTest() {
        assertEquals(0,courseList.numCourses());
        courseList.addList(course1);
        assertEquals(1,courseList.numCourses());
        courseList.addList(course2);
        assertEquals(2,courseList.numCourses());
    }

    @Test
    void addListTest() {
        testCourseList.add(course1);
        courseList.addList(course1);
        assertEquals(testCourseList,courseList.getCourseList());
    }

    @Test
    void getCourseInformationTest() {
        courseList.addList(course1);
        assertEquals(course1,courseList.getCourseInformation(course1));
    }

    @Test
    void selectTheCourseTest() {
        courseList.addList(course1);
        assertTrue(courseList.selectTheCourse("MATH","220"));

        courseList.addList(course2);
        assertTrue(courseList.selectTheCourse("PHYS","118"));
        assertFalse(courseList.selectTheCourse("MATH","210"));
        assertFalse(courseList.selectTheCourse("CPSC","220"));
        assertFalse(courseList.selectTheCourse("PHYS","119"));
        assertFalse(courseList.selectTheCourse("CHEM","118"));
    }

    @Test
    void findCourseTest() {
        courseList.addList(course1);
        assertEquals(course1, courseList.findCourse("MATH","220"));
        assertEquals(null, courseList.findCourse("VISA","220"));
        assertEquals(null,courseList.findCourse("MATH","221"));
    }

    @Test
    void removeListTest() {
        courseList.addList(course1);
        courseList.addList(course2);
        courseList.removeList(course2);

        testCourseList.add(course1);
        assertEquals(testCourseList,courseList.getCourseList());
    }

    @Test
    void getCourseListTest(){
        courseList.addList(course1);
        courseList.addList(course2);

        testCourseList.add(course1);
        testCourseList.add(course2);

        assertEquals(testCourseList,courseList.getCourseList());
    }

}
