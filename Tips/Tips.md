* 获得java数组的子串
  ```java
  // 数组类型int[] char[] ···
  Object[] newObject = Arrays.copyOfRange(src, start, end); // end取不到

  // ArrayList类型
  ArrayList<String> newArrayList = new ArrayList<>(src.subList(start, end)); // end取不到
  // 对于subList的更新会同步到src中去
  ```
* 遍历字符串中的字母
  ```java
  String s = "abcd";

  // 方法一
  for (int i = 0; i < s.length(); i ++) 
    char ch = s.charAt(i);

  // 方法二
  for (int i = 0; i < s.length(); i ++)
    String str = s.substring(i, i+1);
  
  // 方法三
  for (Character ch : s.toCharArray())

* 代码运行时间
  ```java
  long start = System.currentTimeMillis();

    // do something

  long end = System.currentTimeMillis();
  System.out.println((end - start) + "ms");
  ```

* 数组的初始化
  ```java
  // 指定大小
  int[] array = new int[5];

  // 指定内容
  int[] array = {1, 2, 3, 4};
  int[] array = new int[]{1, 2, 3, 4};
  ```

* 数组基本操作
  ```java
  public static void main(String[] args) {
        int[] arr1 = new int[5];
        int[] arr2 = new int[]{5, 4, 3, 2, 1};
        int[] arr3 = {1, 5, 3, 4, 2};
        // 数组填充
        Arrays.fill(arr1, 5);

        // 数组遍历
        for (int i : arr1)
            System.out.print(i + " "); // 5 5 5 5 5

        // 数组toString
        System.out.println("\n" + Arrays.toString(arr2)); // [5, 4, 3, 2, 1]

        // 数组复制
        int[] arr4 = new int[10];
        System.arraycopy(arr1, 0, arr4, 5, 5);
        System.out.println(Arrays.toString(arr4)); // [0, 0, 0, 0, 0, 5, 5, 5, 5, 5]

        // 一维数组的比较
        int[] arr5 = new int[5];
        Arrays.fill(arr5, 5);
        // 基于Object的equals方法比较
        System.out.println(arr1.equals(arr5)); // false
        // 基于Arrays.equals方法比较
        System.out.println(Arrays.equals(arr1, arr5)); // true

        // 二维数组的比较
        int[][] arr6 = {{1, 2, 3}, {4, 5, 6}};
        int[][] arr7 = {{1, 2, 3}, {4, 5, 6}};
        // 基于一维数组的比较
        System.out.println(Arrays.equals(arr6, arr7)); // false
        // 基于二维数组的比较
        System.out.println(Arrays.deepEquals(arr6, arr7)); // true

        // 基于一维数组toString
        System.out.println(Arrays.toString(arr6)); // [[I@b1bc7ed, [I@7cd84586]
        System.out.println(Arrays.deepToString(arr6)); // [[1, 2, 3], [4, 5, 6]]

        // 排序
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2)); // [1, 2, 3, 4, 5]

        // 二分搜索
        int index1 = Arrays.binarySearch(arr3, 3);
        int index2 = Arrays.binarySearch(arr3, 6);
        System.out.println(index1 + " " + index2); // 2 -6

    }
  ```

    
