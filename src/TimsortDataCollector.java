import java.io.*;

public class TimsortDataCollector {
    private String fileNameResult = "C:\\res\\repository\\semesterwork1\\src\\res\\TimsortResult";
    private String fileNameInput = "C:\\res\\repository\\semesterwork1\\src\\res\\InputFile";
    public void dataCollect(){
        cleanFile();
//        TimSort ts = new TimSort();
        TimSortLight ts = new TimSortLight();
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameInput))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                int[] array = new int[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    array[i] = Integer.parseInt(numbers[i]);
                }
                long startTime = System.nanoTime();
                ts.timsort(array);
                long endTime = System.nanoTime();
                long durationMs = (endTime - startTime);
                int iterations = ts.getIterCount();

                fillTimsortRes(numbers.length, iterations, durationMs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillTimsortRes(int size, int iterations, long durationMs){
        try (FileWriter fileWriter = new FileWriter(fileNameResult, true)) {
            fileWriter.write(String.valueOf(size)+", "+String.valueOf(iterations) + ", " + String.valueOf(durationMs));
            fileWriter.write("\n"); // Добавляем перенос строки после записи массива

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    private void cleanFile(){
        try (FileWriter fileWriter = new FileWriter(fileNameResult)) {
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
