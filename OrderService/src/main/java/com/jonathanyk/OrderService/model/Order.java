package com.jonathanyk.OrderService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderNo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;
}
