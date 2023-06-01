package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.factory.dto.ParamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportFactory {

    private final ClientReport clientReport;
    private final TransactionReport transactionReport;

    public Report createReport(ParamRequest paramRequest){

        switch (paramRequest.reportType()) {
            case CLIENT:
                return clientReport;
            case TRANSACTION:
                return transactionReport;
            default:
                throw new IllegalArgumentException("Unsupported report type: " + paramRequest.reportType());
        }
    }
}