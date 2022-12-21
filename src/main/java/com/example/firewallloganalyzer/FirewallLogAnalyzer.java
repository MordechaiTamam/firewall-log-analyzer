package com.example.firewallloganalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.io.*;

public class FirewallLogAnalyzer {
    @Autowired
    IFWLogRecordParser parser;

    @Value("${log-file-path}")
    String logFile;

    Collection<String> run(){
        Collection<String> distinctIps = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceUtils.getFile(logFile)))) {
            //Read line by line:
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                LogRecord record = parser.parse(line);
                //TODO: Filter

                distinctIps.add(record.getSrcIp());
            }
            return distinctIps;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
