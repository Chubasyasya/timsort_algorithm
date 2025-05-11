import java.util.Arrays;
import java.util.Stack;

public class TimSortLight {
    Stack<int[]> stack;
    private int minrun;
    private static int iterCount;

    public int[] timsort(int[] arr) {
        iterCount = 0;
        minrun = 63;
        stack = new Stack<>();
        splittingIntoSubarrays(arr);
        chooseWichMerge(arr);
        return arr;
    }
    public int getIterCount(){
        return iterCount;
    }
    private void chooseWichMerge(int[] arr) {
        //сделать цикл
        while (stack.size() > 2) {//неправильное содержимое в стеке
            int[] arrZ = stack.pop();
            int[] arrY = stack.pop();
            int[] arrX = stack.pop();
            if (!(arrY[1] > arrZ[1]) || !(arrX[1] > arrY[1] + arrZ[1])) {
                if (arrZ[1] <= arrX[1]) {
                    merge(arr, arrY[0], arrZ[0], arrZ[0] + arrZ[1]);
                    stack.add(arrX);
                    stack.add(new int[]{arrY[0], arrZ[1] + arrY[1]});
                } else {
                    merge(arr, arrX[0], arrY[0], arrY[0] + arrY[1]);
                    stack.add(new int[]{arrX[0], arrX[1] + arrY[1]});
                    stack.add(arrZ);
                }
            }
            iterCount++;
        }
        if (stack.size() == 2) {
            int[] arrY = stack.pop();
            int[] arrX = stack.pop();
            merge(arr, arrX[0], arrY[0], arrX[1] + arrY[1]);
        }
    }

    public static int binarySearch(int[] array, int target, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (array[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
            iterCount++;
        }

        return left < array.length ? left : -1;
    }

    private void splittingIntoSubarrays(int[] arr) {
        int runSize = 0;
        for (int i = 0; i < arr.length - 1; i += runSize) {
            if (arr[i] <= arr[i + 1]) {//по возрастанию
                runSize = 1;
                while (i + runSize + 1 < arr.length) {
                    if (arr[i+runSize] < arr[i + runSize+1]) {
                        runSize++;
                    }else{
                        break;
                    }
                }
            }else{
                runSize = 1;
                while (i + runSize + 1 < arr.length) {
                    if (arr[i+runSize] > arr[i + runSize+1]) {
                        runSize++;
                    }else{
                        break;
                    }
                }
                reverse(arr, i, i + runSize);
            }
            if (runSize < minrun) {//хззззззззззззз
                int possibleRun = Math.min((minrun), (arr.length - i));
                if(i+possibleRun == arr.length-1){
                    possibleRun++;
                }
                stack.add(new int[]{i, possibleRun});
                insertionSort(arr, i, i+possibleRun-1);
                runSize = minrun;
            } else {
                stack.add(new int[]{i, runSize+1});
            }
            iterCount++;
        }

    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
            iterCount++;
        }
    }
    

    public void insertionSort(int[] arr, int left, int right) {//затаргивает лишние штуки в сортировке
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp && j >= left) {
                arr[j + 1] = arr[j];
                j--;
                iterCount++;
            }
            arr[j + 1] = temp;
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int sizeLeftArr = mid - left;
        int sizeRightArr = right - mid + 1;
            mergeArr(arr, left, mid, right);
    }
    private void mergeArr(int[] arr, int left, int mid, int right) {
        int[] temporary = Arrays.copyOfRange(arr, left, mid);
        int a = 0;//счетчик arr
        int t = 0;//счетчик tempporary
        //райт не входит в массив
        int rightT = temporary.length - 1;
        int rightA = right - 1;
        int galopCounterA = 0;
        int galopCounterT = 0;
        while (a < right - mid && t < rightT+1) {
            if (arr[mid+a] >= temporary[t]) {
                arr[left+a+t] = temporary[t];
                t++;
                galopCounterT++;
                galopCounterA = 0;
            }else{
                arr[left+a+t] = arr[mid+a];
                a++;
                galopCounterA++;
                galopCounterT = 0;
            }
            if (galopCounterA >= 7) {
                a+=galop(mid+a,rightA,temporary[t],arr, arr, left+a+t);
                galopCounterA = 0;
                galopCounterT = 0;
            }else if(7 <= galopCounterT){
                t+=galop(t, rightT,arr[mid+a],temporary, arr, left+a+t);
                galopCounterA = 0;
                galopCounterT = 0;
            }
            iterCount++;
        }
        while (a<right - mid) {
            arr[left+a+t] = arr[mid+a];
            a++;
            iterCount++;
        }
        while (t < rightT+1) {
            arr[left+a+t] = temporary[t];
            t++;
            iterCount++;
        }
    }

    private int galop(int left, int right, int value, int[] galopArr, int[] arr, int index) {
        int powTwo = 2;
        int leftBorder = left;
        int rightBorder = left;
        while (rightBorder+powTwo < galopArr.length && value > galopArr[rightBorder]) {
            rightBorder += powTwo;
            powTwo *= 2;
            iterCount++;
        }
        if(rightBorder>galopArr.length){
            rightBorder = galopArr.length-1;
        }
        rightBorder = binarySearch(galopArr, value, leftBorder, rightBorder);
        //бинарным поиском ищем число после которого добавляется элемент из другого массива
        int counter = 0;
        for (int p = leftBorder; p <= rightBorder-1; p++, counter++) {
            arr[index + counter] = galopArr[p];
            iterCount++;
        }
        return counter;
    }
    }

