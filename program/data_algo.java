import java.io.*;

class Numberread extends Thread {
        public static void main(String arg[]) {
                int dataAmount = 0;
                for (int dataAmountNum = 0; dataAmountNum < 5; dataAmountNum++) {
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

                                        switch (otherDataType) {
                                                case 0:
                                                        dataType = "";
                                                        break;
                                                case 1:
                                                        dataType = "_almost";
                                                        break;
                                        }
                                        
                                        for (int loop = 0; loop < 10; loop++) {
                                                int a[] = new int[dataAmount];
                                                int i;
                                                i = 0;
                                                try {
                                                        FileReader fr = new FileReader(
                                                                        "/Users/mizunoshoma/Documents/二年前期/データ構造とアルゴリズム/data-algo/"
                                                                                        + dataAmount + "/" + dataAmount
                                                                                        + "_"
                                                                                        + otherData + dataType); // FileReaderオブジェクトの作成
                                                        StreamTokenizer st = new StreamTokenizer(fr); // StreamTokenizerオブジェクトの作成

                                                        while (st.nextToken() != StreamTokenizer.TT_EOF) // ファイルの終わりに達するとTT_EOFが返されるので、そこでループ終了
                                                        {
                                                                // System.out.print(st.nval + " "); // 読み取ったデータを表示する。
                                                                a[i] = (int)st.nval; // 読み取ったデータを配列に代入
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

                                                /*for (int k = 0; k < a.length - 1; k++) {
                                                        for (int l = k + 1; l < a.length; l++) {
                                                                if (a[k] > a[l]) {
                                                                        double tmp = a[k];
                                                                        a[k] = a[l];
                                                                        a[l] = tmp;
                                                                }
                                                        }
                                                        //System.out.println(k);
                                                }*/
                                                //QuickSort.quickSort(a, 0, dataAmount - 1);
                                                ParallelQuickSort.parallelQuickSort(a);
                                                //BubbleSort.bubbleSort(a);
                                                //LSDRadixSort.RadixSort(a);

                                                long endTime = System.currentTimeMillis();
                                                // ソート終了 タイマーオフ


                                                //チェック機構
                                                // for (int j = 0; j < i ; j++) {
                                                //         System.out.print(a[j] + " "); // ソート後の配列の中のデータを表示
                                                // }

                                                // ソート時間の表示
                                                System.out.println("Start: " + startTime + "[ms]");
                                                System.out.println("End: " + endTime + "[ms]");
                                                System.out.println("Duration: " + (endTime - startTime) + "[ms]");
                                                try {
                                                        FileWriter fw = new FileWriter(
                                                                        "/Users/mizunoshoma/Documents/二年前期/データ構造とアルゴリズム/data-algo/mysort/result_parallelquicksort.txt",
                                                                        true);
                                                        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
                                                        if (loop == 0) {
                                                                pw.println(dataAmount + "_" + otherData + dataType);
                                                        }
                                                        pw.println(loop);
                                                        pw.println("Duration: " + (endTime - startTime) + "[ms]");
                                                        pw.close();
                                                } catch (IOException e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }

                        }

                }

        }
}