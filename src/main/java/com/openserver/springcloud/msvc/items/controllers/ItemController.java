package com.openserver.springcloud.msvc.items.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openserver.springcloud.msvc.items.models.Item;
import com.openserver.springcloud.msvc.items.services.ItemService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService _service) {
        this.service = _service;
    }

    @GetMapping("/")
    public List<Item> getList() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {
        Optional<Item> itemOptional = service.findById(id);
        if (itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        }
        // return ResponseEntity.notFound().build();
        return ResponseEntity.status(404)
                .body(Collections
                        .singletonMap("message", "No existe el producto en el microservicio msvc-products"));
    }
}
