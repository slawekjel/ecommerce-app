package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.ShipmentDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@AllArgsConstructor
public class ShipmentService {

    /*
    Example method which could execute request to endpoint provided by shipment company to inform them about
    new shipment request and get more details about it.
    */
    public ShipmentDetails orderShipment(String methodName) {
        log.info("Ordering a shipment by informing external company and getting details");
        return getShipmentDetails(methodName);
    }


    /*
    Method which returns mocked info about shipment details (should be provided by external company service(endpoint)).
     */
    private ShipmentDetails getShipmentDetails(String methodName) {
        return new ShipmentDetails("Fake Company Name Ltd.", methodName, 15.0,
                "http://www.fake-domain.com/fake/path/to/track/shipment",
                LocalDate.now());
    }
}
