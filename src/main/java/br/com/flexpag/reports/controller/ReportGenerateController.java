package br.com.flexpag.reports.controller;

import br.com.flexpag.reports.factory.ClientReport;
import br.com.flexpag.reports.factory.Report;
import br.com.flexpag.reports.factory.ReportFactory;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import br.com.flexpag.reports.factory.enums.PaymentType;
import br.com.flexpag.reports.factory.enums.ReportType;
import br.com.flexpag.reports.factory.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/report")
public class ReportGenerateController {

    @Autowired
    ClientReport clientReport;

    @PostMapping
    public void generateReport(@RequestParam Status status, LocalDate date, PaymentType paymentType, Long clientId, ReportType reportType){

        ParamRequest paramRequest = new ParamRequest(status, date, paymentType, clientId, reportType);

        Report report = ReportFactory.createReport(paramRequest);

        report.getReport(paramRequest);

    }



}
