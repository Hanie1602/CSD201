
public class ClockMng1 {
    public static void main(String[] args) {
        ClockList list = new ClockList();
        //Add 5 clocks
        list.add(new Clock("C009", "Nam HN", 120));
        list.add(new Clock("C003", "SG", 125));
        list.add(new Clock("C007", "STG", 70));
        list.add(new Clock("C001", "Seiko", 210));
        list.add(new Clock("C004", "Citizen", 180));
        
        //Output
        list.output();
        //Search operation: case of NOT FOUND
        String ID = "C333";
        Clock ck = list.search(ID);
        if(ck==null || !ck.getID().equals(ID))
            System.out.println("Clock " + ID + " does not exist");
        else System.out.println("Found: " + ck);
        
        //Search operation: case of FOUND
        ID = "C009";
        ck = list.search(ID);
        if(ck == null || !ck.getID().equals(ID))
            System.out.println("Clock " + ID + " does not exist");
        else System.out.println("Found: " + ck);
    }
}
