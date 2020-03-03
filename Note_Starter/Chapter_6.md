## 6.1 接口
接口不是类，是对类的一组需求的描述。
### 6.1.1 接口概念
　　以Arrays.sort(Object)为例，Arrays类中的`sort`方法可以实现对对象数组的排序，但必须满足以下条件，即Object所属的类必须实现了`Comparable`接口，其代码如下所示：
```java
// 原始版本，需要将对象强制转换
public interface Comparable{
    int compareTo(Object other);
}

// Comparable接口改进为泛型的版本
public interface Comparable<T>{
    int compareTo(T other)
}
```
**Tips:** 1) 接口中的方法自动属于public，因此不需要在接口的方法申明中提供public关键字；2) 接口中不可以含有实例域。  
　　以Employee类为例，当要对Employee对象数组进行排序时，我们需要通过两个步骤实现接口。
*  **将类声明为实现给定接口**
```java
public class Employee implements Comparable<Employee>
```
* **对接口中的方法进行定义**
```java
public int compareTo(Employee other){
    return Double.compare(salary, other.salary);
}
```

### 6.1.2  接口特性
* **接口变量声明**
  ```java
  Comparable x = new Employee(···);
  // 接口的变量必须引用实现接口的类对象
  ```
* **判断对象是否实现某个接口**
  ```java
  if (anObject instanceof Comparable){···}
  ```
* **接口扩展**
  ```java
  public interface a extends b{

      int aMethod(); // 自动设定为public

      // 不能包含有实例域，但可以有常量
      // 类似于
      double a = 1.0; // 自动设定为public static final

  }
  ```

### 6.1.3  接口与抽象类
* **一个类只可以扩展一个抽象类**
  ```java
  class Employee extends abstClass
  ```
* **一个类可以扩展多个接口**
  ```java
  class Employee extends abstClass implements interfaceA, interfaceB
  ```

### 6.1.4   静态方法

### 6.1.5   默认方法
* **为接口方法提供一个默认实现**
  ```java
  public interface Comparable<T>{
      default int compareTo(T other){
          return 0;
      }
  }
* **默认方法冲突**
  * **超类优先**。如果超类中定义了具体方法，则默认方法会被忽略。
  * **接口冲突**。两个超接口都具有同名方法，相同类型参数，则必须人为解决二义性。
    ```java
    // 两个提供默认方法的接口
    public interface Name{
        default String getName(){
            return getClass().getName();
        }
    }

    public interface Person{
        default String getName(){
            return getClass().getName();
        }
    }

    // 实现这两个接口
    public class Student implements Name, Person{
        public String getName(){
            // 选择冲突方法中的一个
            return Person.super.getName();
        }
    }
    // 若其中一个没有提供默认实现，仍然需要解决二义性。
 
## 6.3  Lambda表达式
----
### 6.3.1 引入Lambda表达式的意义
将一个代码块传递到一个对象并不容易，在之前的例子中，我们的做法是先定义一个类，在类中包含所需要功能的代码，实例化这个类后再传递。以实现按字符串长度对字符串数组排序为例：
```java
// 实现功能
class LengthComparator implements Comparator<String>{
    public int compare(String a, String b){
        return a.length() - b.length();
    }
}

// 调用实例
Comparator<String> cmp = new LengthComparator();
String[] list1 = new String[]{···};
Arrays.sort(list1, cmp);
```
这个过程并不方便，而Lambda表达式便是针对这个问题的改进。

### 6.3.2  Lambda表达式的语法
以上例中的`LengthComparator`方法为例，其Lambda表达式为：
```java
Arrays.sort(list1, (a, b) -> a.length() - b.length());
```

### 6.3.3 函数式接口
 对于只有一个抽象方法的接口，当需要这种接口的对象时，就可以提供一个Lambda表达式。

### 6.3.4  方法引用
* 考虑一个例子，只要出现一个定时器事件，就打印这个事件。
  * **采用Lambda表达式写法**
    ```java
    Timer t = new Timer(1000, event -> System.out.println(event));
    ```
  * **现成的方法引用**
    ```java
    Timer t = new Timer(1000, System.out::println);
    ```
* 方法引用格式
  ```java
  // 等价于提供方法参数的Lambda表达式：(x, y) -> Method(x, y)
  object::instanceMethond
  Class::staticMethond
  // 第一个参数会成为方法的目标：(x, y) -> x.Method(y)
  Class::instanceMethod
  // 可以使用this和super参数
  this::instanceMethod
  super::instanceMethod
  ```

### 6.3.6 变量作用域
通常我们希望可以在Lambda表达式中访问外围方法或类中的变量，但是被访问的变量必须为最终值，即不会改变的值。否则在并发时，会出现一些问题。
我们以一个实例来说明：
```java
public static void repeatMessage(String text, int delay){
    ActionListener listener = event -> {
        delay--;
        System.out.println(delay) // Error:变值
        System.out.println(text); // OK
    }
}
// 在Lambda表达式中声明与局部变量同名的参数也是不合法的
```

## 6.4  内部类
内部类指的是定义在另一个类中的类，使用内部类的原因包括：
* 可以访问该类定义所在作用域中的数据，包括私有数据。
* 可以对包中的其它类隐藏起来。
* 当要定义一个回调函数时，使用匿名内部类更方便。
