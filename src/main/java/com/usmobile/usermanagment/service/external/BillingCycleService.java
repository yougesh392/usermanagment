package com.usmobile.usermanagment.service.external;

import com.usmobile.usermanagment.model.BillingCycleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BillingCycleService {
    private static final Logger log = LoggerFactory.getLogger(BillingCycleService.class);

    private final RestTemplate restTemplate;
    private static String url = "http://localhost:8081/api/billingcycle";

    public BillingCycleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void callBillingCycleManagement(BillingCycleRequest billingCycleRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BillingCycleRequest> request = new HttpEntity<>(billingCycleRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            // handle the response
            log.debug("Billing cycle created successfully");
        } else {
            log.debug("Error occurred: " + response.getStatusCode());
        }
    }


}
