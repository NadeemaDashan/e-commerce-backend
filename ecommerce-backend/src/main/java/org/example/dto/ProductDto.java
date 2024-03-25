package org.example.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private Long id;
    private String name;
    private  String desc;
    private double price;
    private int soldCount;
    private byte[] imageData;
    private Category category;
    private Collection collection;
    private SubCategory subCategory;
}