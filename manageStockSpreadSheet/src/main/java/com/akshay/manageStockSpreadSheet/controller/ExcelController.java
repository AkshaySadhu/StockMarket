package com.akshay.manageStockSpreadSheet.controller;


import com.akshay.manageStockSpreadSheet.excelHelper.ExcelHelper;
import com.akshay.manageStockSpreadSheet.model.Excel;
import com.akshay.manageStockSpreadSheet.responseMessage.ResponseMessage;
import com.akshay.manageStockSpreadSheet.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        System.out.println(file.getSize());
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveAllDetails(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all-stock-details")
    public ResponseEntity<List<Excel>> getAllStockDetails() {
        try {
            List<Excel> tutorials = excelService.getAllStockDetails();

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download")
    public ResponseEntity<Resource> getStockPriceXLS() {
        String filename = "stockprices.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.downloadFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}
