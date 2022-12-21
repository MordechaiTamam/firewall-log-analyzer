package com.example.firewallloganalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This implementation is based on a file that contains the cloud services
 */
@Slf4j
public class CloudServiceDB implements ICloudServiceDB{
    @Value("${cloud-service-file}")
    private String dbFileName;

    //Map of domain name to cloud service entity:
    private Map<String, CloudServiceEntity> map;

    @PostConstruct
    void init() throws FileNotFoundException {
        log.info("An INFO Message");
        // parse CSV file to create a list of `CloudServiceEntity` objects

        try (Reader reader = new FileReader(ResourceUtils.getFile(dbFileName))) {

            // create csv bean reader
            CsvToBean<CloudServiceEntity> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CloudServiceEntity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of CloudServiceEntity
            List<CloudServiceEntity> cloudServiceEntities = csvToBean.parse();
            log.info("loaded cloud services: \n {}",StringUtils.join(cloudServiceEntities, "\n"));
            map = cloudServiceEntities.stream()
                    .collect(Collectors.toMap(CloudServiceEntity::getServiceDomain, Function.identity()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public CloudServiceEntity getCloudService(String domain) {
        return map.get(domain);
    }
}
