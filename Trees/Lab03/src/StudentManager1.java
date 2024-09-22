
public class StudentManager1 {
    public static void main(String[] args) {
        StudentList list = new  StudentList();
        //Add 5 student
        Student st = new Student("SE009", "Nam", 4);
        list.add(st);
        st = new Student("SE001", "Phuc", 7);
        list.add(st);
        st= new Student("SE006", "Vinh", 8);
        list.add(st);
        st= new Student("SE002", "Truong", 9);
        list.add(st);
        st = new Student("SE005", "Dat", 2);
        list.add(st);
        
        //Output
        list.output();
        //Search operation: case of NOT FOUND
        String ID = "SE003";
        st = list.search(ID);
        if(st==null || !st.getID().equals(ID))
            System.out.println("Student: " + ID + " does not exist");
        else System.out.println("Found: " + st);
        //Search operation: case of FOUND
        ID = "SE006";
        st = list.search(ID);
        if (st==null || !st.getID().equals(ID))
            System.out.println("Student: " + ID + " does not exist");
        else System.out.println("Found: " + st);
    }
}
