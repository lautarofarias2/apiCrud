package com.lautaro.crud.crud_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lautaro.crud.crud_spring.models.Product;
import com.lautaro.crud.crud_spring.repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAllProducts(){
        return ((List<Product>)repository.findAll());
    }

    public Optional<Product> findProductById(Long id){
        return repository.findById(id);
    }

    public List<Product> findProductsByName(String name){
        return repository.findProductsByName(name);
    }

    // producto mayor precio
    public Optional<Product> findMinPriceProduct(){
        return repository.findProductMinPrice();
    }

    // producto menor precio
    public Optional<Product> findMaxPriceProduct(){
        return repository.findProductMaxPrice();
    }

    // productos ordenado de men a may segun precio
    public List<Product> findAllProductOrderByPriceAsc(){
        return repository.findAllOrderByPriceAsc();
    }

    // productos ordenado de may a men segun precio
    public List<Product> findAllProductOrderByPriceDesc(){
        return repository.findAllOrderByPriceDesc();
    }

    // productos ordenado de men a may segun alfab
    public List<Product> findAllProductOrderByAlfbAsc(){
        return repository.findAllOrderByAlfbAsc();
    }

    // productos ordenado de men a may segun alfab
    public List<Product> findAllProductOrderByAlfbDesc(){
        return repository.findAllOrderByAlfbDesc();
    }

    //crear product
    public void saveProduct(Product product){
        repository.save(product);
    }
}
