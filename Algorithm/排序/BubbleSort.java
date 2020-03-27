import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args)
    {
        int[] arr = {5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr)
    {
        int tmp;

        // 趟数
        for (int i = 0; i < arr.length; i++)
        {
            // 当前趟数需要比较的元素个数
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                if (arr[j] > arr[j+1])
                {
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}
