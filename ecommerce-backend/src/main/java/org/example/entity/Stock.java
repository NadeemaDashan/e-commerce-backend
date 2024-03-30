package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Table(name = "stock")
//public class Stock {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @OneToOne(mappedBy = "StockId")
//    private Cart cart;
//
//    @Column(name = "Color")
//    private String color;
//
//    @Column(name = "size")
//    private String size;
//
//    @Column(name = "price")
//    private String price;
//
//    @Column(name = "quantity")
//    private String qty;
//
//}
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;

    @OneToOne(mappedBy = "stock")
    private Cart cart;

    @Column(name = "Color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private String qty;

}
