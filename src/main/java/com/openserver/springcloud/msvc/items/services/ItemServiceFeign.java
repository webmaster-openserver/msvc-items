package com.openserver.springcloud.msvc.items.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openserver.springcloud.msvc.items.clients.ProductFeignClient;
import com.openserver.springcloud.msvc.items.models.Item;
import com.openserver.springcloud.msvc.items.models.Product;

import feign.FeignException;

@Service
public class ItemServiceFeign implements ItemService {

    // final private ProductFeignClient client;
    @Autowired
    private ProductFeignClient client;

    @Override
    public List<Item> findAll() {
        return client.findAll().stream().map(p -> {
            Random rnd = new Random();

            return new Item(p, rnd.nextInt(10) + 1);
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        try {
            Product prd = client.details(id);
            return Optional.of(new Item(prd, new Random().nextInt(10) + 1));
        } catch (FeignException ex) {
            return Optional.empty();
        }
    }

}
