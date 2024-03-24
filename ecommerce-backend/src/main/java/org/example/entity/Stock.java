package org.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "stock")
public class Stock{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Stock ID")
    private Long id;

    @Column(name = "Addmin ID")//join
    private String AddminId;

    @Column(name = "Product ID")//join
    private String ProductId;

    @Column(name = "Color")
    private  String color;

    @Column(name = "Size")
    private String size;

    @Column(name = "Price")
    private String price;

    @Column(name = "Quantity")
    private String qty;

}
