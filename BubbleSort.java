Estefy Calderon A00344189
import java.util.*;

public class Test{
    public static void main(String[] args){
        int[] lista = {9,8,7,6,5,4,3,2,1};

        bubbleSort(lista, lista.length);
    }

    public static void bubbleSort(int[] arr, int n){
        System.out.println(Arrays.toString(arr));
        for(int index=0;index<n-1;index++){
            if(arr[index] >  arr[index + 1]){
                int tmp = arr[index];
                arr[index] = arr[index+1];
                arr[index+1] = tmp;          
            }
        }

        if(n > 1){
            bubbleSort(arr, n-1);
        }
    }
 
}
