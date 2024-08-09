import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static javax.swing.text.html.HTML.Attribute.N;

public class Organization {
    Employee root;

    int height(Employee node) {
        if (node == null)
            return -1;
        return node.height;
    }

    Employee rightRotate(Employee a) {
        Employee b = a.left;
        Employee c = b.right;

        b.right = a;
        a.left = c;

        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;
        return b;
    }

    Employee leftRotate(Employee a) {
        Employee b = a.right;
        Employee c = b.left;

        b.left = a;
        a.right = c;

        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;

        return b;
    }

    int getBalance(Employee node) {
        if (node == null)
            return 0;
        return height(node.right) - height(node.left);
    }
    public boolean addEmployee(String name, int salary, IT pos) {
        Employee newEmployee = new Employee(name, salary, pos);
        root = insert(root, newEmployee);
        return true;
    }
    Employee insert(Employee node, Employee employee) {
        if (node == null)
            return employee;
        if (employee.salary < node.salary)
            node.left = insert(node.left, employee);
        else if (employee.salary > node.salary)
            node.right = insert(node.right, employee);
        else
            return node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance < -1 && employee.salary < node.left.salary)
            return rightRotate(node);
        if (balance > 1 && employee.salary > node.right.salary)
            return leftRotate(node);
        if (balance < -1 && employee.salary > node.left.salary)
            return rightRotate(node);
        if (balance > 1 && employee.salary < node.right.salary)
            return leftRotate(node);
        return node;
    }
    public Employee deleteEmployee(int salary) {
        root = deletedNode(root, salary);
        return root;
    }

    Employee minValueNode(Employee node) {
        Employee current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Employee deletedNode(Employee root, int salary) {
        if (root == null)
            return root;
        if (salary < root.salary)
            root.left = deletedNode(root.left, salary);
        else if (salary > root.salary)
            root.right = deletedNode(root.right, salary);
        else if ((root.left == null) || (root.right == null)) {
            Employee temp = null;
            if (temp == root.left)
                temp = root.right;
            else
                temp = root.left;
            if (temp == null) {
                temp = root;
                root = null;
            } else
                root = temp;
        } else {
            Employee temp = minValueNode(root.right);
            root.salary = temp.salary;
            root.right = deletedNode(root.right, temp.salary);
        }
     if(root == null)
         return root;
     root.height = Math.max(height(root.left), height(root.right))+1;
     int balance = getBalance(root);
     if(balance <-1 && getBalance(root.left) <= 0)
         return rightRotate(root);
     if(balance < - 1 && getBalance(root.left) > 0) {
         root.left = leftRotate(root.left);
         return rightRotate(root);
     }
     if (balance > 1 && getBalance(root.left) >= 0)
         return  leftRotate(root);
     if(balance > 1 && getBalance(root.right) < 0){
         root.right = rightRotate(root.right);
         return  leftRotate(root);
     }
     return root;
    }
    public boolean employeeExists(int salary) {
        Employee current = root;
        while (current != null) {
            if (current.salary == salary)
                return true;
            current = (salary < current.salary) ? current.left : current.right;
        }
        return false;
    }
    void getEmployeesWithSalaryGreaterThanHelper(Employee node, int N, List<Employee> employees) {
        if (node != null) {
            if (node.salary > N)
                employees.add(node);
            getEmployeesWithSalaryGreaterThanHelper(node.left, N, employees);
            getEmployeesWithSalaryGreaterThanHelper(node.right, N, employees);
        }
    }
    public List<Employee> getEmployeesWithSalaryGreaterThan(int N) {
        List<Employee> employees = new ArrayList<>();
        getEmployeesWithSalaryGreaterThanHelper(root, N, employees);
        return employees;
    }
    void inorderTraversal(Employee node, List<Employee> employees) {
        if (node != null) {
            inorderTraversal(node.left, employees);
            employees.add(node);
            inorderTraversal(node.right, employees);
        }
    }
    public List<Employee> getEmployeesInOrder() {
        List<Employee> employees = new ArrayList<>();
        inorderTraversal(root, employees);
        return employees;
    }
    public List<Employee> getEmployeesLevelOrder() {
        List<Employee> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Employee> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Employee current = queue.poll();
            result.add(current);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }
    public Employee getSibling(Employee node, int salary) {
        if (node == null || node.left == null || node.right == null) return null;

        if (node.left.salary == salary) return node.right;
        if (node.right.salary == salary) return node.left;

        Employee left = getSibling(node.left, salary);
        if (left != null) return left;

        return getSibling(node.right, salary);
    }

    public Employee getSibling(int salary) {
        return getSibling(root, salary);
    }


}








