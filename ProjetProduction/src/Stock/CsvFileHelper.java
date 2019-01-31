/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trongvo
 */
public class CsvFileHelper {
    public static List<String[] > readCsvFile(String fileName, char separator) {
        final List<String[] > data = new ArrayList<String[] >();

        try {
            final CSVParser parser =new CSVParserBuilder().withSeparator(separator).withIgnoreQuotations(true).build();
            final File file = new File(fileName);
            final FileReader fr = new FileReader(file);

            final CSVReader reader =new CSVReaderBuilder(fr).withSkipLines(1).withCSVParser(parser).build();
                    

            String[] nextLine = null;
            while ((nextLine = reader.readNext()) != null) {
                final int size = nextLine.length;

                // ligne vide
                if (size == 0) {
                    continue;
                }
                final String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }

                // ligne de commentaire
                if (debut.startsWith("#")) {
                    continue;
                }
                data.add(nextLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
