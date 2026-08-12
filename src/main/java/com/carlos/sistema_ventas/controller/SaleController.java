package com.carlos.sistema_ventas.controller;

import com.carlos.sistema_ventas.dto.sale.SaleRequest;
import com.carlos.sistema_ventas.dto.sale.SaleResponse;
import com.carlos.sistema_ventas.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody @Valid SaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
}