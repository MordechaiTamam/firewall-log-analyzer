package com.example.firewallloganalyzer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class FWLogRecordAnalyzerTest {
    @Autowired
    IFWLogRecordParser logRecordAnalyzerl;
    @Test
    void parse() {
        LogRecord logRecord = logRecordAnalyzerl.parse("Feb  1 00:00:02 bridge kernel: INBOUND TCP: IN=br0 PHYSIN=eth0 OUT=br0 PHYSOUT=eth1 SRC=192.150.249.87 DST=11.11.11.84 LEN=40 TOS=0x00 PREC=0x00 TTL=110 ID=12973 PROTO=TCP SPT=220 DPT=6129 WINDOW=16384 RES=0x00 SYN URGP=0 USER=dave@acme.com DOMAIN=www.dropbox.com\n");
        log.info(logRecord.toString());
    }

    @Test
    void parseWithoutDomain() {
        LogRecord logRecord = logRecordAnalyzerl.parse("Feb  1 00:00:02 bridge kernel: INBOUND TCP: IN=br0 PHYSIN=eth0 OUT=br0 PHYSOUT=eth1 SRC=192.150.249.87 DST=11.11.11.84 LEN=40 TOS=0x00 PREC=0x00 TTL=110 ID=12973 PROTO=TCP SPT=220 DPT=6129 WINDOW=16384 RES=0x00 SYN URGP=0 USER=dave@acme.com\n");
        log.info(logRecord.toString());
    }
}