package com.inventory.inventoryApi.web;

import com.inventory.inventoryApi.model.ProductType;
import com.inventory.inventoryApi.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    private final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);


    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getProductType();
        return ResponseEntity.ok(productTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        try {
            Optional<ProductType> productType = productTypeService.getProductTypeById(id);
            return productType.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            logger.error("Error retrieving product type by ID: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        ProductType createdProductType = productTypeService.addProductType(productType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductType);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProductType(@PathVariable Long id, @RequestBody ProductType updatedProductType) {
        try {
            ProductType updated = productTypeService.updateProductType(id, updatedProductType);
            //return ResponseEntity.ok(updated);
            return ResponseEntity.ok("ProductType Updated Successfully");
        } catch (Exception e) {
            logger.error("Error updating product type with ID {}: {}", id, e.getMessage());
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductType With ID Not Found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductType(@PathVariable Long id) {
        try {
            productTypeService.deleteProductType(id);
           // return ResponseEntity.noContent().build();
            return ResponseEntity.ok("ProductType Deleted Successfully");
        } catch (Exception e) {
            logger.error("Error deleting product type with ID {}: {}", id, e.getMessage());
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProductType Not Found Or Already Deleted");
        }
    }
}
