package com.jonathanyk.OrderService.service;

import com.jonathanyk.OrderService.dto.OrderItemsDto;
import com.jonathanyk.OrderService.dto.OrderRequest;
import com.jonathanyk.OrderService.model.Order;
import com.jonathanyk.OrderService.model.OrderItems;
import com.jonathanyk.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList()
                .stream().map(orderItemsDto -> mapFromDto(orderItemsDto)).toList();

        order.setOrderItemsList(orderItems);

        orderRepository.save(order);
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
