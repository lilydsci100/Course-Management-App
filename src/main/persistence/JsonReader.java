package persistence;

import model.Course;
import model.CourseList;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Event;
import model.EventLog;
import org.json.*;
// citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private CourseList courseList = new CourseList("My Course List");

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads course list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Load " + courseList.getName()));
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses course list from JSON object and returns it
    private CourseList parseCourseList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CourseList cl = new CourseList(name);
        addCourses(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses courses from JSON object and adds them to workroom
    private void addCourses(CourseList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Courses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCourse(cl, nextThingy);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses course from JSON object and adds it to course list
    private void addCourse(CourseList cl, JSONObject jsonObject) {
        String courseType = jsonObject.getString("CourseType");
        String courseNum = jsonObject.getString("CourseNum");
        String professorInformation = jsonObject.getString("ProfessorInformation");
        String officeHourLocation = jsonObject.getString("OfficeHourLocation");
        String officeHourTime = jsonObject.getString("OfficeHourTime");
        String gradeWeight = jsonObject.getString("GradeWeight");

        Course course = new Course(courseType, courseNum, professorInformation, officeHourLocation,
                officeHourTime, gradeWeight);
        cl.addList(course);
    }
}

