package com.openserver.springcloud.msvc.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openserver.springcloud.msvc.items.models.Product;

@FeignClient(name = "msvc-products", url = "http://localhost:8001/api/products")
public interface ProductFeignClient {

    @GetMapping("/")
    List<Product> findAll();

    @GetMapping("/{id}")
    Product details(@PathVariable Long id);
}
