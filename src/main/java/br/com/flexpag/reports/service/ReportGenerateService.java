package br.com.flexpag.reports.service;

import br.com.flexpag.reports.factory.Report;
import br.com.flexpag.reports.factory.ReportFactory;
import br.com.flexpag.reports.factory.dto.ParamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportGenerateService {

    public final ReportFactory reportFactory;

    public ByteArrayOutputStream generateReport(ParamRequest paramRequest){

        Report report = reportFactory.createReport(paramRequest);

        return report.getReport(paramRequest);

    }

}
