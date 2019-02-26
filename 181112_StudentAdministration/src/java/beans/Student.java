package beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Manuel
 */
public class Student implements Serializable{
    
    private String studentClass;
    private String lastname;
    private String firstname;
    private LocalDate birthdate;

    public Student() {
    }
    
    public Student(String studentClass, String lastname, String firstname, LocalDate birthdate) {
        this.studentClass = studentClass;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthdate = birthdate;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getFullName()
    {
        return lastname + " " + firstname;
    }
}
