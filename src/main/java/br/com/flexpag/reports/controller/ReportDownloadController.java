package br.com.flexpag.reports.controller;

import br.com.flexpag.reports.service.ReportDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report/download")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportDownloadController {

    private final ReportDownloadService reportDownloadService;

    @GetMapping
    public ResponseEntity getDownloadLink(@RequestParam String fileName){

        String fileLink = reportDownloadService.getDownloadLink(fileName);

        return ResponseEntity.ok().body(fileLink);

    }

}
