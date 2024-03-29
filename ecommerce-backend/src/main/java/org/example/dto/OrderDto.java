package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.BillingInfo;
import org.example.entity.Cart;
import org.example.entity.Customer;
import org.example.entity.Payment;
import org.example.util.enums.OrderStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {

    private Cart cart;
    private String address;
    private String phone;
    private String tax;
    private String charge;
    private Integer zipCode;
    private Long tot;
    private String city;
    private BillingInfo billingInfo;
    private Customer customer;
    private Payment payment;
    private OrderStatus status;
}
