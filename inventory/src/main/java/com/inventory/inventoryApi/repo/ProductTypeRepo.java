package com.inventory.inventoryApi.repo;

import com.inventory.inventoryApi.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Long> {
}
