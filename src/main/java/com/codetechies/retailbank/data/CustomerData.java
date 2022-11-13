package com.codetechies.retailbank.data;


import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CustomerData {

    private Map<String,Integer> customerData = Stream.of(new Object[][]{
        {"AMIT DATTA", 1000}
    }).collect(Collectors.toMap(data -> (String) data[0],data-> (Integer) data[1]));

    public Integer getBalance(String customerName){
        return customerData.get(customerName.toUpperCase());
    }
}
