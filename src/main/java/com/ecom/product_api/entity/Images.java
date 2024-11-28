package com.ecom.product_api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="product_images")
public class Images {
    @Id
    private String id;
    @Embedded
    private FileResource fileResource;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
