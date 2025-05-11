import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        FillSourceFile f = new FillSourceFile("C:\\res\\repository\\semesterwork1\\src\\res\\InputFile");
        int amount = 100;
        f.fillFile(amount);//заполнили файл данными
        TimsortDataCollector t = new TimsortDataCollector();
        t.dataCollect();

    }
}