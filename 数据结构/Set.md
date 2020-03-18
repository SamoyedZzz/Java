## Set
* **定义**
  * Set继承于Collection接口，是一个不允许出现重复元素，且无序的集合，主要包括HashSet和TreeSet。
  * 判断重复元素时，HashSet调用`hashCode()`和`equals`方法实现，TreeSet调用`compareTo`方法实现。
#### HashSet
* **定义**
    HashSet实现了Set接口，底层通过传入HashMap中的<Key, 默认Object>实现。
* **特点**
  * 无重复元素
  * 无序
  * 可以插入null值
  * 线程不安全，两个线程操作HashSet时需要通过代码同步
* **操作**
  ```java
  // 创建对象
  Set<String> hashSet = new HashSet<String>();
  
  // 添加元素
  hashSet.add("abc");
  hashSet.add("def");
  
  // 集合大小
  int size = hashSet.size();
  
  // 删除指定元素
  hashSet.remove("abc");
  
  // 清除所有元素
  hashSet.clear();
  
  // 判断集合是否为空
  boolean empty = hashSet.isEmpty();
  
  // 判断集合是否包含指定元素
  boolean contains = hashSet.contains("abc");

  // 迭代器遍历集合
  Iterator<String> iterator = hashSet.iterator();
  while (iterator.hasNext())
    String str = iterator.next();

  // for each循环遍历
  for (String str : hashSet) {
      if (str.equals("abc"))
        rerturn true;
  }
  ```
* **无序性判断**
  * 先判断加入元素的`hashCode`
  * 再用`equals`方法判断
  * 相同则覆盖原有元素

#### TreeSet
* **定义**
  TreeSet实现了TreeMap，底层结构是红黑树，其具有排序功能（默认为自然升序，也可以自定义排序）。
* **特点**
  * 有序集合
  * 无重复元素
  * 可以传入null值
  * 线程不安全
* **操作**
  ```java
  // 创建对象
  TreeSet<String> treeSet = new TreeSet<String>();

  // 添加元素
  treeSet.add("abc");
  treeSet.add("def");

  // 集合大小
  int size = treeSet.size();

  // 返回集合的字符串格式 （默认升序）
  String str = treeSet.toString();

  // 获取头结点
  String first = treeSet.first();

  // 获得指定元素前的集合（不包含该元素）
  SortedSet<String> headSet = treeSet.headSet("abc");

  // 获得指定区间的元素集合 （包含头，不包含尾）
  SortedSet<String> subSet = treeSet.subSet("abc", "def");

  // 删除元素（若元素不存在，则返回false）
  boolean delete = treeSet.remove("abc");
  
  // 删除并返回第一个元素
  String str_first = treeSet.pollFirst();

  // 删除并返回最后一个元素
  String str_last = treeSet.pollLast();

  // 清空集合
  treeSet.clear();

  // 集合判断
  boolean empty = treeSet.isEmpty();
  boolean contains = treeSet.contains("abc");

  // for each循环遍历
  for (String str : treeSet){
      if (str.equals("abc"))
        return true;l
  }

  // 迭代器升降序循环
  Iterator<String> iterator = treeSet.iterator(); // 升序
  Iterator<String> Aesc_iterator = treeSet.descendingIterator();
  ```
* **自定义数据结构排序**
  ```java
  // 定义数据结构
  public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Person person) {
        int num = this.age - person.age;
        return num == 0 ? this.name.length() - person.name.length() : num;
    }

    public String toString(){
        return "{" + "name: " + name + ", age: " + age + "}";
    }
  }

  // 示例
  public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Person> treeSet = new TreeSet<>();
        Person p1 = new Person("fangming", 23);
        Person p2 = new Person("fangyuan", 14);
        treeSet.add(p1);
        treeSet.add(p2);
        System.out.println(treeSet);
    }
  }

  // 输出结果
  [{name: fangyuan, age: 14}, {name: fangming, age: 23}]
  ```