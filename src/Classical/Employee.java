package Classical;

import java.util.Random;

public class Employee {
    private static final Random RAND = new Random();
    
    private String name;
    private String lastname;
    private String id;
    private Department department;
    private int salary;
    private Employee supervisor;
    
    public Employee(String _name, String _lastname, String _id, Department _department, int _salary, Employee _supervisor) {
        setName(_name);
        setLastname(_lastname);
        setId(_id);
        setDepartment(_department);
        salary = Math.max(1, _salary);
        supervisor = _supervisor;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public String getId() {
        return id;
    }
    
    public int getSalary() {
        return salary;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public Employee getSupervisor() {
        return supervisor;
    }
    
    public void setName(String _name)  {
        name = (_name == null)? "" : _name;
    }
    
    public void setLastname(String _lastname) {
        lastname = (_lastname == null)? "" : _lastname;
    }
    
    public void setId(String _id) {
        id = (_id == null)? "000-0000000-0" : Validator.validate(_id);
    }
    
    public void setDepartment(Department _department) {
        department = (_department == null)? getRandomDepartment() : _department;
    }
    
    public void increase(int percent) {
        salary += salary * Math.max(0, percent) / 100;
    }
    
    public void setSupervisor(Employee _supervisor) {
        supervisor = _supervisor;
    }
    
    public String toString() {
        StringBuilder data = new StringBuilder();

        data.append("name : " + name + '\n'); 
        data.append("lastname : " + lastname + '\n');
        data.append("ID : " + id + '\n');
        data.append("Deparment : " + department.name() + '\n');
        data.append("Salary : " + salary + '\n');
        
        if(supervisor != null) {
            data.append("Supervisor : " + supervisor.getName() + " " + supervisor.getLastname() + '\n');
        }
        
        return data.toString();
    }
    
    public static Department getRandomDepartment() {
        Department[] departments = Department.values();
        
        return departments[RAND.nextInt(departments.length)];
    }
}