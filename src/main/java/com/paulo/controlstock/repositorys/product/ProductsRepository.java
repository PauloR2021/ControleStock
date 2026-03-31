package com.paulo.controlstock.repositorys.product;

import com.paulo.controlstock.models.product.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsModel, Integer> {

    List<ProductsModel> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);


}
