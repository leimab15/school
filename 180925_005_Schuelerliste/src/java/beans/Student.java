package beans;

import java.time.LocalDate;

/**
 *
 * @author Manuel
 */
public class Student {
    private String firstname;
    private String lastname;
    private String klasse;
    private LocalDate birthdate;
    private int kn;

    public Student(String firstname, String lastname, String klasse, LocalDate birthdate, int kn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.klasse = klasse;
        this.birthdate = birthdate;
        this.kn = kn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getKn() {
        return kn;
    }

    public void setKn(int kn) {
        this.kn = kn;
    }
}
