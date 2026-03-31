package com.paulo.controlstock.controllers.products;

import com.paulo.controlstock.api.ResponseApiControl;
import com.paulo.controlstock.dtos.products.RequestProductsDTO;
import com.paulo.controlstock.dtos.products.ResponseProductsDTO;
import com.paulo.controlstock.infra.security.SecurityConfiguration;
import com.paulo.controlstock.models.product.ProductsModel;
import com.paulo.controlstock.services.product.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @PostMapping("/create")
    @Operation(summary = "Rota para criar produto",description = "Cria novos produtos")
    public ResponseEntity<ResponseApiControl<ResponseProductsDTO>> createProduct(
            @RequestBody
            @Valid
            RequestProductsDTO dto)
    {
        ResponseProductsDTO response = productsService.createProduct(dto);

        ResponseApiControl<ResponseProductsDTO> apiResponse =
                new ResponseApiControl<>(true,"Product created successfully", response,HttpStatus.CREATED.value());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/all")
    @Operation(summary = "Rota para listar todos os Produtos",description = "Lista todos os Produtos")
    public ResponseEntity<ResponseApiControl<List<ResponseProductsDTO>>> getAllProducts(){
        List<ResponseProductsDTO> response = productsService.getAllProducts();

        ResponseApiControl<List<ResponseProductsDTO>> apiResponse =
                new ResponseApiControl<>(true,"Products find all", response,HttpStatus.OK.value());

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    @Operation(summary = "Rota para listar os produtos por pesquisa", description = "Listando os produtos através das pesquisas")
    public ResponseEntity<ResponseApiControl<List<ProductsModel>>> search(@RequestParam String q){
        List<ProductsModel> response = productsService.search(q);

        ResponseApiControl<List<ProductsModel>> apiResponse =
                new  ResponseApiControl<>(true,"Products find all", response,HttpStatus.OK.value());

        return ResponseEntity.ok(apiResponse);
    }
}
