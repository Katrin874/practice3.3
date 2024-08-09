import java.util.List;

public class Main {
    public static void main(String[] args) {
        Organization org = new Organization();
        org.addEmployee("Alice", 50000, IT.FULLSTACK_DEV);
        org.addEmployee("Bob", 40000, IT.FRONTED_DEV);
        org.addEmployee("Charlie", 60000, IT.BACKEND_DEV);
        org.addEmployee("David", 45000, IT.HQ);
        org.addEmployee("Eve", 55000, IT.FULLSTACK_DEV);
        System.out.println("Працівники в порядку зростання зарплати:");
        for (Employee e : org.getEmployeesInOrder()) {
            System.out.println(e.name + " - " + e.salary);
        }
        System.out.println("Чи існує працівник з зарплатою 45000: " + org.employeeExists(45000));
        System.out.println("Працівники з зарплатою більше 45000:");
        for (Employee e : org.getEmployeesWithSalaryGreaterThan(45000)) {
            System.out.println(e.name + " - " + e.salary);
        }
        org.deleteEmployee(40000);
        System.out.println("Працівники після видалення працівника з зарплатою 40000:");
        for (Employee e : org.getEmployeesInOrder()) {
            System.out.println(e.name + " - " + e.salary);
        }
        System.out.println("Працівники з 1 по 3 рівні дерева (BFS):");
        List<Employee> bfsEmployees = org.getEmployeesLevelOrder();
        for (Employee e : bfsEmployees) {
            System.out.println(e.name + " - " + e.salary);
        }
        Employee sibling = org.getSibling(55000);
        if (sibling != null) {
            System.out.println("Sibling працівника з зарплатою 55000: " + sibling.name + " - " + sibling.salary);
        } else {
            System.out.println("Sibling працівника з зарплатою 55000 не знайдено.");
        }
    }
}