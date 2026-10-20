package com.lautaro.crud.crud_spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lautaro.crud.crud_spring.models.Product;



public interface ProductRepository extends CrudRepository<Product, Long>{

    //buscar por nombre
    Optional<Product> findByName(String name);
    //menor precio
    @Query("select p from Product p where p.price = (select min(p2.price) from Product p2)")
    Optional<Product> findProductMinPrice();
    //mayor precio
    @Query("select p from Product p where p.price = (select max(p2.price) from Product p2)")
    Optional<Product> findProductMaxPrice();
    //ordenar de menor a mayor precio
    @Query("select p from Product p order by p.price asc")
    List<Product> findAllOrderByPriceAsc();
    //odenar de mayor a menor precio
    @Query("select p from Product p order by p.price desc")
    List<Product> findAllOrderByPriceDesc();
    //ordenar por orden alfabetico asc
    @Query("select p from Product p order by p.name asc")
    List<Product> findAllOrderByAlfbAsc();
    //ordenar por orden alfabetico desc
    @Query("select p from Product p order by p.name desc")
    List<Product> findAllOrderByAlfbDesc();

}
