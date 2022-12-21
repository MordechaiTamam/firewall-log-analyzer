package com.example.firewallloganalyzer;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Stream;

public class FWLogRecordParser implements IFWLogRecordParser {
    @Autowired
    IDNSLookup idnsLookup;
    @Override
    public LogRecord parse(String log) {
        String[] arr = log.split(" ");
        final String[] domain = new String[1];
        LogRecord retVal = new LogRecord();
        Stream.of(arr)
                .forEach(str -> {
                    if (str.startsWith("DOMAIN=")){
                        retVal.setDomainName(str.split("=")[1]);
                    } else if (str.startsWith("USER=")) {
                        retVal.setUser(str.split("=")[1]);
                    }else if(str.startsWith("SRC=")){
                        retVal.setSrcIp(str.split("=")[1]);
                    }else if(str.startsWith("DST=")){
                        retVal.setDstIp(str.split("=")[1]);
                    }
                });

        if(retVal.getDomainName() == null){
            Optional<String> domainName = idnsLookup.lookup(retVal.getDstIp());
            domainName.ifPresent(d->{retVal.setDomainName(d);});
        }
        return retVal;
    }
}
