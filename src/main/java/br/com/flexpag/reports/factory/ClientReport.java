package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.factory.dto.ParamRequest;
import org.springframework.stereotype.Component;

@Component
public class ClientReport implements Report{

    @Override
    public void getReport(ParamRequest paramRequest) {
        System.out.println(paramRequest.clientId());
        System.out.println(paramRequest.reportType());
    }

}
