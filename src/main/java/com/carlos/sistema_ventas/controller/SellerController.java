package com.carlos.sistema_ventas.controller;

import com.carlos.sistema_ventas.dto.auth.LoginRequest;
import com.carlos.sistema_ventas.dto.seller.SellerRequest;
import com.carlos.sistema_ventas.dto.seller.SellerResponse;
import com.carlos.sistema_ventas.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService service;

    @GetMapping
    public ResponseEntity<List<SellerResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SellerResponse> create(@RequestBody @Valid SellerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PostMapping("/login")
    public ResponseEntity<SellerResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(service.login(request.email(), request.password()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerResponse> update(@PathVariable Long id, @RequestBody @Valid SellerRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}