
import java.util.Iterator;
import java.util.TreeSet;

public class ClockList extends TreeSet<Clock>{
    public ClockList() {
        super(Clock.comparator);
    }
    
    public Clock search(String ID) {
        return this.ceiling(new Clock(ID));
    }
    
    public void output() {
        //Traversing the tree, ascending order
        Iterator it = this.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
