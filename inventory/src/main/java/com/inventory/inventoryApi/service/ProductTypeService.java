package com.inventory.inventoryApi.service;

import com.inventory.inventoryApi.model.ProductType;
import com.inventory.inventoryApi.repo.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService{

    @Autowired
    private ProductTypeRepo productTypeRepo;


    public ProductType addProductType(ProductType productType) {
        return productTypeRepo.save(productType);
    }


    public List<ProductType> getProductType() {
        return productTypeRepo.findAll();
    }


    public Optional<ProductType>  getProductTypeById(Long id) {
        return productTypeRepo.findById(id) ;
    }


    public ProductType updateProductType(Long id, ProductType productType) {
        if (productTypeRepo.existsById(id)) {
            productType.setId(id);
            return productTypeRepo.save(productType);

        } else {

        throw new IllegalArgumentException("ProductType with ID " + id + " not found.");
    }}


    public void deleteProductType(Long id) {
        productTypeRepo.deleteById(id);

    }
}
