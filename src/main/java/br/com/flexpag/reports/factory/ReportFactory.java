package br.com.flexpag.reports.factory;

import br.com.flexpag.reports.factory.dto.ParamRequest;
import br.com.flexpag.reports.factory.enums.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReportFactory {

    private final Map<String, Report> services;

    public Report getService(String fileType) {
        var type = ReportType.valueOf(fileType);
        return services.get(type.getType());
    }
}