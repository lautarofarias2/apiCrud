package com.lautaro.crud.crud_spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lautaro.crud.crud_spring.exceptions.ProductNotFoundException;
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
    public List<Product> all(){
        List<Product> list = service.findAllProducts();
        if(list.isEmpty()){
            throw new ProductNotFoundException("No hay productos");
        }
        return list;
    }

    @GetMapping("/{id}")
    public Product showProduct (@PathVariable Long id){
        return service.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Producto no encontrado: " + id));
    }

    @GetMapping("/search")
    public List<Product> showProductByName(@RequestParam String name){
        List<Product> list = service.findProductsByName(name);
        if(list.isEmpty()){
            throw new ProductNotFoundException("Productos no encontrados: " + name );
        }
        return list;
    }

    @GetMapping("/min")
    public Product minProduct(){
        return service.findMinPriceProduct().orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
    }

    @GetMapping("/max")
    public Product maxProduct(){
        return service.findMaxPriceProduct().orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));
    }

    @GetMapping("/minOrd")
    public List<Product> minOrd(){
        List<Product> list = service.findAllProductOrderByPriceAsc();
        if(list.isEmpty()){
            throw new ProductNotFoundException("No hay productos");
        }
        return list;
    }

    @GetMapping("/maxOrd")
    public List<Product> maxOrd(){
        List<Product> list = service.findAllProductOrderByPriceDesc();
        if(list.isEmpty()){
            throw new ProductNotFoundException("No hay productos");
        }
        return list;
    }

    @GetMapping("/alfDesc")
    public List<Product> alfDesc(){
        List<Product> list = service.findAllProductOrderByAlfbDesc();
        if(list.isEmpty()){
            throw new ProductNotFoundException("No hay productos");
        }
        return list;
    }

    @GetMapping("/alfAsc")
    public List<Product> alfAsc(){
        List<Product> list = service.findAllProductOrderByAlfbAsc();
        if(list.isEmpty()){
            throw new ProductNotFoundException("No hay productos");
        }
        return list;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product){
        service.saveProduct(product);
        return product;
    }
}
