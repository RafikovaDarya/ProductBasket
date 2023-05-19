import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class ClientLog {
    protected List<String[]> logString = new ArrayList<>();

    public void log(int productNum, int amount) {
        logString.add(new String[]{String.valueOf(productNum), String.valueOf(amount)});
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile, true))) {
            writer.writeAll(logString);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logString(String[] s) {
        logString.add(s);
    }

}