import java.util.Arrays;

public class HeapInsert
{
    public static void main(String[] args) 
    {
        int[] array = {9, 12, 17, 30, 50, 20, 60, 65, 4, 19};
        int n = array.length;
        for (int i = n - 1; i >= 0; i --)
        {
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            HeapDelete(array, 0, i);
        }

        System.out.println(Arrays.toString(array));

    }

    // 向上调整方法
    public static int[] HeapInsertFix(int[] array, int index) {
        int j = (index - 1) / 2;
        while (j >= 0 && index != 0) 
        {
            if (array[index] > array[j])
                break;
            int tmp = array[index];
            array[index] = array[j];
            array[j] = tmp;
            index = j;
            j = (index - 1) / 2;
        }
        return array;
    }

    // 向下调整方法                                                                                            
    public static int[] HeapDelete(int[] array, int start, int end) {
        int j = 2 * start + 1;
        int tmp1 = array[start];
        while (j < end)
        {
            if (j + 1 < end && array[j] > array[j + 1])
                j ++;

            if (tmp1 <= array[j])
                break;
            
            array[start] = array[j];
            start = j;
            j = 2 * start + 1;
                
        }
        array[start] = tmp1;
        return array;
    }

}
