package com.codetechies.retailbank.controller;

import com.codetechies.retailbank.data.CustomerData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class RetailBankController {

    private CustomerData customerData;

    @GetMapping("/balance")
    public ResponseEntity<String> customerBalance(@AuthenticationPrincipal OAuth2User principle){

        String msg =  "Invalid user, unable to process further. Please authenticate yourself";

        if(principle != null) {
            String customerName = principle.getAttribute("name");
            log.info("Fetching account balance for customer : {}",customerName);
            var balance = customerData.getBalance(customerName);

            if(null == balance){
                msg = "Sorry no customer found with name: "+customerName;
            } else{
                msg = "Hello "+customerName+ " your available balance is :"+balance;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
