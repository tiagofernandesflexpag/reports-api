package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.factory.dto.ParamRequest;
import org.springframework.stereotype.Service;

@Service
public class ReportFactory {

    public static Report createReport(ParamRequest paramRequest){

        switch (paramRequest.reportType()) {
            case CLIENT:
                return new ClientReport();
            case TRANSACTION:
                return new TransactionReport();
            default:
                throw new IllegalArgumentException("Unsupported report type: " + paramRequest.reportType());
        }
    }

    }
