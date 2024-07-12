package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represent a list of courses
public class CourseList implements Writable {
    private List<Course> courseList;
    private String name;

    //EFFECTS: construct an empty course list with a name
    public CourseList(String name) {
        this.name = name;
        this.courseList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns number of courses in this course list
    public int numCourses() {
        return courseList.size();
    }

    //REQUIRES: the list does not already contain a course with the same courseNum as the course we are trying to add.
    //MODIFIES: this
    //EFFECTS: add a course in the list
    public void addList(Course course) {
        courseList.add(course);
        EventLog.getInstance().logEvent(new Event("Added course: " + course));
    }

    //REQUIRES: the course need to be selected
    //EFFECTS: get all the information of selected course
    public Course getCourseInformation(Course co) {
        return co;
    }

    //REQUIRES: give the course with its course type and number
    //EFFECTS:  if the course in the course list, return true. Otherwise, return false.
    public boolean selectTheCourse(String courseType, String number) {
        for (Course course : courseList) {
            if (course.getCourseNum().equals(number) && course.getCourseType().equals(courseType)) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: give the course with its course type and number
    //EFFECTS: return the course that you selected in the course list, if the course does not exist in the course list,
    //          return null
    public Course findCourse(String courseType, String number) {
        for (Course course : courseList) {
            if (course.getCourseNum().equals(number) && course.getCourseType().equals(courseType)) {
                return course;
            }
        }
        return null;
    }

    //REQUIRES: the course need to be selected
    //MODIFIES: this
    //EFFECTS: remove the selected course in the list
    public void removeList(Course course) {
        courseList.remove(course);
        EventLog.getInstance().logEvent(new Event("Removed course: " + course));
    }

    //EFFECTS: get the current course list
    public List<Course> getCourseList() {
        return this.courseList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns course in this course list as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courseList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
