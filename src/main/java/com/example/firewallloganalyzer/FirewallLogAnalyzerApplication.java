package com.example.firewallloganalyzer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
@Slf4j
@SpringBootApplication
public class FirewallLogAnalyzerApplication {
    @Bean
    ICloudServiceDB cloudServiceDB(){
        return new CloudServiceDB();
    }

    @Bean
    IFWLogRecordParser fwLogRecordAnalyzer(){
        return new FWLogRecordParser();
    }

    @Bean
    IDNSLookup idnsLookup(){
        return new IDNSLookup() {
            @Override
            public Optional<String> lookup(String ip) {
                return IDNSLookup.super.lookup(ip);
            }
        };
    }

    @Bean
    FirewallLogAnalyzer application(){
        return new FirewallLogAnalyzer();
    }

    public static void main(String[] args) {
        ApplicationContext ctx  =SpringApplication.run(FirewallLogAnalyzerApplication.class, args);
        FirewallLogAnalyzer firewallLogAnalyzer = ctx.getBean(FirewallLogAnalyzer.class);
        Collection<String> ips = firewallLogAnalyzer.run();
        log.info(StringUtils.join(ips, "\n"));
    }
}
