public class Employee {
    int height;
    Employee right;
    Employee left;
    int salary;
    String name;
    IT pos;

    public Employee(String name, int salary,  IT pos) {
        this.name = name;
        this.salary = salary;
        this.pos = pos;
        this.height = 1;
    }
}
