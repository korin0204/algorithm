import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort2 {
    private static final int THRESHOLD = 1000; // 基準とする配列のサイズの閾値

    public static void main(String[] args) {
        int[] array = { 5, 2, 9, 1, 3 };

        // マルチスレッドでクイックソートを実行する
        parallelQuickSort(array);

        // ソートされた結果を表示する
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static void parallelQuickSort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new QuickSortTask(array, 0, array.length - 1));
        forkJoinPool.shutdown();
    }

    private static class QuickSortTask extends RecursiveAction {
        private int[] array;
        private int low;
        private int high;

        public QuickSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low < THRESHOLD) {
                // 閾値未満の配列サイズの場合、通常のクイックソートを実行する
                quickSort(array, low, high);
            } else {
                // ピボットを選択し、分割する
                int pivotIndex = partition(array, low, high);

                // ピボットの左側と右側を並列にソートする
                QuickSortTask leftTask = new QuickSortTask(array, low, pivotIndex - 1);
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, high);
                invokeAll(leftTask, rightTask);
            }
        }

        private void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(array, low, high);
                quickSort(array, low, pivotIndex - 1);
                quickSort(array, pivotIndex + 1, high);
            }
        }

        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    swap(array, i, j);
                }
            }

            swap(array, i + 1, high);
            return i + 1;
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
