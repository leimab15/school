package bl;

import beans.Student;
import java.util.ArrayList;

/**
 *
 * @author Manuel
 */
public class Filter {
    
    public ArrayList<Student> filterStudents(ArrayList<Student> listToFilter,String filterString)
    {
        ArrayList<Student> filterdList = new ArrayList();
        filterdList.addAll(listToFilter);
        for (int i = 0; i < filterdList.size(); i++) {
                String studentName = filterdList.get(i).getFirstname()+filterdList.get(i).getLastname();
                if(!studentName.trim().toLowerCase().contains(filterString.trim().toLowerCase()))
                {
                   filterdList.remove(i);
                   i--;
                }
            }
        return filterdList;
    }
}
