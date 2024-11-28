package com.ecom.product_api.api;

import com.ecom.product_api.Service.ProductService;
import com.ecom.product_api.dto.request.RequestProductDto;
import com.ecom.product_api.util.StanderdResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/procucts-service/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<StanderdResponse> createProduct(@RequestParam("data") String data,
                                                          @RequestParam("image") MultipartFile image) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestProductDto dto = objectMapper.readValue(data,RequestProductDto.class);
        productService.createProduct(dto,image);
        return new ResponseEntity<>(new StanderdResponse(201,null,"Product Saved"),
                HttpStatus.CREATED
                );
    }
    @PutMapping("/update/{productId}")
    public ResponseEntity<StanderdResponse> updateProduct(@RequestBody RequestProductDto dto,@PathVariable String productId){
        productService.updateProduct(dto,productId);
        return new ResponseEntity<>(new StanderdResponse(201,null,"Product Updated"),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<StanderdResponse> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new StanderdResponse(204,null,"Product Saved"),
                HttpStatus.NO_CONTENT
        );
    }
    @GetMapping("/find-by-id/{productId}")
    public ResponseEntity<StanderdResponse> findProduct(@PathVariable String productId){

        return new ResponseEntity<>(new StanderdResponse(200,productService.findProduct(productId),"Product Available"),
                HttpStatus.CREATED
        );
    }
    @GetMapping("/search-products")
    public ResponseEntity<StanderdResponse> searchAllProduct(@RequestParam String searchText,@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(new StanderdResponse(200,productService.searchAllProduct(searchText,page,size),"Product List"),
                HttpStatus.OK
        );
    }
    @PutMapping("/update-image/{imageId}")
    public ResponseEntity<StanderdResponse> updateImage(@PathVariable String imageId,@RequestParam("image") MultipartFile file){
        productService.updateImage(imageId,file);
        return new ResponseEntity<>(new StanderdResponse(201,null,"Product Image Updated"),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("/delete-image-by-id/{imageId}")
    public ResponseEntity<StanderdResponse> deleteImage(@PathVariable String imageId){
        productService.deleteImage(imageId);
        return new ResponseEntity<>(new StanderdResponse(204,null,"Product Image Deleted"),
                HttpStatus.NO_CONTENT
        );
    }
}
