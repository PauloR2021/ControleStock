package com.paulo.controlstock.services.product;


import com.paulo.controlstock.dtos.products.RequestProductsDTO;
import com.paulo.controlstock.dtos.products.ResponseProductsDTO;
import com.paulo.controlstock.models.product.ProductsModel;
import com.paulo.controlstock.repositorys.product.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Transactional
    public ResponseProductsDTO createProduct(RequestProductsDTO dto) {
        ProductsModel product = new ProductsModel();

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setCategory(dto.category());
        product.setPrice(dto.price());
        product.setVenda(dto.venda());
        product.setStock(dto.stock());

        ProductsModel newProduct = productsRepository.save(product);

        return toResponse(newProduct);
    }

    @Transactional
    public List<ResponseProductsDTO> getAllProducts(){
        return productsRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public List<ProductsModel> search (String search){

        List<ProductsModel> produtos = productsRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search,search);

        if(produtos == null || produtos.isEmpty()){
            throw new RuntimeException("Nenhum produto encontrado");
        }

        return produtos;
    }



    private ResponseProductsDTO toResponse(ProductsModel  productsModel) {
        return new ResponseProductsDTO(
                productsModel.getId(),
                productsModel.getName(),
                productsModel.getDescription(),
                productsModel.getCategory(),
                productsModel.getPrice(),
                productsModel.getVenda(),
                productsModel.getStock()
        );
    }
}
