package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stock;

    @Column(name = "Quantity")
    private int qty;

    @Column(name = "Product Total")
    private int productTot;

    @OneToOne(mappedBy = "cart")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Boolean completed;
}
