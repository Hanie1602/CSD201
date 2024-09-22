
import java.util.Iterator;
import java.util.TreeSet;

public class StudentList extends TreeSet<Student>{
    public StudentList() {
        super();
    }
    
    public Student search(String ID) {
        return this.ceiling(new Student(ID));
    }
    
    public void output() {
        //Traversing the treem ascending order
        Iterator it = this.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
