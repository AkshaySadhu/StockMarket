package com.akshay.manageStockSpreadSheet.excelHelper;

import com.akshay.manageStockSpreadSheet.model.Excel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String SHEET = "stock_price";
    public static String[] COLUMNs = {"Company Code", "Stock Exchange", "Price Per Share", "Date", "Time"};

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Excel> excelFileToExcelObj(InputStream inputStream) {
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            System.out.println(workbook);
            System.out.println("Created workbook");
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("created sheet");
            System.out.println(sheet);
            Iterator<Row> rows = sheet.iterator();

            System.out.println("Created Iterator");

            List<Excel> excelList = new ArrayList<>();

            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                System.out.println("Why is sheet not printing");
                System.out.println(currentRow.toString());
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Excel excel = new Excel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            System.out.println(currentCell.getStringCellValue());
                            excel.setCompanyCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            System.out.println(currentCell.getStringCellValue());
                            excel.setStockExchange(currentCell.getStringCellValue());
                            break;

                        case 2:
                            System.out.println(currentCell.getNumericCellValue());
                            excel.setCurrentPrice(currentCell.getNumericCellValue());
                            break;

                        case 3:
                            System.out.println(currentCell.getDateCellValue());
//                            Date dateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(currentCell.getDateCellValue()));
                            System.out.println(currentCell.getDateCellValue());
                            excel.setDate(currentCell.getDateCellValue());
                            System.out.println("Date format set");
                            break;

                        case 4:
                            System.out.println(currentCell.getStringCellValue());
                            Date time = new SimpleDateFormat("HH:mm:ss").parse(currentCell.getStringCellValue());
                            excel.setTime(time);
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                    System.out.println(cellIdx);
                }
                System.out.println(excel.getCompanyCode());
                excelList.add(excel);
            }
            workbook.close();
            return excelList;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream stockDataToExcel(List<Excel> excelList) throws IOException {
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            Sheet sheet = workbook.createSheet("Stock Price");

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
            }

            int rowIdx = 1;
            for (Excel entry : excelList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entry.getCompanyCode());
                row.createCell(1).setCellValue(entry.getStockExchange());
                row.createCell(2).setCellValue(entry.getCurrentPrice().toString());
                row.createCell(3).setCellValue(entry.getDate().toString());
                row.createCell(4).setCellValue(entry.getTime().toString());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}