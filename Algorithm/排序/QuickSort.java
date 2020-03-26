import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args)
    {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 快速排序
    public static void quickSort(int[] arr, int start, int end)
    {
        if (start >= end || arr == null || arr.length <= 1)
            return;

        int i = start;
        int j = end;
        int pivot = arr[(start + end) / 2];

        while (i <= j)
        {
            while (arr[i] < pivot)
                ++i;
            while (arr[j] > pivot)
                --j;
            if (i < j)
            {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                ++i;
                --j;
            }
            else if (i == j)
                ++i;
        }
        quickSort(arr, start, j);
        quickSort(arr, i, end);

    }

}
