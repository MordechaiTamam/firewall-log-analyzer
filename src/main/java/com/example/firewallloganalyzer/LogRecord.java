package com.example.firewallloganalyzer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogRecord {
    String domainName;
    String srcIp;
    String dstIp;
    String user;

    @Override
    public String toString() {
        return "LogRecord{" +
                "domainName='" + domainName + '\'' +
                ", srcIp='" + srcIp + '\'' +
                ", dstIp='" + dstIp + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
