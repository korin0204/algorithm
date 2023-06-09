import java.io.*;

import javax.sql.DataSource;

class Numberread extends Thread {
        public static void main(String arg[]) {
                int sortTarget;
                String SortTarget = "";
                for (sortTarget = 0; sortTarget < 3; sortTarget++) {
                        int dataAmount = 0;
                        for (int dataAmountNum = 0; dataAmountNum < 5; dataAmountNum++) {
                                // 読み取るデータの数を変えます
                                switch (dataAmount) {
                                        case 0:
                                                dataAmount = 10000;
                                                break;
                                        case 10000:
                                                dataAmount *= 5;
                                                break;
                                        case 50000:
                                                dataAmount *= 2;
                                                break;
                                        case 100000:
                                                dataAmount *= 5;
                                                break;
                                        case 500000:
                                                dataAmount *= 2;
                                                break;
                                        case 1000000:
                                                break;
                                }

                                for (int otherData = 0; otherData < 5; otherData++) {
                                        for (int otherDataType = 0; otherDataType < 2; otherDataType++) {
                                                String dataType = "";
                                                // 読み取るデータがnormalかalmostかを変えます
                                                switch (otherDataType) {
                                                        case 0:
                                                                dataType = "";
                                                                break;
                                                        case 1:
                                                                dataType = "_almost";
                                                                break;
                                                }

                                                // 各データを10回回します
                                                long data[] = new long[10];
                                                double mean = 0;
                                                double variance = 0;
                                                for (int loop = 0; loop < 10; loop++) {
                                                        int a[] = new int[dataAmount];
                                                        int i;
                                                        i = 0;
                                                        try {
                                                                FileReader fr = new FileReader(
                                                                                dataAmount + "/" + dataAmount + "_"
                                                                                                + otherData + dataType); // FileReaderオブジェクトの作成
                                                                StreamTokenizer st = new StreamTokenizer(fr); // StreamTokenizerオブジェクトの作成

                                                                while (st.nextToken() != StreamTokenizer.TT_EOF) // ファイルの終わりに達するとTT_EOFが返されるので、そこでループ終了
                                                                {
                                                                        // System.out.print(st.nval + " "); //
                                                                        // 読み取ったデータを表示する。
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

                                                        // ソートアルゴリズムの変更(一番大外のfor文で変更)
                                                        switch (sortTarget) {
                                                                case 0:
                                                                        QuickSort.quickSort(a, 0, dataAmount - 1);
                                                                        SortTarget = "QuickSort";
                                                                        break;
                                                                case 1:
                                                                        LSDRadixSort2.RadixSort(a);
                                                                        SortTarget = "RadixSort";
                                                                        break;
                                                                case 2:
                                                                        BubbleSort.bubbleSort(a);
                                                                        SortTarget = "BubbleSort";
                                                                        break;
                                                                case 3:
                                                                        ParallelQuickSort.parallelQuickSort(a);
                                                                        break;
                                                        }

                                                        long endTime = System.currentTimeMillis();
                                                        // ソート終了 タイマーオフ

                                                        // チェック機構
                                                        // for (int j = 0; j < i ; j++) {
                                                        // System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
                                                        // }

                                                        // ソート時間の表示
                                                        data[loop] = endTime - startTime;
                                                        System.out.println("Start: " + startTime + "[ms]");
                                                        System.out.println("End: " + endTime + "[ms]");
                                                        System.out.println(
                                                                        "Duration: " + (endTime - startTime) + "[ms]");
                                                        mean += endTime - startTime;
                                                        variance += Math.pow(data[loop], 2);

                                                        // ソート時間の記録
                                                        try {
                                                                FileWriter fw = new FileWriter(
                                                                                "sort_result/result_"
                                                                                                + SortTarget + ".txt",
                                                                                true);
                                                                PrintWriter pw = new PrintWriter(
                                                                                new BufferedWriter(fw));
                                                                if (loop == 0) {
                                                                        pw.println(dataAmount + "_" + otherData
                                                                                        + dataType);
                                                                }

                                                                pw.println(loop);
                                                                pw.println("Duration: " + (endTime - startTime)
                                                                                + "[ms]");
                                                                if (loop == 9) {
                                                                        mean = mean / 10;
                                                                        variance = variance / 10;
                                                                        variance = variance - Math.pow(mean, 2);
                                                                        pw.println("mean:" + mean);
                                                                        pw.println("variance:" + variance);
                                                                }
                                                                pw.close();
                                                        } catch (IOException e) {
                                                                e.printStackTrace();
                                                        }
                                                        //csvへの出力
                                                }
                                        }

                                }

                        }
                }

        }
}