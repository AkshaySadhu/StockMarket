package com.akshay.manageStockSpreadSheet.service;

import com.akshay.manageStockSpreadSheet.excelHelper.ExcelHelper;
import com.akshay.manageStockSpreadSheet.model.Excel;
import com.akshay.manageStockSpreadSheet.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    ExcelRepository excelRepository;

    public void saveAllDetails(MultipartFile file) {
        try {
            System.out.println("Trying to save all data");
            System.out.println(file.getInputStream().toString());
            List<Excel> tutorials = ExcelHelper.excelFileToExcelObj(file.getInputStream());
            System.out.println("Converted all data successfully");
            Iterable<Excel> it = excelRepository.saveAll(tutorials);
            System.out.println(it.toString());
        } catch (Exception e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream downloadFile() {
        List<Excel> excelList = (List<Excel>) excelRepository.findAll();

        try {
            ByteArrayInputStream inputStream = ExcelHelper.stockDataToExcel(excelList);
            return inputStream;
        } catch (Exception e) {

        }

        return null;
    }

    public List<Excel> getAllStockDetails() {
        return (List<Excel>) excelRepository.findAll();
    }
}
