继承
====
- __超类和子类__
```java
// 超类
package inheritance;

public class Employee {
    private String name;
    private double salary;
    
    // Constructor
    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public void raiseSalary(double percentage){
        double raise = salary * percentage / 100;
        salary += raise;
    }
}
```
```java
// 子类
package inheritance;

public class Manager extends Employee{
    private double bonus;

    public Manager(String name, double salary){
        super(name, salary);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        double basicSalary = super.getSalary();
        return basicSalary + bonus;
    }

    public void setBonus(double b){
        bonus = b;
    }
}
```
