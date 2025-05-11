import java.util.*;

public class GenerationInputData {
//    private int max = (int) Math.pow(2, 15);
private int max = 200;
    private int[] arr;
    private List<Integer> l;

    public int randomGenerateInt(){
        return (int) (Math.random() * ++max);
    }
    public void fillArray(int arraySize){
        arr = new int[arraySize];
        l = new ArrayList<>();
        for(int i = 0; i < arraySize; i++){
            int x = randomGenerateInt();
            arr[i]=x;
            l.add(x);
        }
    }
    public boolean sortArray(){
        TimSort t = new TimSort();
//        l.stream().forEach(elem -> System.out.print(elem + ", "));
        Collections.sort(l);
        t.timsort(arr);
        System.out.println();
        for(int i = 0; i < arr.length-1; i++){
            System.out.println(arr[i]+" "+l.get(i)+" "+i);
            if(arr[i] > arr[i+1]){
                return false;
            }
        }
        return true;
    }
}
