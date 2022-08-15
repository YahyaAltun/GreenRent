package com.greenrent.controller;

import com.greenrent.exception.ExcelReportException;
import com.greenrent.exception.message.ErrorMessage;
import com.greenrent.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/excel")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;


    @GetMapping("/download/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getUserReport(){
        String fileName = "users.xlsx";

        try {
            ByteArrayInputStream bais = reportService.getUserReport();
            InputStreamResource file = new InputStreamResource(bais);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=" + fileName).
                    contentType(MediaType.parseMediaType("application/vmd.ms-excel")).body(file);

        } catch (IOException e) {
            throw new ExcelReportException(ErrorMessage.EXCEL_REPORT_CREATION_ERROR_MESSAGE);
        }
    }

    @GetMapping("/download/cars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getCarReport(){
        String fileName="cars.xlsx";

        try {
            ByteArrayInputStream bais= reportService.getCarReport();
            InputStreamResource file=new InputStreamResource(bais);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                    .contentType(MediaType.parseMediaType("application/vmd.ms-excel")).body(file);

        } catch (IOException e) {
            throw new ExcelReportException(ErrorMessage.EXCEL_REPORT_CREATION_ERROR_MESSAGE);
        }
    }

    @GetMapping("/download/reservations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getReservationReport(){
        String fileName="reservations.xlsx";

        try {
            ByteArrayInputStream bais= reportService.getReservationReport();
            InputStreamResource file=new InputStreamResource(bais);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+fileName)
                    .contentType(MediaType.parseMediaType("application/vmd.ms-excel")).body(file);

        } catch (IOException e) {
            throw new ExcelReportException(ErrorMessage.EXCEL_REPORT_CREATION_ERROR_MESSAGE);
        }
    }
}
