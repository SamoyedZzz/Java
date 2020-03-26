import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args)
    {
        int[] arr = {5, 4, 3, 20, 2, 1, 10};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 创建临时数组，将待排序数组放入递归第一步
    public static void mergeSort(int[] arr)
    {
        int len = arr.length;
        int[] tmp = new int[len];
        mergeRecursion(arr, tmp, 0, len - 1);
    }

    // 递归过程
    public static void mergeRecursion(int[] arr, int[] result, int start, int end)
    {
        if (start >= end)
            return;

        int start1 = start;
        int mid =  start + (end - start) / 2;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;

        // 分治
        mergeRecursion(arr, result, start1, end1);
        mergeRecursion(arr, result, start2, end2);

        // 合并
        int k = start;
        // 当左右数组的指针都没走到头时
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        // 右指针走到头
        while (start1 <= end1)
            result[k++] = arr[start1++];
        // 左指针走到头
        while (start2 <= end2)
            result[k++] = arr[start2++];
        // 将临时数组的内容写入arr, 以继续往回合并
        // 用一个临时数组，若每调用一次递归声明一个临时数组，任意时刻有logN个临时数组处于活动状态
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

}
