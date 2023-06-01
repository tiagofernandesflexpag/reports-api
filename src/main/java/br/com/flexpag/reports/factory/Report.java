package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.factory.dto.ParamRequest;

import java.io.ByteArrayOutputStream;

public interface Report {

    ByteArrayOutputStream getReport(ParamRequest paramRequest);


}
