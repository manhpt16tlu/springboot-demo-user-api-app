package com.ncc.mn.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {
    private Integer id;
    private String productId;
    private String productName;
    private String productDescription;
}
