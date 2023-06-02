package br.com.flexpag.reports.factory.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReportType {

    CLIENT("clientReport"),
    TRANSACTION("transactionReport");

    private final String type;

}
