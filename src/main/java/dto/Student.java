package dto;

import java.time.LocalDateTime;

public class Student {
    private String surname;
    private String name;
    private String faculty;
    private String group;
    private LocalDateTime dateOfAdmission;
    private int term;
    private boolean isStudying;

    public Student() {}

    public Student(String surname, String name, String faculty, String group, LocalDateTime dateOfAdmission, int term, boolean isStudying) {
        this.surname = surname;
        this.name = name;
        this.faculty = faculty;
        this.group = group;
        this.dateOfAdmission = dateOfAdmission;
        this.term = term;
        this.isStudying = isStudying;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public void setStudying(boolean studying) {
        isStudying = studying;
    }
}
