import java.io.*;

public class datasortonce {
    public static void main(String[] args) {

        int a[] = new int[1000000];
        int i;
        i = 0;
        try {
            FileReader fr = new FileReader(
                    "/Users/mizunoshoma/Documents/二年前期/データ構造とアルゴリズム/data-algo/1000000/1000000_0"); // FileReaderオブジェクトの作成
            StreamTokenizer st = new StreamTokenizer(fr); // StreamTokenizerオブジェクトの作成

            while (st.nextToken() != StreamTokenizer.TT_EOF) // ファイルの終わりに達するとTT_EOFが返されるので、そこでループ終了
            {
                // System.out.print(st.nval + " "); // 読み取ったデータを表示する。
                a[i] = (int) st.nval; // 読み取ったデータを配列に代入
                i++;
            }
            System.out.println("");
            fr.close();
        } catch (Exception e) {
            System.out.println(e); // エラーが起きたらエラー内容を表示
        }

        System.out.println("");

        // ソート開始 タイマーオン
        long startTime = System.currentTimeMillis();

        // for (int k = 0; k < a.length - 1; k++) {
        // for (int l = k + 1; l < a.length; l++) {
        // if (a[k] > a[l]) {
        // int tmp = a[k];
        // a[k] = a[l];
        // a[l] = tmp;
        // }
        // }
        // // System.out.println(k);
        // }

        // QuickSort.quickSort(a, 0, 999999);
        // BubbleSort.bubbleSort(a);
        // LSDRadixSort.RadixSort(a);
        LSDRadixSort2.RadixSort(a);

        long endTime = System.currentTimeMillis();
        // ソート終了 タイマーオフ

        // チェック機構
        //a[500000] = 1;
        System.out.println(i);
        for (int j = 0; j < i - 1; j++) {
        if(a[j] <= a[j + 1]){}else{
        System.out.println("Error");
        //break;
        }
        System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
        }

        // for (int j = 0; j < i; j++) {
        // System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
        // }
        // ソート時間の表示
        System.out.println("Start: " + startTime + "[ms]");
        System.out.println("End: " + endTime + "[ms]");
        System.out.println("Duration: " + (endTime - startTime) + "[ms]");
    }
}
