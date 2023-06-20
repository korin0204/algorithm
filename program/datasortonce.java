import java.io.*;

public class datasortonce {
    public static void main(String[] args) {
        for (int many = 0; many < 50; many++) {
            int a[] = new int[1000000];
            int i;
            i = 0;
            try {
                FileReader fr = new FileReader(
                        "1000000/1000000_0"); // FileReaderオブジェクトの作成
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
            long startTime1 = System.nanoTime();

            LSDRadixSort2.RadixSort(a);
            //QuickSort.quickSort(a, 0, 1000000 - 1);
            // BubbleSort.bubbleSort(a);

            long endTime1 = System.nanoTime();
            long endTime = System.currentTimeMillis();
            // ソート終了 タイマーオフ

            // チェック機構
            // a[500000] = 1;
            // System.out.println(i);

            for (int j = 0; j < i - 1; j++) {
                if (a[j] <= a[j + 1]) {
                } else {
                    System.out.println("Error");
                    // break;
                }
                // System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
            }

            // for (int j = 0; j < i; j++) {
            // System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
            // }
            // ソート時間の表示
            System.out.println("Start: " + startTime + "[ms]");
            System.out.println("End: " + endTime + "[ms]");
            double time = (endTime1 - startTime1) / 1000000.0;
            System.out.println(time);
            System.out.println("Duration: " + (endTime - startTime) + "[ms]");
            // try {
            //     FileWriter fw = new FileWriter(
            //             "sort_result/test_data.csv",
            //             true);
            //     PrintWriter pw = new PrintWriter(
            //             new BufferedWriter(fw));
            //     pw.print(time);
            //     pw.println();
            //     // for(int abc=0; abc<=99999999; abc+=100){
            //     //     pw.print(abc);
            //     //     pw.println();
            //     // }
            //     pw.close();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }

    }
}
