package com.parabank.qa.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelHelper {

    public static String getCellValue(String filePath, String sheetName, int rowNum, int cellNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNum).getCell(cellNum);

            DataFormatter formatter = new DataFormatter();  // Handles numeric/text/boolean/etc.
            return formatter.formatCellValue(cell);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            return workbook.getSheet(sheetName).getLastRowNum();

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
