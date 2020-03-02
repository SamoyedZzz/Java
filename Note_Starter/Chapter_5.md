## 5.2  Object：所有类的超类
当在定义类时，如果没有指定超类，则Object被认为这个类的超类。例如：
```java
public class Employee extends Object
```
同时可以用Object类变量引用任何类型的对象，例如：
```java
Object obj = new Employee(...);
```
要对其中的内容进行操作，还需要清除对象的原始类型，并进行类型转换：
```java
Employee e = (Employee) obj;
```
### 5.2.1   equals方法
将显示参数指定为otherObject，判断步骤包括：
* **第一步：判断是否引用同一个对象**
  ```java
  if (this == otherObject) return true;
  ```
* **第二步：判断otherObject是否为null**
  ```java
  if (otherObject == null) return false;
  ```
* **第三步：判断两个对象是否属于同一类**
  * 若equals方法在子类中有所改变，则使用getClass()方法
  ```java
  if (getClass() != otherObject.getClass()) return false;
  ```
  * 若equals方法在子类中具有相同语义，则使用instanceof方法
  ```java
  if (!(otherObject instanceof ClassName)) return false;
  ```
* **第四步：此时可以确定otherObject是非null的ClassName对象，对其进行类型转换**
  ```java
  ClassName other = (ClassName) otherObject;
  ```
* **第五步：判断域是否相同，用 == 比较基本类型域，用equals比较对象域**
  ```java
  return field1 == other.field1 && Objects.equal(field2, other.field2);
  ```
### 5.2.2   hashCode方法
若类中定义了hashCode方法，则用该方法导出对象的整型值；若类中没有定义hashCode方法，则调用Object类中的hashCode方法，返回对象存储地址。
```java
public class aHashCode {
        public static void main(String[] args) {
            String a = "Hello";
            StringBuilder a_1 = new StringBuilder(a);
            String b = "Harry";
            StringBuilder b_1 = new StringBuilder(b);
            System.out.println("a: " + a.hashCode() + "\n" + "b: " + b.hashCode());
            System.out.println("a_1: " + a_1.hashCode() + "\n" + "b_1: " + b_1.hashCode());
        }
    }
```
运行结果：
```java
a: 69609650
b: 69496448
a_1: 20132171
b_1: 186370029
```
如果在类中重新定义了`equals`方法，则`hashCode`方法也要重新定义，以保证`(x.equals(y) && (x.hashCode() == y.hashCode())) == true`。

### 5.2.3   toString方法
* **Object类中的toString方法**
  当具体类中没有定义`toString()`方法时，则会调用Object类中的`toString()`方法，返回对象所属的类名和散列码。
  ```java
  System.out.println(System.out);
  // 输出结果：java.io.PrintStream@5fd0d5ae
  ```
* **在类中重写过后的toString方法**
  ```java
  // 基类
  public class Employee{
    ···
    public String toString(){
      return getClass().getName()
        + "[name" + name
        + ",salary" + salary
        + ",hireDay" + hireDay
        + "]";
    }
  }

  // 子类
  public Manager extends Employee{
    ···
    public String toString(){
      return super.toString()
        + "[bonus" + bonus
        + "]";
    }
  }
  ```
## 5.3   泛型数组列表
* **建立一个`Employee`类的数组**
  ```java
  // 指定数组大小的写法，要再改变不容易
  Employee[] staff = new Employee[Size];

  // 数组动态变化的实现方法
  ArrayList<Employee> staff = new ArrayList<>;

* **ArrayList的用法**
  * 将元素添加到数组列表中
    ```java
    staff.add(new Employee("fangming", 500.0))
    ```
  * 如果可以确定数组可能存储的元素数量，在填充数组前可以调用
    ```java
    staff.ensureCapacity(100)
    ```
  * 分配数组列表和为新数组分配空间的区别
    ```java
    new ArrayList<>(100)
    // 表明数组列表具有保存100个元素的潜力，但是在完成初始化构造后，数组列表不会含有任何元素
    new Employee[100]
    // 分配100个元素的存储空间，就有100个空位置可以使用
  * 数组列表的实际大小
    ```java
    staff.size()
    ```
* **访问数组列表元素**
  * 增加元素
    ```java
    staff.add(index, x);
    ```
  * 修改已经存在的元素
    ```java
    staff.set(i, x);
    ```
  * 获得元素
     ```java
    staff.get(i);
    ```
  * 删除元素
    ```java
    staff.remove(i);
    ```
  * 创建完数组列表后，为了更方便访问，可以将数组元素拷贝到一个数组中
    ```java
    Employee[] e = new Employee[staff.size()];
    staff.toArray(e);
    ```

## 5.4   对象包装器与自动装箱
* **对象包装器**
  有时需要将基本类型转换为对象，因此需要对象包装器，常见的对象包装器包括：`Integer`, `Long`, `Float`, `Double`, `Short`, `Byte`。这几种包装器有一个超类`Number`。
  * 定义一个整型数组列表
    ```java
    // 错误的定义方法
    ArrayList<int> = ···
    // 正确的定义方法
    ArrayList<Integer> list = new ArrayList<>();
    // 该方法一般只用于构建小型集合，大型集合采用高效的int[]
    ```
* **自动装箱与拆箱**
  ```java
  list.add(3)
  // 会自动执行list.add(Integer.valueOf(3));
  int i = list.get(i)
  // get后为Integer对象，会自动执行int i = list.get(i).intValue();
  ```
