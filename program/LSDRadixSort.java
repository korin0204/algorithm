public class LSDRadixSort {
    public static void RadixSort(int[] data) {
        if (data == null || data.length < 2) {
        } else {
            // 1.配列の最大数を取得し、桁数を取得します。
            int max = data[0];
            for (int i = 1; i < data.length; i++) {
                max = Math.max(max, data[i]);
            }
            // int maxDigit = 0;
            // while (max != 0) {
            // max /= 10;
            // maxDigit++;
            // }

            int m = 1;
            int s[] = new int[data.length];
            int b[] = new int[data.length];

            while (m <= max) {
                for (int i = 0; i < data.length; i++) {
                    s[i] = (data[i] / m) % 10;

                    int p = 0;
                    for (int j = 0; j <= 9; j++) {
                        for (int k = 0; k < data.length; k++) {
                            if (s[k] == j) {
                                b[p++] = data[k];
                                //System.out.println(k);
                            }
                        }
                    }
                }
                for (int i = 0; i < data.length; i++) {
                    data[i] = b[i];
                    System.out.println(data[i]);
                }
                m *= 10;
                
            }
        }

    }
}
