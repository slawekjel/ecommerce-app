package com.example.ecommerceapp.service;

import com.example.ecommerceapp.model.PaymentDetails;
import com.example.ecommerceapp.model.ShipmentDetails;
import com.example.ecommerceapp.model.dto.OrderDto;
import com.example.ecommerceapp.model.entities.Order;
import com.example.ecommerceapp.model.entities.Payment;
import com.example.ecommerceapp.model.entities.SaleOffer;
import com.example.ecommerceapp.model.entities.Shipment;
import com.example.ecommerceapp.model.request.OrderRequest;
import com.example.ecommerceapp.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final SaleOfferService saleOfferService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    public OrderDto createOrder(OrderRequest orderRequest) {
        log.info("Preparing and creating order");
        var orderBuilder = Order.builder()
                .quantity(orderRequest.getQuantity())
                .pricePerItem(orderRequest.getPricePerItem())
                .shipmentMethod(orderRequest.getShipmentMethod())
                .paymentMethod(orderRequest.getPaymentMethod());

        Optional<SaleOffer> saleOffer = saleOfferService.retrieveSaleOffer(orderRequest.getSaleId());
        saleOffer.ifPresent(orderBuilder::saleOffer);

        var shipmentDetails = shipmentService.orderShipment(orderRequest.getShipmentMethod());
        Double finalPrice = calculateFinalPrice(orderRequest.getPricePerItem(), orderRequest.getQuantity(),
                shipmentDetails.getPrice());
        var paymentDetails = paymentService.orderPayment(orderRequest.getPaymentMethod(), finalPrice);

        orderBuilder.payment(mapToPayment(paymentDetails))
                .shipment(mapToShipment(shipmentDetails))
                .finalPrice(finalPrice);

        var savedOrder = createOrder(orderBuilder.build());

        saleOfferService.updateItemsQuantityInOffer(orderRequest.getSaleId(), orderRequest.getQuantity());

        return OrderDto.builder()
                .orderId(savedOrder.getId())
                .saleId(savedOrder.getSaleOffer()
                        .getId())
                .quantity(savedOrder.getSaleOffer()
                        .getQuantity())
                .pricePerItem(savedOrder.getPricePerItem())
                .finalPrice(savedOrder.getFinalPrice())
                .paymentDetails(mapToPaymentDetails(savedOrder.getPayment()))
                .shipmentDetails(mapToShippigDetails(savedOrder.getShipment()))
                .build();
    }

    private ShipmentDetails mapToShippigDetails(Shipment shipment) {
        return new ShipmentDetails(shipment.getCompanyName(), shipment.getMethodName(), shipment.getPrice(),
                shipment.getTrackingUrl(),
                shipment.getDeliveryDate());
    }

    private PaymentDetails mapToPaymentDetails(Payment payment) {
        return new PaymentDetails(payment.getMethod(), payment.getProvider(), payment.getSum(), payment.getUrl());
    }

    private Payment mapToPayment(PaymentDetails paymentDetails) {
        return Payment.builder()
                .method(paymentDetails.getMethod())
                .sum(paymentDetails.getSum())
                .url(paymentDetails.getUrl())
                .provider(paymentDetails.getProvider())
                .build();
    }

    private Shipment mapToShipment(ShipmentDetails shipmentDetails) {
        return Shipment.builder()
                .companyName(shipmentDetails.getCompanyName())
                .methodName(shipmentDetails.getMethodName())
                .price(shipmentDetails.getPrice())
                .deliveryDate(shipmentDetails.getDeliveryDate())
                .trackingUrl(shipmentDetails.getTrackingUrl())
                .build();
    }

    private Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    private Double calculateFinalPrice(Double itemPrice, Long quantity, Double shipmentPrice) {
        return itemPrice * quantity + shipmentPrice;
    }
}
