package br.com.flexpag.reports.factory.dto;

import br.com.flexpag.reports.factory.enums.PaymentType;
import br.com.flexpag.reports.factory.enums.ReportType;
import br.com.flexpag.reports.factory.enums.Status;

import java.time.LocalDate;

public record ParamRequest(Status status, LocalDate date, PaymentType paymentType, Long clientId, ReportType reportType) {
}
