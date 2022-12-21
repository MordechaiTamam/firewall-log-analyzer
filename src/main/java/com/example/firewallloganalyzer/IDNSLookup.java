package com.example.firewallloganalyzer;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface IDNSLookup {
    Map<String,String> cache = new ConcurrentHashMap<>();
    default Optional<String> lookup(String ip){
        if(ip == null | ip.length()==0){
            return Optional.ofNullable(null);
        }

        Optional<String> retVal = Optional.ofNullable(cache.get(ip));
        if (retVal.isEmpty()){
            try {
                InetAddress addr = InetAddress.getByName(ip);
                cache.put(ip,addr.getHostName());
                retVal = Optional.of(cache.get(ip));
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return retVal;
    }
}
