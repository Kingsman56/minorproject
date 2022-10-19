package com.example.minorproject;

public class model {
    String Name,Branch,Semester,Enrollment,Purl;


    public model() {
    }

    public model(String name, String enrollment, String branch, String semester, String purl) {
        Name = name;
        Enrollment = enrollment;
        Branch = branch;
        Semester = semester;
        Purl = purl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEnrollment() {
        return Enrollment;
    }

    public void setEnrollment(String enrollment) {
        Enrollment = enrollment;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getPurl(){
        return Purl;
    }

    public void setPurl(String purl) {
        Purl = purl;
    }
}
