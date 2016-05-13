package Classical;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Register {
    private ArrayList<Employee> list;
    
    public Register() {
        list = new ArrayList<>();
    }

    public void add(Employee employee) {
        list.add(employee);
    }
    
    public Employee get(int index) {
        Employee employee = null;
        
        if(index >= 0 && index < list.size()) {
            employee = list.get(index);
        }
        
        return employee;
    }
    
    public void remove(int index) {
        if(index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }
    
    public ArrayList<Employee> get() {
        return list;
    }
    
    public ArrayList<Employee> list(String attribute) {
        ArrayList<Employee> data = new ArrayList<>(list);
        
        Collections.sort(data, createComparator(attribute));
        
        return data;
    }

    private static Comparator createComparator(String attribute) {
        Comparator comparator = null;
        
        switch(attribute) {
            case "name":
                comparator = new Comparator<Employee>()
                {
                    @Override
                    public int compare(Employee a, Employee b) {
                        return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
                    }
                };
                
                break;
            case "lastname":
                comparator = new Comparator<Employee>()
                {
                    @Override
                    public int compare(Employee a, Employee b) {
                        return a.getLastname().toLowerCase().compareTo(b.getLastname().toLowerCase());
                    }
                };
            
                break;
            case "id":
                comparator = new Comparator<Employee>()
                {
                    @Override
                    public int compare(Employee a, Employee b) {
                        return a.getId().compareTo(b.getId());
                    }
                };
                
                break;
            case "department":
                comparator = new Comparator<Employee>()
                {
                    @Override
                    public int compare(Employee a, Employee b) {
                        return a.getDepartment().name().toLowerCase().compareTo(b.getDepartment().name().toLowerCase());
                    }
                };
            
                break;
            case "salary":
                comparator = new Comparator<Employee>()
                {
                    @Override
                    public int compare(Employee a, Employee b) {
                        int s1 = a.getSalary(), s2 = b.getSalary();
                        
                        return (s1 == s2)? 0 : ((s1 < s2)? -1: 1);
                    }
                };
                
                break;
        }
        
        return comparator;
    }

    public ArrayList<Employee> query(String attribute, String value) {
        ArrayList<Employee> data = new ArrayList<>();
        
        
        if(attribute != null && value != null) {
            String info = value.toLowerCase();
            
            switch(attribute.toLowerCase()) {
                case "name":
                    for(Employee employee : list) {
                        if(employee.getName().toLowerCase().equals(info)) {
                            data.add(employee);
                        }
                    }
                        
                    break;
                case "lastname":
                    for(Employee employee : list) {
                        if(employee.getLastname().toLowerCase().equals(info)) {
                            data.add(employee);
                        }
                    }
                    
                    
                    break;
            }
        }
        
        return data;
    }
    
    public boolean modify(int index, String attribute, Object value) {
        if(index >= 0 && index < list.size() && attribute != null && value != null) {
            Employee employee = list.get(index);
        
            try {
                switch(attribute.toLowerCase()) {
                    case "name":
                        employee.setName((String) value);
                        break;
                    case "lastname":
                        employee.setLastname((String) value);
                        break;
                    case "id":
                        employee.setId((String) value);
                        break;
                    case "department":
                        employee.setDepartment((Department) value);
                        break;
                    case "salary":
                        employee.increase((Integer) value);
                        break;
                    case "supervisor":
                        employee.setSupervisor((Employee) value);
                        break;
                    default:
                        return false;
                }
                        
                return true;
            }catch(Exception ex) {}
        }
        
        return false;
    }
}