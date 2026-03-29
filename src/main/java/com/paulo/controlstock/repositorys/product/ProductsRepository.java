package com.paulo.controlstock.repositorys.product;

import com.paulo.controlstock.models.product.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsModel, Integer> {


}
