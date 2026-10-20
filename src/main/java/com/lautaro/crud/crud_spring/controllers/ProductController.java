package com.lautaro.crud.crud_spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lautaro.crud.crud_spring.models.Product;
import com.lautaro.crud.crud_spring.services.ProductService;

@RestController
@RequestMapping("/show")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Product> all(){
        return service.findAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> showProduct (@PathVariable Long id){
        return service.findProductById(id);
    }

    @GetMapping("/min")
    public Optional<Product> minProduct(){
        return service.findMinPriceProduct();
    }

    @GetMapping("/max")
    public Optional<Product> maxProduct(){
        return service.findMaxPriceProduct();
    }

    @GetMapping("/minOrd")
    public List<Product> minOrd(){
        return service.findAllProductOrderByPriceAsc();
    }

    @GetMapping("/maxOrd")
    public List<Product> maxOrd(){
        return service.findAllProductOrderByPriceDesc();
    }

    @GetMapping("/alfDesc")
    public List<Product> alfDesc(){
        return service.findAllProductOrderByAlfbDesc();
    }

    @GetMapping("/alfAsc")
    public List<Product> alfAsc(){
        return service.findAllProductOrderByAlfbAsc();
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product){
        service.saveProduct(product);
        return product;
    }
}
