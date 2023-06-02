package br.com.flexpag.reports.controller;

import br.com.flexpag.reports.factory.dto.ReportResponseDTO;
import br.com.flexpag.reports.service.ReportDownloadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/report/download")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportDownloadController {

    private final ReportDownloadService reportDownloadService;

    @GetMapping
    public ResponseEntity<ReportResponseDTO> getDownloadLink(@RequestParam String fileName) throws SQLException {

        try{

            var fileLink = reportDownloadService.getDownloadLink(fileName);

            return ResponseEntity.ok().body(fileLink);

        }catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();

        }

    }

}
