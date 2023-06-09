public class BubbleSort {
        static void bubbleSort(int[] data) {
                for (int k = 0; k < data.length - 1; k++) {
                        for (int l = k + 1; l < data.length; l++) {
                                if (data[k] > data[l]) {
                                        int tmp = data[k];
                                        data[k] = data[l];
                                        data[l] = tmp;
                                }
                        }
                        //System.out.println(k);
                }
        }
}