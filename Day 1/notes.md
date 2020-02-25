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
- __多态__  
对象变量是多态的，例如Employee既可以引用Employee类对象，也可以引用Employee子类对象，如下所示
```java
Employee[] staff = new Employee[3];
Manager boss = new Manager("fangming", 100.0);
staff[0] = boss;
```
当调用类对象里的方法的时候
```java
boss.setBonus(500.0); // OK
staff[0].setBonus(500.0); // Error
```
- __方法调用__  
例如要调用x.f(args)，隐式参数x声明为类C的一个对象。
    - 查看对象的声明类型(类C中任意，类C超类的public)和方法名f。
    - 查看args的参数类型，从上一步的候选f中匹配，若完全匹配，就选择这个方法，被称为**重载解析**。
    - 若类中的方法带有private, static, final修饰符，编译器可以准确知道调用哪个方法，则该种调用称为**静态绑定**；否则，称为**动态绑定**。
    - 当程序运行并采用动态绑定调用方法时，编译器一定调用与x引用对象实际类型最匹配的那个类的方法。



