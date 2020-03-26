import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args)
    {
        int[] arr = {10, 5, 4, 60, 3, 2, 1};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 希尔排序 设置增量为希尔增量
    public static void shellSort(int[] arr)
    {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < len; i ++)
            {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap])
                {
                        swap(arr, j, j - gap);
                        j -= gap;
                }
            }
        }
    }

    // 交换元素
    public static void swap(int[] arr, int index1, int index2)
    {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
