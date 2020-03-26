import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args)
    {
        int[] arr = {10, 5, 4, 60, 3, 2, 1};
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
        deleteMax(arr);
        System.out.println(Arrays.toString(arr));
    }



    // 向下调整
    public static void procDown(int[] arr, int start, int end)
    {
        int j;
        int tmp = arr[start];
        for (j = 2 * start + 1; j < end; j = 2 * start + 1)
        {
            if (j + 1 < end && arr[j] > arr[j + 1])
                j ++;
            if (arr[j] > tmp)
                break;
            arr[start] = arr[j];
            start = j;
        }
        arr[start] = tmp;
    }

    // 建立最小堆，此时可以保证父节点都小于子节点
    public static void buildHeap(int[] arr)
    {
        for (int i = arr.length / 2; i >= 0; i --)
            procDown(arr, i, arr.length);
    }

    // 将根节点和数组的最后一个值交换，对前n - 1个数再进行堆处理
    public static void deleteMax(int[] arr)
    {
        for (int i = arr.length - 1; i > 0; i --)
        {
            swap(arr, 0, i);
            procDown(arr, 0, i);
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
