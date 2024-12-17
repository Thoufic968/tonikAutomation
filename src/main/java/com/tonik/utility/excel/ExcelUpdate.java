package com.tonik.utility.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tonik.utility.ExtentReporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUpdate {

    public static String xlpath;
    public static String xlFileName;
    static String sheetName = "Analysed_Reports";
    public static String UserType = "NA";
    public static String ModuleName = "NA";
    static int row = (getRowCount() + 1);
    static int counter = 0;
    public static int passCounter = 0;
    public static int failCounter = 0;
    public static int warningCounter = 0;
    static String sheet1 = "Module Result";


//    public static void createExcel() {
//        try {
//            String currentDate = getDate();
//            xlpath = System.getProperty("user.dir") + "\\Analysed_Reports\\Analysed_Reports_" + currentDate + ".xlsx";
//            xlFileName = "Analysed_Reports_" + currentDate + ".xlsx";
//            File dir = new File(System.getProperty("user.dir") + "\\Analysed_Reports");
//            if (!dir.isDirectory()) {
//                dir.mkdir();
//            }
//            File file = new File(xlpath);
//            if (!file.exists()) {
//                XSSFWorkbook workbook = new XSSFWorkbook();
//                workbook.createSheet(sheetName);
//                FileOutputStream fos = new FileOutputStream(new File(xlpath));
//                workbook.write(fos);
//                workbook.close();
//            }
//            System.out.println("Excel Sheet created");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

// private static String xlpath;
//        private static String xlFileName;
//        private static String sheetName = "Report";
//        private static int counter = 0;
//        private static int row = 1;
//        private static String ModuleName = "Module Name Example"; // Replace with dynamic module name
//        private static int passCounter = 0;
//        private static int failCounter = 0;
//        private static int warningCounter = 0;

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date); // Ensure file-safe format
    }


    public static void createExcel() {
        try {
            String currentDate = getDate();
            xlpath = System.getProperty("user.dir") + "/Analysed_Reports/Analysed_Reports_" + currentDate + ".xlsx";
            xlFileName = "Analysed_Reports_" + currentDate + ".xlsx";

            File dir = new File(System.getProperty("user.dir") + "/Analysed_Reports");
            if (!dir.isDirectory()) {
                dir.mkdir(); // Create directory if it doesn't exist
            }

            File file = new File(xlpath);
            if (!file.exists()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                workbook.createSheet(sheetName); // Create a sheet with a valid name
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                } finally {
                    workbook.close();
                }
                System.out.println("Excel file created successfully: " + xlpath);
            }
        } catch (Exception e) {
            System.err.println("Error creating Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeData(String validation, String result, String error) {
        try (
                FileInputStream fis = new FileInputStream(xlpath);
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                FileOutputStream fos = new FileOutputStream(xlpath)
        ) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Add header row if counter is 0
            if (counter == 0) {
                addHeaderRow(sheet);
                counter++;
                // Move to the next row after the header
            }

            // Create or get the current row
            XSSFRow xrow = sheet.getRow(row);
            if (xrow == null) {
                xrow = sheet.createRow(row);
            }

            // Write data based on the result
            writeRowData(xrow, validation, result, error);

            // Increment row for the next data entry
            row++;

            // Save the workbook
            workbook.write(fos);
            workbook.close();
            fos.flush();
            System.out.println("Excel Sheet write done");
        } catch (Exception e) {
            System.err.println("Error writing data to Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addHeaderRow(XSSFSheet sheet) {
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Module");
        headerRow.createCell(1).setCellValue("Scenario");
        headerRow.createCell(2).setCellValue("Results");
        headerRow.createCell(3).setCellValue("Remarks");
    }

    private static void writeRowData(XSSFRow row, String validation, String result, String error) {
        row.createCell(0).setCellValue(ModuleName);
        row.createCell(2).setCellValue(validation);

        if ("Pass".equalsIgnoreCase(result)) {
            row.createCell(2).setCellValue(result);
            passCounter++;
        } else if ("Fail".equalsIgnoreCase(result)) {
            row.createCell(2).setCellValue(result);
            row.createCell(3).setCellValue(error);
            failCounter++;
        } else if ("Warning".equalsIgnoreCase(result)) {
            row.createCell(2).setCellValue(result);
            row.createCell(3).setCellValue(error);
            warningCounter++;
        }
    }


    public static void Node(String NodeName) {
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
            FileOutputStream output = new FileOutputStream(xlpath);
            XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
//			myExcelSheet
            XSSFRow xrow = myExcelSheet.getRow(row);
            if (xrow == null) {
                xrow = myExcelSheet.createRow(row);
            }
            Cell cell = null;
            // Update the value of cell
            if (cell == null) {
                cell = xrow.createCell(1);
                cell.setCellValue(NodeName);
            }
            myExcelBook.write(output);
            myExcelBook.close();
        } catch (Exception e) {
        }
    }

    public int getMatchRow(String matchData) {
        String data = "";
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
            XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
            for (int i = 0; i < getRowCount(); i++) {
                data = myExcelSheet.getRow(i).getCell(0).toString();
                if (data.equals(matchData)) {
                    return i;
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Generic method to return the column values in the sheet.
    public static String getCellValue(int row, int col) {
        String data = "";
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
            XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
            data = myExcelSheet.getRow(row).getCell(col).toString();
        } catch (Exception e) {
        }
        return data;
    }

    // Generic method to return the number of rows in the sheet.
    public static int getRowCount() {
        int rc = 0;
        try {
            FileInputStream fis = new FileInputStream(xlpath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(sheetName);
            rc = s.getLastRowNum();
        } catch (Exception e) {
        }
        return rc;
    }

    public static String Iterator(String toFind) throws IOException {
//		String toFind = ID;
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
        for (Row row : myExcelSheet) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                DataFormatter formatter = new DataFormatter();
                String text = formatter.formatCellValue(cell);
                if (toFind.equals(text)) {
                    return cellRef.formatAsString().replaceAll("\\D+", "");
                } else if (text.contains(toFind)) {
                    System.out.println("Text found as part of " + cellRef.formatAsString());
                }
            }
        }
        return "";
    }

    public static void updateResult() {
        if (ExtentReporter.mailBodyPart.size() > 0) {
//            for (int i = 0; i < ExtentReporter.mailBodyPart.size(); i++) {
//                String result[] = ExtentReporter.mailBodyPart.get(i).toString().split(",");
//				System.out.println(result[0]+result[1]+result[2]);
//                try {
//                    XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath));
//                    FileOutputStream output = new FileOutputStream(xlpath);
//                    XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
//                    // Update the value of cell
//                    if (i == 0) {
//                        XSSFRow xrow = myExcelSheet.getRow(i);
//                        if (xrow == null) {
//                            xrow = myExcelSheet.createRow(i);
//                        }

//                        Cell cell = null;
//                        if (cell == null) {
//                            cell = xrow.createCell(1);
//                            cell.setCellValue("Module Name, APP verison - 20.21106.3");
//                            cell = xrow.createCell(2);
//                            cell.setCellValue("Module Result");
//                        }
//                    }
////						myExcelSheet
//                    XSSFRow xrow = myExcelSheet.getRow((i + 1));
//                    if (xrow == null) {
//                        xrow = myExcelSheet.createRow((i + 1));
//                    }
//
//                    Cell cell = null;
//                    if (cell == null) {
//                        cell = xrow.createCell(1);
//                        cell.setCellValue(result[0]);
//                        cell = xrow.createCell(2);
//                        if (failCounter == 0) {
//                            cell.setCellValue("Pass");
//                        } else {
//                            cell.setCellValue("Fail");
//                        }
//                    }
//                    myExcelBook.write(output);
//                    myExcelBook.close();
//            } catch(Exception e){
//            }

        }
    }
}
