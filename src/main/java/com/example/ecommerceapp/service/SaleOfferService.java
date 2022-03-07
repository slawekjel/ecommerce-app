package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.dto.SaleOfferDto;
import com.example.ecommerceapp.model.dto.SaleOffersDetailsDto;
import com.example.ecommerceapp.model.entities.Item;
import com.example.ecommerceapp.model.entities.SaleOffer;
import com.example.ecommerceapp.model.request.SaleOfferPartialRequest;
import com.example.ecommerceapp.model.request.SaleOfferRequest;
import com.example.ecommerceapp.repository.SaleOfferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SaleOfferService {

    private final SaleOfferRepository saleOfferRepository;

    public SaleOfferDto createSaleOffer(SaleOfferRequest saleOfferRequest) {
        log.info("Creating sale offer");
        var item = Item.builder()
                .name(saleOfferRequest.getItemName())
                .description(saleOfferRequest.getItemDescription())
                .category(saleOfferRequest.getItemCategory())
                .producer(saleOfferRequest.getItemProducer())
                .build();

        var saleOffer = SaleOffer.builder()
                .name(saleOfferRequest.getName())
                .description(saleOfferRequest.getDescription())
                .status(saleOfferRequest.getStatus())
                .price(saleOfferRequest.getPrice())
                .isNew(saleOfferRequest.isNew())
                .quantity(saleOfferRequest.getQuantity())
                .shipmentNames(saleOfferRequest.getShipmentNames())
                .paymentNames(saleOfferRequest.getPaymentNames())
                .item(item)
                .build();

        item.setSaleOffer(saleOffer);

        var savedSaleOffer = saleOfferRepository.save(saleOffer);

        return mapSaleOfferToDto(savedSaleOffer);
    }

    public List<SaleOfferDto> retrieveAllSaleOffers() {
        log.info("Retrieving all sale offers");
        List<SaleOffer> saleOffers = saleOfferRepository.findAll();

        return saleOffers.stream()
                .map(this::mapSaleOfferToDto)
                .collect(Collectors.toList());
    }

    public void deleteSaleOffer(Long id) {
        log.info("Deleting sale offer with id: {}", id);
        saleOfferRepository.deleteById(id);
    }

    public Optional<SaleOffer> retrieveSaleOffer(Long id) {
        log.info("Retrieving sale offer with id: {}", id);
        return saleOfferRepository.findById(id);
    }

    public SaleOfferDto updateSaleOffer(Long id, SaleOfferPartialRequest saleOfferPartialRequest) {
        log.info("Updating sale offer with id: {}", id);
        Optional<SaleOffer> oldSafeOffer = retrieveSaleOffer(id);

        if (oldSafeOffer.isPresent()) {
            oldSafeOffer.get()
                    .setName(saleOfferPartialRequest.getName());
            oldSafeOffer.get()
                    .setDescription(saleOfferPartialRequest.getDescription());
            oldSafeOffer.get()
                    .setPrice(saleOfferPartialRequest.getPrice());
            oldSafeOffer.get()
                    .setQuantity(saleOfferPartialRequest.getQuantity());
            oldSafeOffer.get()
                    .setNew(saleOfferPartialRequest.isNew());
            oldSafeOffer.get()
                    .setPaymentNames(saleOfferPartialRequest.getPaymentNames());
            oldSafeOffer.get()
                    .setShipmentNames(saleOfferPartialRequest.getShipmentNames());
            var saleOffer = saleOfferRepository.save(oldSafeOffer.get());
            return mapSaleOfferToDto(saleOffer);
        } else {
            return null;
        }

    }

    public SaleOffersDetailsDto retrieveSaleOfferWithDetails(Long id) {
        log.info("Retrieving sale offer with details for id: {}", id);
        Optional<SaleOffer> saleOffer = retrieveSaleOffer(id);

        return saleOffer.map(offer -> SaleOffersDetailsDto.builder()
                        .name(offer.getName())
                        .description(offer.getDescription())
                        .isNew(offer.isNew())
                        .price(offer.getPrice())
                        .quantity(offer.getQuantity())
                        .status(offer.getStatus())
                        .shipmentNames(offer.getShipmentNames())
                        .paymentNames(offer.getPaymentNames())
                        .item(offer
                                .getItem())
                        .build())
                .orElse(null);
    }

    public void updateItemsQuantityInOffer(Long saleId, Long quantity) {
        log.info("Reducing quantity of {} for id: {}", quantity, saleId);
        Optional<SaleOffer> saleOffer = retrieveSaleOffer(saleId);

        saleOffer.ifPresent(offer -> {
            Long oldQuantity = offer
                    .getQuantity();

            offer.setQuantity(oldQuantity - quantity);
        });
    }

    private SaleOfferDto mapSaleOfferToDto(SaleOffer savedSaleOffer) {
        return SaleOfferDto.builder()
                .id(savedSaleOffer.getId())
                .itemId(savedSaleOffer.getItem()
                        .getId())
                .name(savedSaleOffer.getName())
                .description(savedSaleOffer.getDescription())
                .isNew(savedSaleOffer.isNew())
                .price(savedSaleOffer.getPrice())
                .quantity(savedSaleOffer.getQuantity())
                .status(savedSaleOffer.getStatus())
                .shipmentNames(savedSaleOffer.getShipmentNames())
                .paymentNames(savedSaleOffer.getPaymentNames())
                .build();
    }
}
