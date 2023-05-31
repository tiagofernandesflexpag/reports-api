package br.com.flexpag.reports.controller;

import br.com.flexpag.reports.factory.ClientReport;
import br.com.flexpag.reports.factory.Report;
import br.com.flexpag.reports.factory.ReportFactory;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import br.com.flexpag.reports.factory.enums.PaymentType;
import br.com.flexpag.reports.factory.enums.ReportType;
import br.com.flexpag.reports.factory.enums.Status;
import br.com.flexpag.reports.service.ReportGenerateService;
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
    ReportGenerateService reportGenerateService;

    @PostMapping
    public void generateReport(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = true) ReportType reportType){

        ParamRequest paramRequest = new ParamRequest(status, date, paymentType, clientId, reportType);

        reportGenerateService.generateReport(paramRequest);

    }



}
