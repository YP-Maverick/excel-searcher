package ru.maverick.excelsearcher.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelNumberReader implements FileNumberReader {

    @Override
    public List<Integer> readNumbers(String pathFile, int n) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (
            InputStream inputStream = new FileInputStream(pathFile);
            Workbook workbook = new XSSFWorkbook(inputStream)
        ) {
            Sheet sheet = workbook.getSheetAt(0);

            int counter = 0;
            for (Row row : sheet) {
                if (counter >= n) break;

                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                    counter++;
                }
            }
        }
        return numbers;
    }
}
