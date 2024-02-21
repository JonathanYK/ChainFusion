package com.jonathanyk.OrderService.service;

import com.jonathanyk.OrderService.dto.OrderItemsDto;
import com.jonathanyk.OrderService.dto.OrderRequest;
import com.jonathanyk.OrderService.model.Order;
import com.jonathanyk.OrderService.model.OrderItems;
import com.jonathanyk.OrderService.repository.OrderRepository;
import com.jonathanyk.chainCommons.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest) {
        if (orderRequest.getOrderItemsDtoList() == null) {
            String errorMessage = "failed to parse order request!";
            log.error(errorMessage);
            throw new ResourceNotFoundException(errorMessage);
        }

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream().map(orderItemsDto -> mapFromDto(orderItemsDto)).toList();

        order.setOrderItemsList(orderItems);

        orderRepository.save(order);
        StringBuilder successMsg = new StringBuilder();
        for (OrderItems orderItemsOfSavedOrder : order.getOrderItemsList()) {
            successMsg.append("order with sku code: ")
                    .append(orderItemsOfSavedOrder.getSkuCode())
                    .append(" saved\n");
        }

        log.info(successMsg.toString());
        return successMsg.toString();
    }

    private OrderItems mapFromDto(OrderItemsDto orderItemsDto) {
        return OrderItems
                .builder()
                .skuCode(orderItemsDto.getSkuCode())
                .price(orderItemsDto.getPrice())
                .quantity(orderItemsDto.getQuantity())
                .build();
    }
}
