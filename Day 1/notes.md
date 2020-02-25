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
- 多态
对象变量是多态的，例如Employee既可以引用Employee类对象，也可以引用Employee子类对象，如下所示
```java
Employee[] staff = new Employee[3];
Manager boss = new Manager("fangming", 100.0);
staff[0] = boss;
```
