package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.model.dto.SaleOfferDto;
import com.example.ecommerceapp.model.dto.SaleOffersDetailsDto;
import com.example.ecommerceapp.model.request.SaleOfferPartialRequest;
import com.example.ecommerceapp.model.request.SaleOfferRequest;
import com.example.ecommerceapp.service.SaleOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/offers/sales/")
public class SaleOfferController {

    private SaleOfferService saleOfferService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOfferDto> createSaleOffer(@RequestBody SaleOfferRequest saleOfferRequest) {
        log.info("Received a request with sale offer to create new sale offer.");
        try {
            var saleOfferDto = saleOfferService.createSaleOffer(saleOfferRequest);
            return new ResponseEntity<>(saleOfferDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOfferDto> updateSaleOffer(@PathVariable Long id, @RequestBody SaleOfferPartialRequest saleOfferPartialRequest) {
        log.info("Received a request to delete sale offer.");
        var saleOfferDto = saleOfferService.updateSaleOffer(id, saleOfferPartialRequest);
        if (Objects.nonNull(saleOfferDto)) {
            return new ResponseEntity<>(saleOfferDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleOfferDto>> getAllSaleOffers() {
        log.info("Received a request retrieve all sale offers.");
        try {
            List<SaleOfferDto> saleOfferDtos = saleOfferService.retrieveAllSaleOffers();

            if (saleOfferDtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(saleOfferDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleOffersDetailsDto> getSaleOfferWithDetails(@PathVariable Long id) {
        log.info("Received a request to retrieve sale offer.");
        var saleOffersDetailsDto = saleOfferService.retrieveSaleOfferWithDetails(id);

        return Objects.nonNull(saleOffersDetailsDto)
                ? new ResponseEntity<>(saleOffersDetailsDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteSaleOffer(@PathVariable Long id) {
        log.info("Received a request to delete sale offer.");
        try {
            saleOfferService.deleteSaleOffer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
