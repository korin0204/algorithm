import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelQuickSort {
    private static final int THRESHOLD = 1000000; // 基準とする配列のサイズの閾値

    public static void main(String[] args) {
        //int[] array = { 5, 2, 9, 1, 3 };
        int n = 1000000;
            int a[] = new int [n];
            for(int i = 0; i < n; i++){
                a[i] = n-i;
                //System.out.println(a[i]);
            }

        // マルチスレッドでクイックソートを実行する
        long startTime = System.currentTimeMillis();
        parallelQuickSort(a);
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
        // ソートされた結果を表示する
        // for (int num : a) {
        //     System.out.print(num + " ");
        // }
    }

    public static void parallelQuickSort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new QuickSortTask(array, 0, array.length - 1));
        forkJoinPool.shutdown();
    }

    private static class QuickSortTask extends RecursiveTask<Void> {
        private int[] array;
        private int low;
        private int high;

        public QuickSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Void compute() {
            if (high - low < THRESHOLD) {
                // 閾値未満の配列サイズの場合、通常のクイックソートを実行する
                Arrays.sort(array, low, high + 1);
            } else {
                // ピボットを選択し、分割する
                int pivotIndex = partition(array, low, high);

                // ピボットの左側を新しいタスクとして実行する
                QuickSortTask leftTask = new QuickSortTask(array, low, pivotIndex - 1);
                leftTask.fork();

                // ピボットの右側を現在のスレッドで実行する
                QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, high);
                rightTask.compute();

                // 左側のタスクが完了するまで待機する
                leftTask.join();
            }
            return null;
        }

        private int partition(int[] array, int low, int high) {
            // ピボットとして最後の要素を選択する
            int pivot = array[high];

            // ピボットより小さい要素をピボットの左側に配置する
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    swap(array, i, j);
                }
            }

            // ピボットを適切な位置に配置する
            swap(array, i + 1, high);

            // ピボットのインデックスを返す
            return i + 1;
        }

        private void swap(int[] array, int i, int j) {
            int temp;
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    // class tester {
    //     public static void main(String[] args) {
    //         int n = 1000;
    //         int a[] = new int [n];
    //         for(int i = 0; i < n; i++){
    //             a[i] = n-i;
    //         }
    //         ParallelQuickSort.parallelQuickSort(a);
    //         for(int i=0; i<n; i++){
    //             System.out.println(a[i]);
    //         }   
    //     }
    // }
}
            
