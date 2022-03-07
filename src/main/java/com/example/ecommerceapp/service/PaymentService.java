package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.PaymentDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService {

    /*
    Example method which could execute request to endpoint provided by payments company to inform them about
    new payment request and get more details about it.
     */
    public PaymentDetails orderPayment(String methodName, Double sum) {
        log.info("Ordering a payment by informing external company and getting details");
        return getPaymentDetails(methodName, sum);
    }

    /*
    Method which returns mocked info about payment details (should be provided by external company service(endpoint)).
     */
    private PaymentDetails getPaymentDetails(String methodName, Double sum) {
        return new PaymentDetails(methodName, "Fake Payment Provider Company",
                sum, "http://www.fake-domain.com/fake/path/to/payment/url");
    }
}
