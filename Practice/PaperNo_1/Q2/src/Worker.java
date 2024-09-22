// =========== DO NOT EDIT THE GIVEN CONTENT OF THIS FILE ============

class Worker {

    String name;
    int salary, ability;

    Worker() {
    }

    Worker(String xName, int xSalary, int xAbility) {
        name = xName;
        salary = xSalary;
        ability = xAbility;
    }

    public String toString() {
        return ("(" + name + "," + salary + "," + ability + ")");
    }
}
