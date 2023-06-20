public class QuickSort {
    static void quickSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int x = data[(left + right) / 2];
        int l = left;
        int r = right;
        int tmp;
        while (l <= r) {
            while (data[l] < x) {
                l++;
            }
            while (data[r] > x) {
                r--;
            }
            if (l <= r) {
                tmp = data[l];
                data[l] = data[r];
                data[r] = tmp;

                l++; r--;
            }
        }
        quickSort(data, left, r);
        quickSort(data, l, right);
    }
}