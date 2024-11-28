package com.ecom.product_api.util;

import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StanderdResponse {
    private int code;
    private Object data;
    private String message;
}
