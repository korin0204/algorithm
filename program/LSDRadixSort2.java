public class LSDRadixSort2 {
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        
        // 配列の最大数を取得し、桁数を取得します
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        
        int mod = 10, div = 1;
        int[][] bucketList = new int[10][array.length];
        int[] bucketSize = new int[10];
        
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            // バケットの初期化
            for (int j = 0; j < 10; j++) {
                bucketSize[j] = 0;
            }
            
            // 各要素をバケットに振り分け
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList[num][bucketSize[num]] = array[j];
                bucketSize[num]++;
            }
            
            // バケットから元の配列に戻す
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketSize[j]; k++) {
                    array[index] = bucketList[j][k];
                    index++;
                }
            }
        }
        
        return array;
    }
}
