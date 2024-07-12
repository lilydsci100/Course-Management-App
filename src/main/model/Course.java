package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents a Course having a course number, professor name, professor email, office hour location, office hour time
// and grade weight
public class Course implements Writable {

    private String courseType;
    private String courseNum;
    private String professorInformation;
    private String officeHourLocation;
    private String officeHourTime;
    private String gradeWeight;

    //EFFECTS: course has course number, professor name, professor email, office hour location, office hour time
    // and grade weight
    public Course(String courseType, String courseNum, String professorInformation, String officeHourLocation,
                  String officeHourTime, String gradeWeight) {
        this.courseType = courseType;
        this.courseNum = courseNum;
        this.professorInformation = professorInformation;
        this.officeHourLocation = officeHourLocation;
        this.officeHourTime = officeHourTime;
        this.gradeWeight = gradeWeight;
    }

    //MODIFIES: this
    //EFFECTS: set the course type
    public void setType(String type) {
        this.courseType = type;
    }

    //MODIFIES: this
    //EFFECTS: set the course number
    public void setCourseNum(String num) {
        this.courseNum = num;
    }

    //MODIFIES: this
    //EFFECTS: set the professor information of the course
    public void setProInfor(String proInfor) {
        this.professorInformation = proInfor;
    }

    //MODIFIES: this
    //EFFECTS: set the office hour location of the course
    public void setOfficeHourLocation(String ofl) {
        this.officeHourLocation = ofl;
    }

    //MODIFIES: this
    //EFFECTS: set the office hour time of the course
    public void setOfficeHourTime(String oht) {
        this.officeHourTime = oht;
    }

    //MODIFIES: this
    //EFFECTS: set the grade weight of the course
    public void setGradeWeight(String gw) {
        this.gradeWeight = gw;
    }

    //MODIFIES: this
    //EFFECTS: save the change of the course
    public void modifyCourse(String type, String num, String proInfor, String ofl, String oht, String gw) {
        setType(type);
        setCourseNum(num);
        setProInfor(proInfor);
        setOfficeHourLocation(ofl);
        setOfficeHourTime(oht);
        setGradeWeight(gw);
        EventLog.getInstance().logEvent(new Event("Save Existing: " + this));
    }


    //EFFECTS: returns course number
    public String getCourseType() {
        return this.courseType;
    }

    //EFFECTS: returns course number
    public String getCourseNum() {
        return this.courseNum;
    }

    //EFFECTS: returns professor information of this course
    public String getProInfor() {
        return this.professorInformation;
    }

    //EFFECTS: returns office hour location of this course
    public String getOfficeHourLocation() {
        return this.officeHourLocation;
    }

    //EFFECTS: returns office hour time of this course
    public String getOfficeHourTime() {
        return this.officeHourTime;
    }

    //EFFECTS: returns grade weight of this course
    public String getGradeWeight() {
        return this.gradeWeight;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("CourseType", courseType);
        json.put("CourseNum", courseNum);
        json.put("ProfessorInformation", professorInformation);
        json.put("OfficeHourLocation", officeHourLocation);
        json.put("OfficeHourTime", officeHourTime);
        json.put("GradeWeight", gradeWeight);
        return json;
    }
}
