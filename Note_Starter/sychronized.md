## sychronized

* **定义**
  sychronized是java中的关键字，用于锁机制同步。

* **锁的特性**
  * 互斥性
  同一时间只允许一个线程持有同一个对象的锁，这样同一时间只有一个线程对需要同步的代码块进行操作，互斥性有时也称为原子性。
  * 可见性
  在锁被释放前，持有该锁的线程对共享变量的修改，对下一个持有该锁的线程是可见的。换句话说，下一个线程获得锁的同时，也会获得共享变量的最新值，避免了在本地缓存的副本上进行操作而引起不一致。

* **对象锁和类锁**
  * 对象锁
  java针对每个对象会有一个monitor对象，被称为对象锁或者内置锁。由于一个类可以有多个对象，每个对象有其独立的锁，互不干扰。
  * 类锁
  java针对每个类也有一个锁，被称为类锁，是通过类的class对象锁实现的。由于一个类只有一个class对象，每个类只有一个类锁。

* **sychronized用于获取对象锁**
  * 用法
    ```java
    // 用法一
    sychronized (this | object) {}
    // 用法二
    修饰非静态方法
    ```
  * 定义一个同步线程类
    ```java
    class SyncThread implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.startsWith("A")) {
            async();
        } else if (threadName.startsWith("B")) {
            sync1();
        } else if (threadName.startsWith("C")) {
            sync2();
        }

    }

    /**
     * 异步方法
     */
    private void async() {
        try {
            System.out.println(Thread.currentThread().getName() + "_Async_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "_Async_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法中有 synchronized(this|object) {} 同步代码块
     */
    private void sync1() {
        System.out.println(Thread.currentThread().getName() + "_Sync1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + "_Sync1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "_Sync1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 修饰非静态方法
     */
    private synchronized void sync2() {
        System.out.println(Thread.currentThread().getName() + "_Sync2: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            System.out.println(Thread.currentThread().getName() + "_Sync2_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "_Sync2_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
  * 同一个对象
    ```java
    // 测试代码
    public class test {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread A_thread1 = new Thread(syncThread, "A_thread1");
        Thread A_thread2 = new Thread(syncThread, "A_thread2");
        Thread B_thread1 = new Thread(syncThread, "B_thread1");
        Thread B_thread2 = new Thread(syncThread, "B_thread2");
        Thread C_thread1 = new Thread(syncThread, "C_thread1");
        Thread C_thread2 = new Thread(syncThread, "C_thread2");
        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();
    }
    }

    // 测试结果
    A_thread1_Async_Start: 23:12:08
    B_thread1_Sync1: 23:12:08
    B_thread2_Sync1: 23:12:08
    C_thread1_Sync2: 23:12:08
    A_thread2_Async_Start: 23:12:08
    C_thread1_Sync2_Start: 23:12:08
    A_thread1_Async_End: 23:12:10
    A_thread2_Async_End: 23:12:10
    C_thread1_Sync2_End: 23:12:10
    B_thread2_Sync1_Start: 23:12:10
    B_thread2_Sync1_End: 23:12:12
    B_thread1_Sync1_Start: 23:12:12
    B_thread1_Sync1_End: 23:12:14
    C_thread2_Sync2: 23:12:14
    C_thread2_Sync2_Start: 23:12:14
    C_thread2_Sync2_End: 23:12:16
    ```
    根据测试结果可以得出，对于同一个对象中的同步方法，其执行不受线程锁的影响；对于同一个对象中的异步方法，要等当前有锁的线程执行完了，并将锁释放，下一个线程才可以获得锁，并执行异步方法。   
    
  * 多个不同对象
    ```java
    // 测试代码
    public class test {
    public static void main(String[] args) {

        Thread A_thread1 = new Thread(new SyncThread(), "A_thread1");
        Thread A_thread2 = new Thread(new SyncThread(), "A_thread2");
        Thread B_thread1 = new Thread(new SyncThread(), "B_thread1");
        Thread B_thread2 = new Thread(new SyncThread(), "B_thread2");
        Thread C_thread1 = new Thread(new SyncThread(), "C_thread1");
        Thread C_thread2 = new Thread(new SyncThread(), "C_thread2");
        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();

    }
    }

    // 测试结果
    B_thread1_Sync1: 23:37:43
    A_thread2_Async_Start: 23:37:43
    A_thread1_Async_Start: 23:37:43
    B_thread1_Sync1_Start: 23:37:43
    C_thread2_Sync2: 23:37:43
    B_thread2_Sync1: 23:37:43
    B_thread2_Sync1_Start: 23:37:43
    C_thread1_Sync2: 23:37:43
    C_thread2_Sync2_Start: 23:37:43
    C_thread1_Sync2_Start: 23:37:43
    A_thread2_Async_End: 23:37:45
    A_thread1_Async_End: 23:37:45
    B_thread1_Sync1_End: 23:37:45
    B_thread2_Sync1_End: 23:37:45
    C_thread2_Sync2_End: 23:37:45
    C_thread1_Sync2_End: 23:37:45
    ```
    根据测试结果可以得出，对象锁针对的是不同线程对同一个对象的调用，同一个类的不同对象的对象锁互不干扰。

* **sychronized用于获取类锁**
  * 用法
    ```java
    // 用法一
    sychronized (类.class) {}
    // 用法二
    修饰静态方法
    ```
  * 定义同步线程类
    ```java
    // 仅对异步方法进行改动
    private void sync1() {
        System.out.println(Thread.currentThread().getName() + "_Sync1: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        synchronized (SyncThread.class) {
            try {
                System.out.println(Thread.currentThread().getName() + "_Sync1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "_Sync1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void sync2() {
        System.out.println(Thread.currentThread().getName() + "_Sync2: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        try {
            System.out.println(Thread.currentThread().getName() + "_Sync2_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "_Sync2_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    ```
  * 同一个对象
    ```java
    // 测试代码
    public class test {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread A_thread1 = new Thread(syncThread, "A_thread1");
        Thread A_thread2 = new Thread(syncThread, "A_thread2");
        Thread B_thread1 = new Thread(syncThread, "B_thread1");
        Thread B_thread2 = new Thread(syncThread, "B_thread2");
        Thread C_thread1 = new Thread(syncThread, "C_thread1");
        Thread C_thread2 = new Thread(syncThread, "C_thread2");
        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();
    }
    }

    // 测试结果
    B_thread1_Sync1: 23:53:52
    A_thread2_Async_Start: 23:53:52
    C_thread1_Sync2: 23:53:52
    A_thread1_Async_Start: 23:53:52
    B_thread2_Sync1: 23:53:52
    C_thread1_Sync2_Start: 23:53:52
    A_thread1_Async_End: 23:53:54
    A_thread2_Async_End: 23:53:54
    C_thread1_Sync2_End: 23:53:54
    B_thread2_Sync1_Start: 23:53:54
    B_thread2_Sync1_End: 23:53:56
    B_thread1_Sync1_Start: 23:53:56
    B_thread1_Sync1_End: 23:53:58
    C_thread2_Sync2: 23:53:58
    C_thread2_Sync2_Start: 23:53:58
    C_thread2_Sync2_End: 23:54:00
    ```
    根据结果可以看出，当只有一个对象时，获得类锁的结果与获得对象锁的结果基本一致。

  * 不同对象
    ```java
    // 测试代码
    public class test {
    public static void main(String[] args) {

        Thread A_thread1 = new Thread(new SyncThread(), "A_thread1");
        Thread A_thread2 = new Thread(new SyncThread(), "A_thread2");
        Thread B_thread1 = new Thread(new SyncThread(), "B_thread1");
        Thread B_thread2 = new Thread(new SyncThread(), "B_thread2");
        Thread C_thread1 = new Thread(new SyncThread(), "C_thread1");
        Thread C_thread2 = new Thread(new SyncThread(), "C_thread2");
        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();

    }
    }

    // 测试结果
    B_thread2_Sync1: 23:57:40
    C_thread1_Sync2: 23:57:40
    A_thread1_Async_Start: 23:57:40
    A_thread2_Async_Start: 23:57:40
    B_thread1_Sync1: 23:57:40
    C_thread1_Sync2_Start: 23:57:40
    A_thread2_Async_End: 23:57:42
    A_thread1_Async_End: 23:57:42
    C_thread1_Sync2_End: 23:57:42
    B_thread1_Sync1_Start: 23:57:42
    B_thread1_Sync1_End: 23:57:44
    B_thread2_Sync1_Start: 23:57:44
    B_thread2_Sync1_End: 23:57:46
    C_thread2_Sync2: 23:57:46
    C_thread2_Sync2_Start: 23:57:46
    C_thread2_Sync2_End: 23:57:48
    ```
    根据结果可以看出，虽然对象不一样，但是所有对象属于同一个类，因此类锁仍然会限制不同线程对不同对象的异步方法的调用。
   
* **sychronized同时获取对象锁和类锁**
  * 定义同步线程类
    ```java
    // 只重写两个异步方法
    private synchronized void sync1() {
        try {
            System.out.println(Thread.currentThread().getName() + "_Sync1_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "_Sync1_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void sync2() {
        try {
            System.out.println(Thread.currentThread().getName() + "_Sync2_Start: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "_Sync2_End: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    ```
  * 测试
    ```java
    // 测试代码
    public class test {
    public static void main(String[] args) {

        SyncThread syncThread = new SyncThread();
        Thread B_thread1 = new Thread(syncThread, "B_thread1");
        Thread C_thread1 = new Thread(syncThread, "C_thread1");
        B_thread1.start();
        C_thread1.start();
    }
    }

    // 测试结果
    B_thread1_Sync1_Start: 00:05:46
    C_thread1_Sync2_Start: 00:05:46
    B_thread1_Sync1_End: 00:05:48
    C_thread1_Sync2_End: 00:05:48

    // 另外一种测试
    Thread B_thread1 = new Thread(syncThread, "B_thread1");
        Thread C_thread1 = new Thread(syncThread, "C_thread1");
        Thread B_thread2 = new Thread(syncThread, "B_thread2");
        Thread C_thread2 = new Thread(syncThread, "C_thread2");
        B_thread1.start();
        C_thread1.start();
        B_thread2.start();
        C_thread2.start();
    
    // 测试结果
    B_thread1_Sync1_Start: 00:12:58
    C_thread1_Sync2_Start: 00:12:58
    B_thread1_Sync1_End: 00:13:00
    C_thread1_Sync2_End: 00:13:00
    B_thread2_Sync1_Start: 00:13:00
    C_thread2_Sync2_Start: 00:13:00
    B_thread2_Sync1_End: 00:13:02
    C_thread2_Sync2_End: 00:13:02
    ```
    根据结果可以看出，对象锁只限制同一个对象有对象锁的异步方法，类锁只限制同一个类有类锁的异步方法。