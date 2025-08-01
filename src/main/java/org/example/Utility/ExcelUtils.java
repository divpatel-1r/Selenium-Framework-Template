package org.example.Utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
/*
    * ExcelUtils is a utility class for reading data from Excel files.
    * It provides a method to read data from a specified sheet in an Excel file and return it as a two-dimensional Object array.
 */

    public static Object[][] getTestData(String excelPath, String sheetName) {      // Method to read data from an Excel file and return it as a two-dimensional Object array
        Object[][] data = null;// Initialize the data array to null

        try (FileInputStream fis = new FileInputStream(excelPath);// Create a FileInputStream to read the Excel file
             Workbook workbook = new XSSFWorkbook(fis)) {// Create a Workbook instance to read the Excel file using Apache POI

            Sheet sheet = workbook.getSheet(sheetName);// Get the specified sheet from the workbook
            int rowCount = sheet.getPhysicalNumberOfRows();// Get the number of physical rows in the sheet
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();// Get the number of physical cells in the first row (header row)

            data = new Object[rowCount - 1][colCount];// Initialize the data array with the number of rows (excluding header) and columns

            for (int i = 1; i < rowCount; i++) {// Loop through each row starting from the second row (index 1) to skip the header row
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }

        } catch (IOException e) {// Handle any IO exceptions that may occur while reading the file
            e.printStackTrace();
        }

        return data;// Return the data array containing the Excel data
    }
}
