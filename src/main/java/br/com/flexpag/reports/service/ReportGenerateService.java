package br.com.flexpag.reports.service;

import br.com.flexpag.reports.factory.Report;
import br.com.flexpag.reports.factory.ReportFactory;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import org.springframework.stereotype.Service;

@Service
public class ReportGenerateService {

    public void generateReport(ParamRequest paramRequest){

        Report report = ReportFactory.createReport(paramRequest);

        report.getReport(paramRequest);

    }

}
