package com.example.firewallloganalyzer;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

public class CloudServiceEntity {
    @CsvBindByName(column = "Service name")
    @Getter
    String serviceName;
    @CsvBindByName(column = "Service domain")
    @Getter
    String serviceDomain;
    @CsvBindByName(column = "Risk")
    @Getter
    String risk;
    @CsvBindByName(column = "Country of origin")
    @Getter
    String countryOfOrigin;
    @CsvBindByName(column = "GDPR Compliant")
    @Getter
    String gdprCompliant;

    @Override
    public String toString() {
        return "CloudServiceEntity{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceDomain='" + serviceDomain + '\'' +
                ", risk='" + risk + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", gdprCompliant='" + gdprCompliant + '\'' +
                '}';
    }
}
