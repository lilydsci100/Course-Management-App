package persistence;

import model.CourseList;
import model.Event;
import model.EventLog;
import org.json.JSONObject;

import java.io.*;
// citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;
    private CourseList courseList = new CourseList("My Course List");

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of course list to file
    public void write(CourseList cl) {
        JSONObject json = cl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
        EventLog.getInstance().logEvent(new Event("Save " + courseList.getName()));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

