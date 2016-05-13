package ConsoleInterface;

import java.util.Scanner;
import java.util.ArrayList;

import Classical.Department;
import Classical.Employee;
import Classical.Register;

public class CMD {
    private Register register = new Register();
    private Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        CMD cmd = new CMD();
        cmd.start();
    }
    
    private void start() {
        int opt;
        
        do {
            System.out.println("\n\tMENU PRINCIPAL\n"
                + "0 - Agregar empleado\n"
                + "1 - Buscar empleado\n"
                + "2 - Listar todos los empleados\n"
                + "3 - Editar empleado\n"
                + "4 - Salir\n"
            );
            
            System.out.print("Opcion : ");
            opt = sc.nextInt();
        
            switch(opt) {
                case 0:
                    add();
                    break;
                case 1:
                    search();
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    modify();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }while(opt != 4);
    }
    
    private void modify() {
        ArrayList<Employee> data = register.get();
        
        System.out.println("\n\tLista de empleados");
            
        for(int i = 0; i < data.size(); i++) {
            System.out.println(i + " : " + data.get(i));
        }
        
        if(data.size() > 0){
            int index;
            
            do {
                System.out.print("Opcion : ");
                index = sc.nextInt();
            }while(index < 0 || index >= data.size());
            
            System.out.println("\t\nElija el atributo que desea editar : ");
        
            System.out.println(
                "0 - Nombre\n"
                + "1 - Apellido\n"
                + "2 - Cedula\n"
                + "3 - Departmento\n"
                + "4 - Salario\n"
                + "5 - Supervisor\n"
            );
            
            int attribute;
        
            do {
                System.out.print("Opcion : ");
                attribute = sc.nextInt();
            }while(attribute < 0 || attribute > 5);
            
            modify(index, attribute);
        }else {
            System.out.println("\"No hay empleados\"");
        }
    }
    
    private void modify(int index, int attribute) {
        String attr = null;
        Object value = null;
        
        switch(attribute) {
            case 0:
                attr = "name";
                System.out.print("Digite nuevo Nombre : ");
                value = sc.next();
                break;
            case 1:
                attr = "lastname";
                System.out.print("Digite nuevo Apellido : ");
                value = sc.next();
                break;
            case 2:
                attr = "id";
                System.out.print("Digite nueva Cedula : ");
                value = sc.next();
                break;
            case 3:
                attr = "department";
                System.out.println("Elija el nuevo Departamento : ");
                value = nextDeparment();
                break;
            case 4:
                attr = "salary";
                System.out.print("Digite el % de salario a aumentar : ");
                value = sc.nextInt();
                break;
            case 5:
                attr = "supervisor";
                System.out.print("Supervisor : ");
                value = nextSupervisor();
                break;
        }
        
        System.out.println("\n\"El contacto fue editado correctamente\"");
        
        if(attr != null) {
            register.modify(index, attr, value);
        }
    }
    
    private void add() {
        System.out.print("Nombre : ");
        String name = sc.next();
        
        System.out.print("Apellido : ");
        String lastname = sc.next();
        
        System.out.print("Cedula : ");
        String cedula = sc.next();
        
        System.out.println("Departamento : ");
        Department department = nextDeparment();
        
        System.out.print("Salario : ");
        int salary = sc.nextInt();
        
        Employee supervisor = nextSupervisor();
        
        Employee employee = new Employee(name, lastname, cedula, department, salary, supervisor);
        register.add(employee);
        
        System.out.println("\n\"Contacto agregado satisfactoriamente\"");
    }
    
    private Employee nextSupervisor() {
        Employee supervisor = null;
        
        ArrayList<Employee> data = register.get();
        
        for(int i = 0; i < data.size(); i++) {
            System.out.print(i + " : " + data.get(i));
        }
        
        int opt = -1;
        if(data.size() >0) {
            System.out.print("Supervisor : ");
            opt = sc.nextInt();
        }
        
        if(opt >= 0 && opt < data.size()){
            supervisor = data.get(opt);
        }
        
        return supervisor;
    }
    
    private Department nextDeparment() {
        Department department = null;
        
        do {
            int opt;
            
            System.out.println(
                "\n0 - Servicio al cliente\n"
                + "1 - Venta\n"
                + "2 - Almacen\n"
                + "3 - Tecnologia\n"
            );
            
            System.out.print("Opcion : ");
            opt = sc.nextInt();
            
            switch(opt) {
                case 0:
                    department = Department.CUSTOMER_SERVICE;
                    break;
                case 1:
                    department = Department.SALES;
                    break;
                case 2:
                    department = Department.WAREHOUSE;
                    break;
                case 3:
                    department = Department.TECHNOLOGY;
                    break;
                default:
                    System.out.println("Opcion invalida\n");
            }
        }while(department == null);
        
        return department;
    }
    
    private void search() {
        int opt;
        
        do {
            System.out.println("\n\tBuscar por : \n"
                + "0 - Nombre\n"
                + "1 - Apellido\n"
                + "2 - Volver\n"
            );
            
            System.out.print("Opcion : ");
            opt = sc.nextInt();
            
            String value;
            
            switch (opt) {
                case 0:
                    System.out.print("Nombre: ");
                    value = sc.next();
                    System.out.println("\n\tResultados : \n" + register.query("name", value));
                    break;
                case 1:
                    System.out.print("Apellido: ");
                    value = sc.next();
                    System.out.println("\n\tResultados : \n" + register.query("lastname", value));
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opcion invalida\n");
            }
        }while(opt != 2);
    }
    
    private void list(){
        int opt;
        
        do{
            System.out.println (
                "\n\tListar empleados en orden de : \n"
                +"0 - Nombre\n"
                +"1 - Apellido\n"
                +"2 - Cedula\n"
                +"3 - Departamento\n"
                +"4 - Salario\n"
                +"5 - Volver \n"
            );
        
            System.out.print("Opcion : ");
                opt = sc.nextInt();
            
            switch (opt){
                case 0:
                    System.out.print("\n\tListado por Nombre:\n");
                    System.out.println (register.list("name"));
                    break;
                case 1:
                    System.out.print("\n\tListado por Apellido:\n");
                    System.out.println (register.list("lastname"));
                    break;
                case 2:
                    System.out.print("\n\tListado por Cedula:\n");
                    System.out.println (register.list("id"));
                    break;
                case 3:
                    System.out.print("\n\tListado por Departamento:\n");
                    System.out.println (register.list("department"));
                    break;
                case 4:
                    System.out.print("\n\tListado por Salario:\n");
                    System.out.println (register.list("salary"));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\"Opcion invalida\"\n");
                
             }
             
        } while(opt != 5);
        
    }
}