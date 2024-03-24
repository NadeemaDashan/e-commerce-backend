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
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Long id;

    @Column(name = "Email")
    private String mail;

    @Column(name = "UserName")
    private String name ;

    @Column(name = "Address")
    private String address;

    @Column(name ="Contact")
    private String phone;
}
