package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.enums.PaymentStatus;
import org.example.util.enums.PaymentType;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long id;
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    @Column(name = "Payment Total" , nullable = false)
    private String Tot;

    @Column(name = "Payment Status" , nullable = false)
    @Convert(converter =  PaymentStatus.class)
    private PaymentStatus status;

    @Column(name = "Payment Type" , nullable = false)
    @Convert(converter =  PaymentType.class)
    private PaymentType type;

}
