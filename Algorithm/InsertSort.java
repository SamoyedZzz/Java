public class InsertSort {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] arrays = {1, 5, 3};
        int n = 3;
        for (int a : InsertSort3(arrays, n))
            System.out.println(a);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }


    // 暴力插入排序
    public static int[] InsertSort1(int[] array, int n) {
        int i, j, k;
        for (i = 1; i < n; i ++) {
            for (j = i - 1; j >= 0; j --) {
                if (array[j] < array[i])
                    break;
            }
                if (j != i - 1) {
                    int tmp = array[i];
                    for (k = i - 1; k > j; k --) {
                        array[k + 1] = array[k];
                    }
                    array[j + 1] = tmp;  
                }
            
        }
        return array;
    }

    // 边搜索边后移
    public static int[] InsertSort2(int[] array, int n)
    {
        int i, j;
        for (i = 1; i < n; i ++)
        {
            if (array[i] < array[i - 1])
            {
                int tmp = array[i];
                for (j = i - 1; j >= 0 && array[j] > tmp; j --)
                {
                    array[j + 1] = array[j];
                }
                array[j + 1] = tmp;
            }
        }
        return array;
    }

    // 用数据交换代替数据后移
    public static int[] InsertSort3(int[] array, int n)
    {
        int i, j;
        for (i = 1; i < n; i ++) 
        {
            for (j = i - 1; j >= 0 && array[j] > array[j + 1]; j--)
            {
                int tmp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = tmp;
            }
        }
        return array;
    }
}