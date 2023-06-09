public class tester {
    public static void main(String[] args) {
        int a[] = {100,92,50,71,141,400,20,32,141,0};
        //QuickSort.quickSort(a, 0, 9);
        BubbleSort.bubbleSort(a);
        for(int i = 0; i<10; i++){
            System.out.println(a[i]);
        }

         
    }
}