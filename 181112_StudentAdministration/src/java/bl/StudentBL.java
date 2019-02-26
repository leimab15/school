package bl;

import beans.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Manuel
 */
public class StudentBL {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    
    private ArrayList<Student> students = new ArrayList();
    private ArrayList<Student> filterStudents = new ArrayList();
    private TreeSet<String> studentKlassen = new TreeSet();
    
    
    public ArrayList<Student> loadData(String pathToStudentsCSV) throws IOException {
        File studentFile = new File(pathToStudentsCSV);
        FileInputStream fis = new FileInputStream(studentFile);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        String line = "";
        String seperator = ",";
        br.readLine();

        short TOKEN_klasse = 0, TOKEN_lastname = 1, TOKEN_firstname = 2, TOKEN_dateOfBirth = 3;

        while ((line = br.readLine()) != null) {
            String[] studentTokens = line.split(seperator);
            //Klasse,Familienname,Vorname,2000-06-17
            Student createdStudent = new Student(studentTokens[TOKEN_klasse], studentTokens[TOKEN_lastname], studentTokens[TOKEN_firstname],LocalDate.parse(studentTokens[TOKEN_dateOfBirth]));
            studentKlassen.add(studentTokens[TOKEN_klasse]);
            students.add(createdStudent);
        }
        return students;
    }
    
    public ArrayList<Student> removeStudent(int indexStudentToRemove)
    {
        students.remove(filterStudents.get(indexStudentToRemove));
        filterStudents.remove(indexStudentToRemove);
        return filterStudents;
    }
    
    public ArrayList<Student> getStudentFromClass(String classToFilter)
    {
        filterStudents.clear();
        for (Student currentStudent : students) {
            if(currentStudent.getStudentClass().equals(classToFilter))
            {
                filterStudents.add(currentStudent);
            }
        }
        filterStudents.sort((Student o1, Student o2) -> o1.getLastname().compareTo(o2.getLastname()));
        return filterStudents;
    }

    public TreeSet<String> getStudentKlassen() {
        return studentKlassen;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
